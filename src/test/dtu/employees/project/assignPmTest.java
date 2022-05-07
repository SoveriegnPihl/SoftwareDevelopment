package dtu.employees.project;

import dtu.Helper.ErrorMessageHolder;
import dtu.project.employees.Developer;
import dtu.softwarehus.SoftwareHuset;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class assignPmTest {

    private ErrorMessageHolder errorMessageHolder;
    SoftwareHuset softwareHuset;
    HashMap<String, Developer> developers;
    Developer developer,manager;
    int notAProject;


    public assignPmTest(SoftwareHuset sf,ErrorMessageHolder errorMessageHolder){
            this.softwareHuset = sf;
            this.errorMessageHolder = errorMessageHolder;

        }
    @When("the developer assigns the project manager with initials {string}")
    public void theDeveloperAssignsTheProjectManagerWithInitials(String name) {
        softwareHuset.assignPM(name,22001);
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


}