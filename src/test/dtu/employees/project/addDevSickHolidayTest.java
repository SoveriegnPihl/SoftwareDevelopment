package dtu.employees.project;

import dtu.Helper.ErrorMessageHolder;
import dtu.employees.Developer;
import dtu.softwarehus.SoftwareHuset;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashMap;

public class addDevSickHolidayTest {
    private ErrorMessageHolder errorMessageHolder;
    SoftwareHuset softwareHuset;
    HashMap<String, Developer> developers;
    Developer developer,manager;
    int notAProject;


    public addDevSickHolidayTest(SoftwareHuset sf,ErrorMessageHolder errorMessageHolder){
        this.softwareHuset = sf;
        this.errorMessageHolder = errorMessageHolder;

    }
    @When("the developer {string} registers a sick-day")
    public void theDeveloperRegistersASickDay(String arg0) {
    }


    @Then("developer {string} is not available for any projects for the next {int} hours")
    public void developerIsNotAvailableForAnyProjectsForTheNextHours(String arg0, int arg1) {

    }

    @When("the developer {string} registers a holiday from {int}\\/{int}\\/{int} to {int}\\/{int}\\/{int}")
    public void theDeveloperRegistersAHolidayFromTo(String arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6) {
    }

    @Then("the developer {string} will be unavailable for work")
    public void theDeveloperWillBeUnavailableForWork(String arg0) {
    }

    @And("the developer {string} will not be available for any projects between {int}\\/{int}\\/{int} and {int}\\/{int}\\/{int}")
    public void theDeveloperWillNotBeAvailableForAnyProjectsBetweenAnd(String arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6) {

    }

    @And("the developer {string} already has {int} weeks of holiday registered")
    public void theDeveloperAlreadyHasWeeksOfHolidayRegistered(String arg0, int arg1) {

    }

    @Then("a message {string} is displayed")
    public void aMessageIsDisplayed(String arg0) {

    }

    @And("the developer {string}'s status in the system will be that he is not on holiday")
    public void theDeveloperSStatusInTheSystemWillBeThatHeIsNotOnHoliday(String arg0) {
    }

    @And("the developer {string} will still be available for projects")
    public void theDeveloperWillStillBeAvailableForProjects(String arg0) {

    }

}
