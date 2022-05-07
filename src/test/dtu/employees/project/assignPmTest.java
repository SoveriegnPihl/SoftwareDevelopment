package dtu.employees.project;

import dtu.Helper.ErrorMessageHolder;
import dtu.employees.Developer;
import dtu.project.Project;
import dtu.softwarehus.SoftwareHuset;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.hu.De;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;

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



    @And("There is not a project with id {string}")
    public void thereIsNotAProjectWithId(String projectID) {
        notAProject = Integer.parseInt(projectID);
    }

    @When("the developer tries to assigns the project manager with initials {string}")
    public void theDeveloperTriesToAssignsTheProjectManagerWithInitials(String name) {
        try {
            softwareHuset.assignPM(name, notAProject);
        }
        catch (NullPointerException e) {
            errorMessageHolder.setErrorMessage(e.getMessage());

        }
        System.out.println(errorMessageHolder.getErrorMessage());
    }
    @And("the error message {string}")
    public void theErrorMessage(String name) {

    }
}