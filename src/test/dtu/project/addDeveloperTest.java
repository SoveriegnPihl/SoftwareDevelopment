package dtu.project;


import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class addDeveloperTest {


    @BeforeEach
    void checkInit() {
        if (!AvailabilityTest.programStarted) {
            AvailabilityTest.programStarted = true;
            SoftwareHuset.startProgram();
        }
    }

    @Test
    public void addDeveloperTestA() {
        Developer ekki = SoftwareHuset.developers.get("ekki");
        SoftwareHuset.addDeveloper(new String[]{ekki.getInitials(), "noOcc", "noSick"});
    }

    @Test
    public void addDeveloperTestB() {
        Developer developer = new Developer("lmao");
        SoftwareHuset.addDeveloper(new String[]{developer.getInitials(), "noOcc", "noSick"});

    }

    @Test
    public void addDeveloperTestC() {
        Developer developer = new Developer("lmao");
        SoftwareHuset.addDeveloper(new String[]{developer.getInitials(), "occ", "noSick", "2022", "10", "10", "2022", "11", "11"});
    }


    @Test
    public void addDeveloperTestD() {
        Developer developer = new Developer("lmao");
        SoftwareHuset.addDeveloper(new String[]{developer.getInitials(), "noOcc", "10", "2022",
                "10", "10", "2022", "11", "11", "2022", "10", "10", "2022", "11", "11"});
    }

    @Test
    public void preCondition() {
        String ini = "rofl";
        Assert.assertTrue(ini.length() == 4);
        Assertions.assertFalse(SoftwareHuset.developers.containsKey(ini));
    }

    @Test
    public void postCondition() {
        Developer rofl = new Developer("rofl");
        SoftwareHuset.developers.put(rofl.getInitials(), rofl);
        Assert.assertTrue(SoftwareHuset.developers.containsKey(rofl.getInitials()));
        SoftwareHuset.developers.remove(rofl.getInitials());
    }


}
