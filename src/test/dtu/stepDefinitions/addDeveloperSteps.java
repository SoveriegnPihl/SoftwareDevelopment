package dtu.stepDefinitions;

import dtu.project.SoftwareHuset;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

public class addDeveloperSteps {
    SoftwareHuset softwareHuset;
    String newDeveloperName;

    @BeforeAll
    public static void checkInit() {
        if (!AAcheckForEmployeesTest.programStarted) {
            AAcheckForEmployeesTest.programStarted = true;
            SoftwareHuset.startProgram();
        }
    }

    public addDeveloperSteps(SoftwareHuset softwareHuset){
        this.softwareHuset = softwareHuset;
    }

    @Given("that {string} is not a developer")
    public void that_is_not_a_developer(String devName) {
        newDeveloperName = devName;
        assertFalse(softwareHuset.isDeveloper(devName));
    }

    @Given("that the initials isn't greater than {int}")
    public void that_the_initials_isn_t_greater_than(Integer limit) {
        assertTrue(newDeveloperName.length() <= limit);
    }

    @Then("the new developer gets registered")
    public void the_new_developer_gets_registered() {
        softwareHuset.addDeveloper(new String[]{newDeveloperName, "noOcc", "noSick"});
        assertTrue(softwareHuset.isDeveloper(newDeveloperName));

        softwareHuset.developers.remove(newDeveloperName);
        softwareHuset.updateCSVFile("developers");
    }

    @And("that the initials is greater than {int}")
    public void thatTheInitialsIsGreaterThan(int limit) {
        assertFalse(newDeveloperName.length() <= limit);
    }

    @Given("that {string} is a developer")
    public void thatIsADeveloper(String devName) {
        assertTrue(softwareHuset.isDeveloper(devName));
    }

    @Then("{string} is not created")
    public void isNotCreated(String name) {
        assertFalse(SoftwareHuset.isDeveloper(name));
    }

    @Then("{string} is still an employee")
    public void isStillAnEmployee(String name) {
        assertTrue(SoftwareHuset.isDeveloper(name));
    }
}
