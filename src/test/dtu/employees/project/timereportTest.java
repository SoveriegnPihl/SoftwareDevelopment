package dtu.employees.project;
import dtu.Helper.ErrorMessageHolder;
import dtu.dto.developerInfo;
import dtu.employees.Developer;
import dtu.project.Activity;
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

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class timereportTest {
    private ErrorMessageHolder errorMessage;
    ;
    SoftwareHuset softwareHuset = new SoftwareHuset();

    Developer developer,developer2;
    Project project;
    Activity activity;
    int addedHours;
    boolean isStarted = false;

    public timereportTest() {
        softwareHuset.startProgram();
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
    assertEquals(softwareHuset.developers.get(name).getRegisteredHoursToday(),addedHours,0.1);
    }


    @And("there is no registered time")
    public void thereIsNoRegisteredTime() {
        developer2 = softwareHuset.developers.get("ekki");
        assertEquals(developer2.getRegisteredHoursToday(),0,0.1);
    }



    @Then("Then the error message {string} is given")
    public void thenTheErrorMessageIsGiven(String arg0) {
        System.out.println("No hours registered today");
    }


    @And("there is registered hours for the date {string}")
    public void thereIsRegisteredHoursForTheDate(String arg0) {
        assertTrue(activity.registeredHours.containsKey(developer.getInitials()));
    }

    @Given("that there isn't a developer with initials {string}")
    public void thatThereIsnTADeveloperWithInitials(String name) {
        assertFalse(softwareHuset.developers.containsKey(name));
    }

    @Then("the error message {string} is given")
    public void theErrorMessageIsGiven(String arg0) {
    }

    @When("the non-developer requests reported worked hours for today")
    public void theNonDeveloperRequestsReportedWorkedHoursForToday() {
    }

    @When("the other developer requests reported worked hours for today")
    public void theOtherDeveloperRequestsReportedWorkedHoursForToday() {
        developer2.getRegisteredHoursToday();
    }
}
