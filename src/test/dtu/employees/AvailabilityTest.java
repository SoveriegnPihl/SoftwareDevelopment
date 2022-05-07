package dtu.employees;

import dtu.project.Project;
import dtu.softwarehus.SoftwareHuset;
import io.cucumber.java.hu.De;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class AvailabilityTest {

    @Test
    void getAvailabilityA() {
        SoftwareHuset.startProgram();
        Developer developer = SoftwareHuset.getDeveloper("ekki");
        assertTrue(developer.getAvailability());
    }

    @Test
    void getAvailabilityB() {
        Developer developer = SoftwareHuset.getDeveloper("ekki");
        GregorianCalendar calS = new GregorianCalendar(2022,0,1);
        GregorianCalendar calF = new GregorianCalendar(2023,0,1);
        developer.setHoliday(calS,calF);
        assertFalse(developer.getAvailability());
    }

    @Test
    void getAvailabilityC() {
        Developer developer = SoftwareHuset.getDeveloper("ekki");
        developer.setSick();
        assertFalse(developer.getAvailability());
    }

    @Test
    void getAvailabilityD() {
        Developer developer = SoftwareHuset.getDeveloper("ekki");
        assertFalse(developer.getAvailability());
        developer.isSick = false;
        developer.hasOccupation = false;
        SoftwareHuset.updateCSVFile("developers");
    }
}