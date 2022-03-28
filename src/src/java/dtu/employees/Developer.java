package dtu.employees;

import dtu.project.Activity;
import dtu.project.Project;

public class Developer {
    String initials;
    Activity[] activities = new Activity[20];
    boolean isOccupied = false;


    public Developer (String initials) {
        this.initials=initials;
    }

    void requestDailyHours(){

    }

    void registerActivity(Project project, Activity activity, String time){

    }

    void registerPersonalActivity(String time, String time2){

    }

    void requestAssistance(Activity activity){

    }

    public String getInitials() {
        return initials;
    }

}
