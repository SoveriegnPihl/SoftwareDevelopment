package dtu.employees.whiteBoxTest;

import dtu.employees.Developer;
import dtu.softwarehus.SoftwareHuset;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class listAvailableDevelopers {

    public GregorianCalendar today = new GregorianCalendar();

    ArrayList<Developer> Developer = new ArrayList<Developer>();

    @Test
    void listAvailableDevelopersA() {
        SoftwareHuset.startProgram();
        Developer developer = SoftwareHuset.getDeveloper("ekki");
        assertTrue(developer.getAvailability(today));
       Developer.add(developer);

    }

    @Test
    void listAvailableDevelopersB() {
        Developer developer = SoftwareHuset.getDeveloper("ekki");
        assertFalse(developer.getAvailability(today));
        Developer.remove(developer);

    }
}
