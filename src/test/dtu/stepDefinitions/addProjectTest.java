package dtu.stepDefinitions;

import dtu.project.Project;
import dtu.project.SoftwareHuset;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.AfterClass;
import org.junit.jupiter.api.BeforeEach;


import java.util.GregorianCalendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class addProjectTest {
    SoftwareHuset sf;
    Project thisProject;
    static int projectID;
    GregorianCalendar startDate, endDate;
    int budget, budgetBefore,budgetToAdd;

    @BeforeAll
    public static void checkInit() {
        if (!AAcheckForEmployeesTest.programStarted) {
            AAcheckForEmployeesTest.programStarted = true;
            SoftwareHuset.startProgram();
        }
    }
    @AfterAll
    public static void deleteProject() {
        SoftwareHuset.projects.remove(projectID);
        SoftwareHuset.updateCSVFile("projects");
    }

    public addProjectTest(SoftwareHuset softwareHuset){
        sf = softwareHuset;
    }

    @Given("you want to add a new project starting {int} - {int} - {int} and ending {int} - {int} - {int} with {int} kr budget")
    public void youWantToAddANewProjectStartingAndEndingWithKrBudget(int startD, int startM, int startY, int endD, int endM, int endY, int budget) {
        startDate = new GregorianCalendar(startY, startM, startD);
        endDate = new GregorianCalendar(endY, endM,endD);
        this.budget = budget;

    }

    @And("the end date is before the start date")
    public void theEndDateIsBeforeTheStartDate() {
        assertFalse(endDate.compareTo(startDate) == -1);
    }

    @Given("the end date is after the start date")
    public void the_end_date_is_after_the_start_date() {
        assertTrue(endDate.compareTo(startDate) == 1);
    }

    @Then("the new project is added")
    public void the_new_project_is_added() {
        projectID = sf.createProject(startDate,endDate,budget);
    }

    @And("the new project is an existing project with a budget")
    public void theNewProjectIsAnExistingProject() {
    sf.projects.get(projectID).getBudget();
    }

    @Given("that a project with id {string} exits")
    public void thatAProjectWithIdExits(String name) {
        assertTrue(SoftwareHuset.projects.containsKey(Integer.parseInt(name)));
        thisProject = SoftwareHuset.projects.get(Integer.parseInt(name));
    }
    @And("a project manager want to set new date and budget for the project")
    public void aProjectManagerWantToSetNewDateAndBudgetForTheProject() {
        budgetBefore = thisProject.getBudget();
        budgetToAdd = 500;
        int[] newStartDate = {Integer.parseInt(thisProject.getDateDay("start")), Integer.parseInt(thisProject.getDateMonth("start")), Integer.parseInt(thisProject.getDateYear("start"))};
        int[] newEndDate = {Integer.parseInt(thisProject.getDateDay("end")), Integer.parseInt(thisProject.getDateMonth("end")), Integer.parseInt(thisProject.getDateYear("end"))};
        int newBudget = budgetBefore + budgetToAdd;
        thisProject.setNewDateAndBudget(newStartDate, newEndDate, newBudget);
        SoftwareHuset.updateCSVFile("projects");

    }

    @Then("date and budget is updated for the project")
    public void dateAndBudgetIsUpdatedForTheProject() {
        assertEquals(budgetBefore + budgetToAdd + thisProject.budgetUsed, thisProject.getBudget());
        thisProject.budget = budgetBefore;
        SoftwareHuset.updateCSVFile("projects");
    }


}
