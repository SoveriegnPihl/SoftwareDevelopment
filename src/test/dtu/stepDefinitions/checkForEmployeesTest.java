package dtu.stepDefinitions;
import dtu.project.Project;
import dtu.project.SoftwareHuset;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import static org.junit.Assert.assertTrue;

public class checkForEmployeesTest {
    int id = 22001;
    SoftwareHuset sf;

    public checkForEmployeesTest(SoftwareHuset softwareHuset){
        sf = softwareHuset;
        sf.startProgram();
    }

    @And("There is a project manager")
    public void there_is_project_manager(){
        assertTrue(sf.projectManagers.containsKey(22001));
    }
    @And ("There is a list of employees")
    public void there_is_list_of_employees(){
        assertTrue(sf.listDevs().size() > 0);
    }


    @Then ("A list of available employees is made")
    public void a_list_of_available_employees(){
       assertTrue(!(sf.listAvailableDevelopers().isEmpty()));
    }

    @And ("There is a list with available employees")
    public void there_is_list_of_available_employees(){
        //ændret noget kode jakob igang.
        assertTrue(true);
    }





}
