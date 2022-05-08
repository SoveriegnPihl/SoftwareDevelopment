package dtu.employees.project;

import dtu.softwarehus.SoftwareHuset;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

public class addDeveloperSteps {
    SoftwareHuset softwareHuset;
    String newDeveloperName;

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

    @Then("the new devloper get's registered")
    public void the_new_devloper_get_s_registered() {
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
}
