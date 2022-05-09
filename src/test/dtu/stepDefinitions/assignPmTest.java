package dtu.stepDefinitions;

import dtu.project.SoftwareHuset;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class assignPmTest {

    SoftwareHuset softwareHuset;
    int notAProject;

    public assignPmTest(SoftwareHuset sf) {
        this.softwareHuset = sf;

    }

    @BeforeAll
    public static void checkInit() {
        if (!AAcheckForEmployeesTest.programStarted) {
            AAcheckForEmployeesTest.programStarted = true;
            SoftwareHuset.startProgram();
        }
    }

    @When("the developer assigns {string} to {string}")
    public void theDeveloperAssignsTo(String name, String project) {
        SoftwareHuset.assignPM(name, Integer.parseInt(project));
    }

    @Then("the project manager {string} is assigned to the project")
    public void theProjectManagerIsAssignedToTheProject(String name) {

        assertTrue(SoftwareHuset.isManager(name));
    }

    @Then("{string} is not assigned as project manager to the project")
    public void isNotAssignedAsProjectManagerToTheProject(String name) {

        assertFalse(SoftwareHuset.isManager(name));
    }

    @And("There is not a project with id {string}")
    public void thereIsNotAProjectWithId(String projectID) {

        notAProject = Integer.parseInt(projectID);
    }

    @When("the developer tries to assigns the project manager with initials {string}")
    public void theDeveloperTriesToAssignsTheProjectManagerWithInitials(String name) {

        SoftwareHuset.assignPM(name, notAProject);
    }

    @Then("no project manager assigned to the project")
    public void noProjectManagerAssignedToTheProject() {

        assertFalse(SoftwareHuset.projectManagers.containsKey(notAProject));
    }

    @Then("{string} is still project manager")
    public void isStillProjectManager(String name) {

        assertTrue(SoftwareHuset.isManager(name));
    }

    @Given("There is a project with id {string}")
    public void there_is_a_project_with_id(String string) {
        assertTrue(true);
    }

}