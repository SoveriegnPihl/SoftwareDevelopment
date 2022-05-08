package dtu.employees.project;

import dtu.project.Project;
import dtu.softwarehus.SoftwareHuset;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;


import java.util.GregorianCalendar;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;

public class addProjectTest {
    SoftwareHuset sf;
    Project project;
    GregorianCalendar startDate, endDate;
    int budget;

    public addProjectTest(SoftwareHuset softwareHuset){
        sf = softwareHuset;
    }

    @Given("you want to add a new project starting {int} - {int} - {int} and ending {int} - {int} - {int} with {int} kr budget")
    public void youWantToAddANewProjectStartingAndEndingWithKrBudget(int startD, int startM, int startY, int endD, int endM, int endY, int budget) {
        startDate = new GregorianCalendar(startY, startM, startD);
        endDate = new GregorianCalendar(endY, endM,endD);
        sf.createProject(startDate,endDate,budget);
    }

    @And("the new project is an existing project")
    public void theNewProjectIsAnExistingProject() {

    }

    @And("the end date is before the start date")
    public void theEndDateIsBeforeTheStartDate() {
    }

    @Given("the end date is after the start date")
    public void the_end_date_is_after_the_start_date() {
        assertTrue(endDate.compareTo(startDate) == 1);
    }

    @Then("the new project is added")
    public void the_new_project_is_added() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
