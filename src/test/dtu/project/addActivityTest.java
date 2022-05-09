package dtu.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

public class addActivityTest {

    @BeforeEach
    void checkInit(){
        if(!AvailabilityTest.programStarted){
            AvailabilityTest.programStarted = true;
            SoftwareHuset.startProgram();
        }
    }
    @Test
    void addActivityA() {
        Activity activity = new Activity("clean up", 5);
        activity.setBudget(100);
        GregorianCalendar calS = new GregorianCalendar(2022,0,3);
        GregorianCalendar calF = new GregorianCalendar(2022,0,7);
        activity.setDateInterval(calS, calF);
        Project project = SoftwareHuset.projects.get(22001);
        project.addActivity(activity);
        assertTrue(project.findActivity(activity.getName()));
        project.activities.remove("clean up");
    }

    @Test
    void addActivityB() {
        Activity activity = new Activity("clean up", 5);
        activity.setBudget(100);
        GregorianCalendar calS = new GregorianCalendar(2022,0,1);
        GregorianCalendar calF = new GregorianCalendar(2022,0,20);
        activity.setDateInterval(calS, calF);
        Project project = SoftwareHuset.projects.get(22001);
        project.addActivity(activity);
        assertFalse(project.findActivity(activity.getName()));
    }

}
