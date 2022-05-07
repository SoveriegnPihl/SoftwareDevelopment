package dtu.project;

import dtu.employees.Developer;
import io.cucumber.java.bs.A;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActivityTest {
    Activity activity = new Activity("testActivity",50);
    Developer developer1 = new Developer("ekki");

    Developer developer2 = new Developer("hans");


    @Test
    void registerHoursA() {
        double hoursBefore = developer1.getRegisteredHoursToday();
        activity.registerHours(developer1,5);
        Assert.assertEquals(developer1.getRegisteredHoursToday(), hoursBefore +5,0.1 );
    }
    @Test
    void registerHoursB() {
        double hoursBefore = developer1.getRegisteredHoursToday();
        activity.registerHours(developer1,5);
        Assert.assertEquals(developer1.getRegisteredHoursToday(), hoursBefore +5,0.1 );
    }
}
