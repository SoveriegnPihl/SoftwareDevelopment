/*package dtu.project;
import dtu.Helper.ErrorMessageHolder;
import dtu.dto.developerInfo;
import dtu.employees.Developer;
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
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class timereportTest {
    private ErrorMessageHolder errorMessage;
    ;
    SoftwareHuset softwareHuset;
    HashMap<String, Developer> developers;
    Developer developer, manager;

    Project project;
    Developer user;


    public timereportTest(SoftwareHuset softwareHuset, HashMap<String, Developer> developers) {
        softwareHuset.startProgram();
        this.softwareHuset = softwareHuset;
        this.developers = developers;
    }

    @And("there is registered hours")
    public void thereIsRegisteredHours() {
        user = softwareHuset.getDeveloper(name);
       assertThat(user.getHours()!=0,is(true));
    }

    @When("the developer requests reported worked hours for today")
    public void theDeveloperRequestsReportedWorkedHoursForToday() {
        user.getHours();
    }

    @Then("daily worked hours is given for {string}")
    public void dailyWorkedHoursIsGivenFor(String name) {

      assertThat(user.getHours()>0,is(true));
    }


    @And("there is no registered time")
    public void thereIsNoRegisteredTime() {
    }


    @And("the hours registered is zero")
    public void theHoursRegisteredIsZero() {

    }

    @Then("Then the error message {string} is given")
    public void thenTheErrorMessageIsGiven(String arg0) {
    }


}*/
