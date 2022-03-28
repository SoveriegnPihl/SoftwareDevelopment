package dtu.employees;

import dtu.project.Activity;
import dtu.project.Project;

public class Developer {
    String initials;
    Activity[] activities = new Activity[20];
    boolean isOccupied = false;

    public Developer(String ini) {
        initials = ini;
    }

    public void printDeveloper() {
        System.out.println("Initials: " + initials + " is occupied?: " + isOccupied);
    }

    public void requestDailyHours() {

    }

    public void registerActivity(Project project, Activity activity, String time) {

    }

    public void registerPersonalActivity(String time, String time2) {

    }

    public void requestAssistance(Activity activity) {

    }

    public void setOccupied() {
        isOccupied = true;
    }

    public void setUnOccupied() {
        isOccupied = false;
    }

    public boolean isOccupied() {
        return isOccupied;
    }
}
