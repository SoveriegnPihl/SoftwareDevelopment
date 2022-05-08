package dtu.stepDefinitions;

import dtu.Helper.ErrorMessageHolder;
import dtu.project.Developer;
import dtu.project.SoftwareHuset;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class assignPmTest {

    @BeforeAll
    public static void checkInit() {
        if (!AAcheckForEmployeesTest.programStarted) {
            AAcheckForEmployeesTest.programStarted = true;
            SoftwareHuset.startProgram();
        }
    }

    SoftwareHuset softwareHuset;
    int notAProject;


    public assignPmTest(SoftwareHuset sf){
            this.softwareHuset = sf;

        }
    @When("the developer assigns {string} to {string}")
    public void theDeveloperAssignsTo(String name, String project) {
        softwareHuset.assignPM(name, Integer.parseInt(project));
    }

    @Then("the project manager {string} is assigned to the project")
    public void theProjectManagerIsAssignedToTheProject(String name) {

        assertTrue(softwareHuset.isManager(name));
    }

    @Then("{string} is not assigned as project manager to the project")
    public void isNotAssignedAsProjectManagerToTheProject(String name) {

        assertFalse(softwareHuset.isManager(name));
    }

    @And("There is not a project with id {string}")
    public void thereIsNotAProjectWithId(String projectID) {

        notAProject = Integer.parseInt(projectID);
    }

    @When("the developer tries to assigns the project manager with initials {string}")
    public void theDeveloperTriesToAssignsTheProjectManagerWithInitials(String name) {

        softwareHuset.assignPM(name, notAProject);
    }

    @Then("no project manager assigned to the project")
    public void noProjectManagerAssignedToTheProject() {

        assertFalse(softwareHuset.projectManagers.containsKey(notAProject));
    }

    @Then("{string} is still project manager")
    public void isStillProjectManager(String name) {

        assertTrue(softwareHuset.isManager(name));
    }
    @Given("There is a project with id {string}")
    public void there_is_a_project_with_id(String string) {
        assertTrue(true);
    }

}