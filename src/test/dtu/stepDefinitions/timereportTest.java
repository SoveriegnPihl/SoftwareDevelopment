package dtu.stepDefinitions;
import dtu.Helper.ErrorMessageHolder;
import dtu.project.Activity;
import dtu.project.Developer;
import dtu.project.SoftwareHuset;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class timereportTest {
    SoftwareHuset softwareHuset;

    Developer developer,developer2;
    Activity activity;
    int addedHours;

    public timereportTest(SoftwareHuset sf) {
        softwareHuset = sf;
    }

    @Given("that there is a developer with initials {string}")
    public void thatThereIsADeveloperWithInitials(String string) {
        assertTrue(softwareHuset.developers.containsKey(string));
    }


    @And("there is registered hours")
    public void thereIsRegisteredHours() {
        activity = SoftwareHuset.allActivities.get("Test solutions");
        developer = SoftwareHuset.developers.get("vic7");
        addedHours = 5;
        activity.registerHours(developer,addedHours);
       assertThat(developer.getRegisteredHoursToday()!=0,is(true));
    }

    @When("the developer requests reported worked hours for today")
    public void theDeveloperRequestsReportedWorkedHoursForToday(){

        developer.getRegisteredHoursToday();
    }

    @Then("daily worked hours is given for {string}")
    public void dailyWorkedHoursIsGivenFor(String name) {
    assertEquals(softwareHuset.getDeveloper(name).getRegisteredHoursToday(),addedHours,0.1);
    }


    @And("there is no registered time")
    public void thereIsNoRegisteredTime() {
        developer2 = softwareHuset.developers.get("ekki");
        assertEquals(developer2.getRegisteredHoursToday(),0,0.1);
    }

    @Given("that there isn't a developer with initials {string}")
    public void thatThereIsnTADeveloperWithInitials(String name) {
        assertFalse(softwareHuset.developers.containsKey(name));
    }

    @When("{string} requests reported worked hours for today")
    public void requestsReportedWorkedHoursForToday(String name) {
        assertFalse(softwareHuset.isDeveloper(name));
    }

    @When("the other developer requests reported worked hours for today")
    public void theOtherDeveloperRequestsReportedWorkedHoursForToday() {
        developer2.getRegisteredHoursToday();
    }

    @Then("hours is not reported for {string}")
    public void hoursIsNotReportedFor(String name) {
        assertFalse(softwareHuset.isDeveloper(name));
    }

    @Then("zero hours is given for {string}")
    public void zeroHoursIsGivenFor(String name) {
        assertEquals(softwareHuset.developers.get(name).getRegisteredHoursToday(),0,0.1);
    }
}
