package dtu.project;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class createActivityTest {

    @Given("that there is a Project manager or a developer with initials {String}")
    public void that_there_is_a_project_manager_or_developer(String initials) {
    }
    @And("there is a project named {string}")
    public void there_is_a_project_named(String name) throws Exception{
        throw new PendingException();
    }

    @When("they need a new activity named {string} with expected work hours {int} ")
    public void a_new_activity_with_name_and_with_expected_work_hours(String name, Integer expHours) throws Exception{
        throw new PendingException();
    }

    @And("There is a activity with name {String}")
    public void there_is_an_activity_with_name_and_expected_work_hours(String name, Integer expHours) throws Exception{
        throw new PendingException();
    }




}
