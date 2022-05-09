package dtu.stepDefinitions;

import dtu.project.SoftwareHuset;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

//Lavet af Thomas

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

    public addDeveloperSteps(SoftwareHuset softwareHuset) {
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
        newDeveloperName = devName;
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

    @And("the developer has a holiday starting {int} - {int} - {int} and finishing {int} - {int} - {int}")
    public void theDeveloperHasAHolidayStartingAndFinishing(int startD, int startM, int startY, int endD, int endM, int endY) {
        softwareHuset.addDeveloper(new String[]{newDeveloperName, String.valueOf(startY), String.valueOf(startM), String.valueOf(startD), String.valueOf(endY), String.valueOf(endM), String.valueOf(endD), "noSick"});
    }

    @Then("the developer dont have a occupation")
    public void theDeveloperDontHaveAOccupation() {
        assertFalse(softwareHuset.developers.get(newDeveloperName).hasOccupation);
        softwareHuset.developers.remove("thom");
        softwareHuset.updateCSVFile("developers");
    }

    @Then("the developer has a occupation")
    public void theDeveloperHasAOccupation() {
        assertTrue(softwareHuset.developers.get(newDeveloperName).hasOccupation);
        softwareHuset.developers.remove("thom");
        softwareHuset.updateCSVFile("developers");
    }
}
