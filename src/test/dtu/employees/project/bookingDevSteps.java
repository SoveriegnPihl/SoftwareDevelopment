package dtu.employees.project;

import dtu.employees.Developer;
import dtu.project.Activity;
import dtu.project.Project;
import dtu.softwarehus.SoftwareHuset;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class bookingDevSteps {
    SoftwareHuset softwareHuset;
    HashMap<String, Developer> developers;
    ArrayList<Project> projects;
    Developer developer, manager;
    Activity activity;
    Project project;

    public bookingDevSteps(SoftwareHuset softwareHuset, HashMap<String, Developer> developers, ArrayList<Project> projects){
        this.softwareHuset = softwareHuset;
        this.developers = developers;
        this.projects = projects;
    }

    @Given("that there is a manager and a Developer with initials {string} and {string}")
    public void that_there_is_a_manager_and_a_developer_with_initials(String manageInitials, String devInitials) throws Exception {
       manager = new Developer(manageInitials);
       developer = new Developer(devInitials);

       developers.put(manageInitials, manager);
       developers.put(devInitials, developer);
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
        GregorianCalendar calS = new GregorianCalendar(2022,0,1);
        GregorianCalendar calF = new GregorianCalendar(2023,0,14);
        project = new Project(calS, calF, 700);
    }

    @Given("the developer is available for the project")
    public void the_developer_is_available_for_the_project() {
        assertTrue(developer.getAvailability());
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
        developer.setSick();
        assertFalse(developer.getAvailability());
    }

    @Given("that the developer is not a project manager")
    public void that_the_developer_is_not_a_project_manager() {
        assertThat(manager.isProjectManager(),is(false));
    }


    @When("the developer wants to add a activity {string} with {int} hours estimated")
    public void the_developer_wants_to_add_a_activity_with_hours_estimated(String activityName, Integer estHours) throws Exception {
        activity = new Activity(activityName, estHours);
    }

    @And("the activity is not already in the project")
    public void the_activity_is_not_already_in_the_project() {
     //   assertThat(project.findActivity(activity.name),is(false));
    }

    @And("the activity is already in the project")
   public void the_activity_is_already_in_the_project() {
       // assertThat(project.findActivity(activity.name),is(true));
    }

    @Then("the activity is added to the given project")
    public void the_activity_is_added_to_the_given_project() {
       // project.addActivity(activity);
       // assertThat(project.findActivity(activity.name),is(true));
    }

    @Given("there is not a project named {string}")
    public void there_is_not_a_project_named(String projectName) {
        assertThat(softwareHuset.findProject(projectName),is(false));
    }

}
