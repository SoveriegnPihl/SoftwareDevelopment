package dtu.project;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class ActivityTest {


    @Test
    void registerHoursA() {
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
