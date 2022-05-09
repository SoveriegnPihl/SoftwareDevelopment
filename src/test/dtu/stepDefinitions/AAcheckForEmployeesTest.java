package dtu.stepDefinitions;

import dtu.project.SoftwareHuset;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import static org.junit.Assert.assertTrue;

public class AAcheckForEmployeesTest {
    static SoftwareHuset sf;
    static boolean programStarted = false;
    int id = 22001;

    public AAcheckForEmployeesTest(SoftwareHuset softwareHuset) {
        sf = softwareHuset;

    }

    @BeforeAll
    public static void checkInit() {
        System.out.println("checked");
        if (!programStarted) {
            programStarted = true;
            SoftwareHuset.startProgram();
        }
    }

    @And("There is a project manager")
    public void there_is_project_manager() {
        assertTrue(SoftwareHuset.projectManagers.containsKey(22001));
    }

    @And("There is a list of employees")
    public void there_is_list_of_employees() {
        assertTrue(sf.listDevs().size() > 0);
    }


    @Then("A list of available employees is made")
    public void a_list_of_available_employees() {
        SoftwareHuset.listAvailableDevelopers();
    }

    @And("There is a list with available employees")
    public void there_is_list_of_available_employees() {
        assertTrue(!(SoftwareHuset.listAvailableDevelopers().isEmpty()));
    }


}
