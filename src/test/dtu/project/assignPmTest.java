package dtu.project;

import dtu.Helper.ErrorMessageHolder;
import dtu.dto.developerInfo;
import dtu.softwarehus.SoftwareHuset;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class assignPmTest {
    private SoftwareHuset softwareHuset = new SoftwareHuset();
    private Project project;
    private developerInfo developer;
    private ErrorMessageHolder errorMessage;;


    public assignPmTest() {
        softwareHuset.startProgram();
    }

    @Given("that there is a developer with initials {string}")
    public void that_there_is_a_developer_with_initials(String name) throws Exception {
        softwareHuset.addDeveloper1(name);
        assertThat(softwareHuset.developers.containsKey(name), is(true));
    }

    @And("there is a project named {string}")
    public void thereIsAProjectNamed(String name) {
        project = new Project(name,1,1,1);
        assertThat(project.name,is(equalTo((name))));
    }

    @When("the developer assigns the project manager with initials {string}")
    public void theDeveloperAssignsTheProjectManagerWithInitials(String arg0) {

    }

    @Then("the project manager {string} is assigned to the project")
    public void theProjectManagerIsAssignedToTheProject(String name) {
        softwareHuset.addPm(name);
    }

    @Given("that there isn't a developer with initials {string}")
    public void thatThereIsnTADeveloperWithInitials(String string) {

    }
}
