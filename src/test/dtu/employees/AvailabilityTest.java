package dtu.employees;

import dtu.softwarehus.SoftwareHuset;
import io.cucumber.java.hu.De;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AvailabilityTest {

    @Test
    void getAvailabilityA() {
        SoftwareHuset.startProgram();
        Developer developer = SoftwareHuset.getDeveloper("ekki");
        assertEquals(developer.getAvailability(), "Developer: " + developer.getInitials() + " is NOT occupied today" + "\n");
    }

    @Test
    void getAvailabilityB() {
        Developer developer = SoftwareHuset.getDeveloper("ekki");
        developer = SoftwareHuset.getDeveloper("ekki");
        developer.getAvailability();
    }

    @Test
    void getAvailabilityC() {
        Developer developer = SoftwareHuset.getDeveloper("ekki");
        developer.setSick();
        assertEquals(developer.getAvailability(), "Developer: " + developer.getInitials() + " is occupied today" + "\n");
    }

    @Test
    void getAvailabilityD() {
        Developer developer = SoftwareHuset.getDeveloper("ekki");
        developer = SoftwareHuset.getDeveloper("ekki");
        developer.getAvailability();
    }
}