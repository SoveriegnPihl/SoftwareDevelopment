package dtu.project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class listAvailableDevelopers {
    public GregorianCalendar today = new GregorianCalendar();
    Developer developer;

    @BeforeEach
    void checkInit(){
        if(!AvailabilityTest.programStarted){
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
        developer.isSick = true;
        developer.setSick();
        assertTrue(developer.getAvailability(today));
        developer.isSick = false;
        SoftwareHuset.updateCSVFile("developers");
    }
}