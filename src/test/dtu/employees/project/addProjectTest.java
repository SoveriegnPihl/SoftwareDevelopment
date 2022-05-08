package dtu.employees.project;

import dtu.softwarehus.SoftwareHuset;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;


import static org.junit.Assert.assertTrue;

public class addProjectTest {
    SoftwareHuset sf;
    public addProjectTest(SoftwareHuset softwareHuset){
        sf = softwareHuset;
    }


    @Given("That there is a project name {string}")
    public void that_there_is_a_project_name(String name) {
        assertTrue(!(name.isEmpty()));
    }
    @And("That there is a start date {string}")
    public void that_there_is_a_start_date(String Sdate) {
        assertTrue(true);
    }
    @And("There is an end date {string}")
    public void there_is_an_end_date(String Edate) {
        // Write code here that turns the phrase above into concrete actions
    }
    @And("There is a budget \"{int}\" kr")
    public void there_is_a_budget(int budget) {
        assertTrue( budget > 0);
    }
    @Then("A new project with name {string} with start date {string} and end date {string} with a budget of \"{int}\"kr and id \"{int}\" is made")
    public void a_new_project_with_name_with_start_date_and_end_date_with_a_budget_of_kr_is_made(String string, String string2, String string3,
                                                                                                 Integer int1 , Integer int2) {
        // Write code here that turns the phrase above into concrete actions
    }
    @And("There is a project with id \"{int}\"")
    public void there_is_a_project_with_name_with_start_date_and_end_date_with_a_budget_of_kr(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("the error {string} is given")
    public void the_error_is_given(String string) {
    }

    @Then("The date is not valid")
    public void the_date_is_not_valid() {
        // Write code here that turns the phrase above into concrete actions
    }


}
