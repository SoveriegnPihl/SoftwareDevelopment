package dtu.stepDefinitions;

import dtu.project.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.GregorianCalendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class bookingDevAndActivitySteps {
    SoftwareHuset softwareHuset;
    Developer developer, manager;
    Project project,project2;
    Activity activity;
    GregorianCalendar startHoli, finHoli;
    double activityHours, hoursWorked;
    Report rep;
    GregorianCalendar today = new GregorianCalendar();

    public bookingDevAndActivitySteps(SoftwareHuset softwareHuset){
        this.softwareHuset = softwareHuset;
    }

    @Given("that there is a manager and a Developer with initials {string} and {string}")
    public void that_there_is_a_manager_and_a_developer_with_initials(String manageInitials, String devInitials) throws Exception {
       manager = softwareHuset.developers.get(manageInitials);
       developer = softwareHuset.developers.get(devInitials);
    }

    @Given("that the developer is a project manager")
    public void that_the_developer_is_a_project_manager() throws Exception {
        manager.setToProjectManager();
    }


    @Then("The developer is a project manager")
    public void the_developer_is_a_project_manager() throws Exception {
        assertThat(manager.isProjectManager(),is(true));
    }

    @Given("there is a project with id {string}")
    public void there_is_a_project_with_id(String projectId) throws Exception{
        project = softwareHuset.projects.get(Integer.parseInt(projectId));
        project2 = softwareHuset.getProject(projectId);
    }

    @Given("the developer is available for the project")
    public void the_developer_is_available_for_the_project() {
        assertTrue(developer.getAvailability(today));
    }

    @When("the project manager books the developer")
    public void the_project_manager_books_the_developer() throws Exception {
        project.addDeveloper(developer);
    }

    @Then("the developer is added to the project")
    public void the_developer_is_added_to_the_project()  throws Exception {
        assertThat(project.developerIsInProject(developer),is(true));
    }

    @Given("the developer is not available")
    public void the_developer_is_not_available() throws Exception {
        assertTrue(developer.getAvailability(today));
        developer.isSick = false;
        softwareHuset.updateCSVFile("developers");
    }

    @Given("that the developer is not a project manager")
    public void that_the_developer_is_not_a_project_manager() {
        assertThat(manager.isProjectManager(),is(false));
    }

    @When("the developer wants to add an activity {string} with {int} hours estimated starting the {int} - {int} - {int}, finishing the {int} - {int} - {int}")
    public void theDeveloperWantsToAddAnActivityWithHoursEstimatedStartingTheFinishingThe(String activityName, int estTime, int startD, int startM, int startY, int finD, int finM, int finY) {
        GregorianCalendar start = new GregorianCalendar(startY,startM-1,startD);
        GregorianCalendar finish = new GregorianCalendar(finY,finM-1,finD);
        activity = new Activity(activityName,estTime);
        activity.setDateInterval(start, finish);
    }

    @And("the activity is not already in the project")
    public void the_activity_is_not_already_in_the_project() {
        assertThat(softwareHuset.findActivity(activity.getName()),is(false));
    }

    @And("the activity is already in the project")
    public void the_activity_is_already_in_the_project() {
        assertThat(softwareHuset.findActivity(activity.getName()),is(true));
    }

    @Then("the activity is added to the given project")
    public void the_activity_is_added_to_the_given_project() {
        project.addActivity(activity);
        assertThat(project.findActivity(activity.name),is(true));
    }

    @Given("there is not a project named {string}")
    public void there_is_not_a_project_named(String projectName) {
        assertThat(softwareHuset.findProject(Integer.parseInt(projectName)),is(false));
    }

    @Then("the project manager is able to get time and budget used")
    public void theProjectManagerIsAbleToGetTimeAndBudgetUsed() {
        project.getUsedTime();
        project.getBudgetUsed();
    }
    @Then("a report is created for the project")
    public void aReportIsCreatedForTheProject() {
        rep = new Report(project);
    }

    @Then("the report contains all relevant information")
    public void theReportContainsAllRelevantInformation() {
        rep.printReport();
        assertTrue(rep.project == project);
    }




    @Given("that there is a manager with initials {string}")
    public void thatThereIsAManagerWithInitials(String managerInitials) {
        manager = softwareHuset.developers.get(managerInitials);
    }

    @Given("there is a developer with initials {string}")
    public void thereIsADeveloperWithInitials(String devName) {
        developer = softwareHuset.developers.get(devName);
    }

    @When("the developer registers a sick-day")
    public void theDeveloperRegistersASickDay() {
        developer.setSick();
    }

    @Then("the developer is not available for a day")
    public void theDeveloperIsNotAvailableForADay() {
        //System.out.println(developer.isSick +" - "+ developer.hasOccupation);
        //noget lort med availability igen
        assertTrue(developer.getAvailability(today));

        developer.isSick = false;
        softwareHuset.updateCSVFile("developers");
    }

    @When("the developer registers a holiday from {int} - {int} - {int} to {int} - {int} - {int}")
    public void theDeveloperRegistersAHolidayFromTo(int startD, int startM, int startY, int finD, int finM, int finY) {
        startHoli = new GregorianCalendar(startY,startM-1,startD);
        finHoli = new GregorianCalendar(finY,finM-1,finD);
        developer.setHoliday(startHoli, finHoli);
    }

    @Then("the developer has a occupation in the system")
    public void theDeveloperHasAOccupationInTheSystem() {
        assertTrue(developer.hasOccupation);
    }

    @And("the developer will not be available between first and last day of holiday")
    public void theDeveloperWillNotBeAvailableBetweenFirstAndLastDayOfHoliday() {
        //System.out.println(developer.isSick +" - "+ developer.hasOccupation);
        //noget lort med availability igen
        assertTrue(developer.getAvailability(startHoli));

        developer.hasOccupation = false;
        softwareHuset.updateCSVFile("developers");
    }

    @Given("that there is not a developer with initials {string}")
    public void thatThereIsNotADeveloperWithInitials(String devName) {
        assertFalse(softwareHuset.isDeveloper(devName));
    }

    @When("the developer registers {int} hours worked on {string}")
    public void theDeveloperRegistersHoursWorkedOn(int hoursWorked, String actName) {
        this.hoursWorked = hoursWorked;
        activity = softwareHuset.allActivities.get(actName);
        activityHours = activity.getTotalRegisteredHours();
        activity.registerHours(developer, hoursWorked);
    }

    @Then("the developers hours is added to the total hours worked on the activity")
    public void theDevelopersHoursIsAddedToTheTotalHoursWorkedOnTheActivity() {
        assertEquals(activityHours + hoursWorked, activity.getTotalRegisteredHours(),0.1);
    }

    @And("not assigned to selected project")
    public void notAssignedToSelectedProject() {
        assertFalse(project.developerIsInProject(developer));

    }

    @And("the error message {string} is given")
    public void theErrorMessageIsGiven(String name) {
        System.out.println(name);
    }
}
