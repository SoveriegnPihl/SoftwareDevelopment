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

public class bookingSteps {
    SoftwareHuset softwareHuset;
    HashMap<String, Developer> developers;
    ArrayList<Project> projects;
    Developer developer, manager;

    Project project;

    public bookingSteps(SoftwareHuset softwareHuset, HashMap<String, Developer> developers, ArrayList<Project> projects){
        this.softwareHuset = softwareHuset;
        this.developers = developers;
        this.projects = projects;
    }

    @Given("that there is a manager and a Developer with initials {string} and {string}")
    public void that_there_is_a_manager_and_a_developer_with_initials(String manageInitials, String devInitials) throws Exception {
       manager = new Developer(manageInitials);
       developer = new Developer(devInitials);

       developers.put(manageInitials, manager);
       developers.put(devInitials, developer);
    }

    @Given("that the developer is a procject manager")
    public void that_the_developer_is_a_procject_manager() throws Exception {
        manager.setToProjectManager();
    }

    @Then("The developer is a project manager")
    public void the_developer_is_a_project_manager() throws Exception {
        assertThat(manager.isProjectManager(),is(true));
    }

    @Given("there is a project with id {string}")
    public void there_is_a_project_with_id(String projectId) throws Exception{
        project = new Project(projectId, 1, 2, 4);
    }

    @Given("the developer is available for the project")
    public void the_developer_is_available_for_the_project() {
        assertThat(developer.isOccupied(),is(false));
    }

    @When("the procject manager books the developer")
    public void the_procject_manager_books_the_developer() throws Exception {
        project.addDeveloper(developer);
    }

    @Then("the developer is added to the project")
    public void the_developer_is_added_to_the_project()  throws Exception {
        assertThat(project.developerIsInProject(developer),is(true));
    }

    @Given("the developer is not available")
    public void the_developer_is_not_available() throws Exception {
        developer.setOccupied(true);
    }

    @And("the error message {string} is given")
    public void the_error_message_is_given(String errorMsg) {
        System.out.println(errorMsg);
    }

    @Given("that the developer is not a procject manager")
    public void that_the_developer_is_not_a_procject_manager() {
        assertThat(manager.isProjectManager(),is(false));
    }


    @Then("the developer is not available for the project")
    public void theDeveloperIsNotAvailableForTheProject() {
        assertThat(developer.isOccupied(),is(true));
    }
}
