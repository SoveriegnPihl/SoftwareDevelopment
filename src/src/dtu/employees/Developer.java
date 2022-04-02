package dtu.employees;

import dtu.project.Activity;
import dtu.project.Project;

public class Developer {
    String initials;
    Activity[] activities = new Activity[20];
    boolean isOccupied;
    boolean isProjectManager;

    public Developer(String ini){
        initials = ini;
        isOccupied = false;
        isProjectManager = false;
    }

    public  String getInitials(){
        return initials;
    }

    public String printDeveloper(){
        return "Initials: " + initials + " is occupied?: " + isOccupied +"\n" ;
    }

    public void requestDailyHours(){

    }

    public void registerActivity(Project project, Activity activity, String time){

    }

    public void registerPersonalActivity(String time, String time2){

    }

    public void requestAssistance(Activity activity){

    }

    public void setOccupied(boolean occupation){
        isOccupied = occupation;
    }

    public boolean isOccupied(){
        return isOccupied;
    }

    public void setToProjectManager(){isProjectManager = true;}

    public boolean isProjectManager() {return isProjectManager;}

}
