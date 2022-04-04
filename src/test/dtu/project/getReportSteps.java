package dtu.project;

import dtu.employees.Developer;
import dtu.employees.Manager;
import dtu.softwarehus.SoftwareHuset;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class getReportSteps {
    SoftwareHuset softwareHuset;
    ArrayList<Project> projects;
    Developer manager;

    Project project;

    public getReportSteps(SoftwareHuset softwareHuset, HashMap<String, Developer> developers, ArrayList<Project> projects){
        this.softwareHuset = softwareHuset;
        this.projects = projects;
    }

    @Given("that there is a Developer with initials {string}")
    public void that_there_is_a_developer_with_initials(String managerInitials) throws Exception {
        manager = new Developer(managerInitials);
    }

    @When("the procject manager requests a report for the project")
    public void the_procject_manager_requests_a_report_for_the_project() throws Exception{
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the procject manager get's the project report")
    public void the_procject_manager_get_s_the_project_report() throws Exception {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @And("there is not a project with id {string}")
    public void thereIsNotAProjectWithId(String projectId) throws Exception{
        assertThat(softwareHuset.findProject(projectId),is(false));
    }
}
