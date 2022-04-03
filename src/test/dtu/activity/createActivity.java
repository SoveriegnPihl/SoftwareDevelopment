package dtu.project;

import dtu.Helper.ErrorMessageHolder;
import dtu.dto.developerInfo;
import dtu.employees.Developer;
import dtu.softwarehus.SoftwareHuset;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class createActivity {
    private SoftwareHuset softwareHuset;
    private developerInfo developer;
    private ErrorMessageHolder errorMessage;;
    private Project project;
    private EstimatedTime estimatedTime;


    public createActivity (Project project, String name, EstimatedTime estimatedTime) {
        this.project = there_is_a_project_named(String projectID);
        this.name = name;
        this.estimatedTime = estimatedTime;

    }

    @Given("that there is a Project manager or a developer with initials {String}")
    public void that_there_is_a_project_manager_or_developer(String initials) throws Exception {
        developer = new developerInfo(initials);
        assertThat(developer.getInitials(),is(equalTo(string)));
    }

    @And("there is a project named {string}")
    public void there_is_a_project_named(String projectID) throws Exception{
        this.projectID = projectID;
    }

    @When("they need a new activity named {string} with expected work hours {Integer} ")
    public void a_new_activity_with_name_and_with_expected_work_hours(String name, Integer estimatedTime) throws Exception{
        this.name = name;
        this.estimatedTime = estimatedTime;
    }

    @And("There is an activity with name {String}")
    public void there_is_an_activity_with_name_and_expected_work_hours(String name, Integer expHours) throws Exception{

    }


}