package dtu.employees.project;

import dtu.softwarehus.SoftwareHuset;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class checkForEmployeesTest {

    SoftwareHuset sf;

    public checkForEmployeesTest(SoftwareHuset softwareHuset){

        sf = softwareHuset;
        sf.startProgram();

    }

    @And("There is a project manager")
    public void there_is_a_project_manager() {
        // Write code here that turns the phrase above into concrete actions

    }
    @And("There is a list of employees")
    public void there_is_a_list_of_employees() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("A list of available employees is made")
    public void a_list_of_available_employees_is_made() {
        // Write code here that turns the phrase above into concrete actions

    }
    @And("There is a list with available employees")
    public void there_is_a_list_with_available_employees() {
        // Write code here that turns the phrase above into concrete actions
    }


}
