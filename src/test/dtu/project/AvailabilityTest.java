package dtu.project;
/*
import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class AvailabilityTest {
    public GregorianCalendar today = new GregorianCalendar();

    @Test
    void getAvailabilityA() {
        SoftwareHuset.startProgram();
        Developer developer = SoftwareHuset.getDeveloper("ekki");
        assertTrue(developer.getAvailability(today));
    }

    @Test
    void getAvailabilityB() {
        Developer developer = SoftwareHuset.getDeveloper("ekki");
        GregorianCalendar calS = new GregorianCalendar(2022,0,1);
        GregorianCalendar calF = new GregorianCalendar(2023,0,1);
        developer.setHoliday(calS,calF);
        assertFalse(developer.getAvailability(today));
    }

    @Test
    void getAvailabilityC() {
        Developer developer = SoftwareHuset.getDeveloper("ekki");
        developer.setSick();
        assertFalse(developer.getAvailability(today));
    }

    @Test
    void getAvailabilityD() {
        Developer developer = SoftwareHuset.getDeveloper("ekki");
        assertFalse(developer.getAvailability(today));
        developer.isSick = false;
        developer.hasOccupation = false;
        SoftwareHuset.updateCSVFile("developers");
    }
} */