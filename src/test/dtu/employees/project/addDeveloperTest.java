package dtu.employees.project;

import dtu.Helper.ErrorMessageHolder;
import dtu.employees.Developer;
import dtu.softwarehus.SoftwareHuset;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class addDeveloperTest {

    private ErrorMessageHolder errorMessageHolder;
    SoftwareHuset softwareHuset;
    HashMap<String, Developer> developers;
    Developer developer,manager;
    int notAProject;


    public addDeveloperTest(SoftwareHuset sf,ErrorMessageHolder errorMessageHolder){
        this.softwareHuset = sf;
        this.errorMessageHolder = errorMessageHolder;

    }
    @When("someone tries to create a developer {string}")
    public void someoneTriesToCreateADeveloper(String name) {
        SoftwareHuset.addDeveloper(new String[]{name, "noOcc", "noSick"});

    }

    @Then("the developer {string} does not exist")
    public void theDeveloperDoesNotExist(String name) {
        assertFalse(SoftwareHuset.isDeveloper(name));
    }

    @And("the developer {string} exists")
    public void theDeveloperExists(String name) {
        assertTrue(SoftwareHuset.isDeveloper(name));
    }

    @Then("the developer {string} is not created")
    public void theDeveloperIsNotCreated(String name) {
        assertFalse(SoftwareHuset.isDeveloper(name));
    }


}
