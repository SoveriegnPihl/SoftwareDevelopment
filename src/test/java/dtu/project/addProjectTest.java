package dtu.project;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class addProjectTest {


    @Given("That there is a project name {string}")
    public void that_there_is_a_project_name(String name) {
        // Write code here that turns the phrase above into concrete actions
    }
    @And("That there is a start date {string}")
    public void that_there_is_a_start_date(String Sdate) {
        // Write code here that turns the phrase above into concrete actions
    }
    @And("There is an end date {string}")
    public void there_is_an_end_date(String Edate) {
        // Write code here that turns the phrase above into concrete actions
    }
    @And("There is a budget \"{int}\" kr")
    public void there_is_a_budget(int budget) {
        // Write code here that turns the phrase above into concrete actions
    }
    @Then("A new project with name {string} with start date {string} and end date {string} with a budget of \"{int}\"kr and id {string} is made")
    public void a_new_project_with_name_with_start_date_and_end_date_with_a_budget_of_kr_is_made(String string, String string2, String string3,
                                                                                                 Integer int1 , String string4) {
        // Write code here that turns the phrase above into concrete actions
    }
    @And("There is a project with name {string} with start date {string} and end date {string} with a budget of \"{int}\"kr and id {string}")
    public void there_is_a_project_with_name_with_start_date_and_end_date_with_a_budget_of_kr(String string, String string2, String string3,
                                                                                              Integer int1, String string4) {
        // Write code here that turns the phrase above into concrete actions
    }


}
