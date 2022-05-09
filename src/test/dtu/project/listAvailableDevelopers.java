package dtu.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertTrue;

//Lavet af Victor Eyde

public class listAvailableDevelopers {
    public GregorianCalendar today = new GregorianCalendar();

    @BeforeEach
    void checkInit() {
        if (!AvailabilityTest.programStarted) {
            AvailabilityTest.programStarted = true;
            SoftwareHuset.startProgram();
        }
    }

    @Test
    void listAvailableDevelopersA() {
        Developer developer = SoftwareHuset.getDeveloper("ekki");
        assertTrue(developer.getAvailability(today));
    }

    @Test
    void listAvailableDevelopersB() {
        Developer developer = SoftwareHuset.getDeveloper("ekki");
        developer.setSick();
        assertTrue(developer.getAvailability(today));
        developer.isSick = false;
        SoftwareHuset.updateCSVFile("developers");
    }
}