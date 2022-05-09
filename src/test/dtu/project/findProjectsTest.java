package dtu.project;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Lavet af Marcus

public class findProjectsTest {

    @BeforeEach
    void checkInit() {
        if (!AvailabilityTest.programStarted) {
            AvailabilityTest.programStarted = true;
            SoftwareHuset.startProgram();
        }
    }

    @Test
    public void findProjectA() {
        Assert.assertTrue(SoftwareHuset.findProject(22001));
    }

    @Test
    public void findProjectB() {
        Assert.assertFalse(SoftwareHuset.findProject(21001));
    }

    @Test
    public void preCondition() {
        Assert.assertFalse(SoftwareHuset.projects.isEmpty());
    }

    @Test
    public void postCondition() {
        Assert.assertTrue((SoftwareHuset.findProject(22001)));
    }
}
