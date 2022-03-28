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

public class assignPM {
    private SoftwareHuset softwareHuset;
    private developerInfo developer;
    private ErrorMessageHolder errorMessage;;


    public assignPM (SoftwareHuset softwareHuset, ErrorMessageHolder errorMessage, developerInfo developer) {
        this.softwareHuset=softwareHuset;
        this.errorMessage = errorMessage;
        this.developer = developer;
    }

    @Given("that there is a developer with initials {string}")
    public void that_there_is_a_developer_with_initials(String string) throws Exception {
    developer = new developerInfo(string);
        assertThat(developer.getInitials(),is(equalTo(string)));
    }

}
