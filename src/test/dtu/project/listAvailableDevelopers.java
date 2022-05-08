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
        developer = SoftwareHuset.getDeveloper("vicc");
        assertTrue(developer.getAvailability(today));


    }

    @Test
    void listAvailableDevelopersB() {
        developer = SoftwareHuset.getDeveloper("vicc");
        System.out.println(developer.getInitials());
        developer.setSick();
        assertFalse(developer.getAvailability(today));
    }
}