package dtu.project;

import dtu.employees.Developer;
import dtu.softwarehus.SoftwareHuset;
import io.cucumber.java.bs.A;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActivityTest {


    @Test
    void registerHoursA() {
        SoftwareHuset.startProgram();
        Activity activity = SoftwareHuset.allActivities.get("Test solutions");
        Developer vic7 = SoftwareHuset.developers.get("vic7");
        activity.registerHours(vic7,5);
        double hoursBefore = vic7.getRegisteredHoursToday();
        activity.registerHours(vic7,5);
        Assert.assertEquals(vic7.getRegisteredHoursToday(), hoursBefore +5,0.1 );
    }

    @Test
    void registerHoursB() {
        Activity activity = SoftwareHuset.allActivities.get("Prepare project");
        Developer ekki = SoftwareHuset.developers.get("ekki");
        double hoursAdded=5;
        activity.registerHours(ekki,hoursAdded);
        Assert.assertEquals(ekki.getRegisteredHoursToday(), hoursAdded,0.1 );
    }

}
