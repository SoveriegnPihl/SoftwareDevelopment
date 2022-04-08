package dtu.employees;

import dtu.project.Activity;
import dtu.project.Project;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Developer {
    public String initials;
    int hoursWorked;
    List<Activity> activities = new ArrayList<>();
    boolean isOccupied;
    boolean isProjectManager;
    GregorianCalendar occupiedUntilThisDate = new GregorianCalendar();
    GregorianCalendar occupiedFromThisDate = new GregorianCalendar();

    public Developer(String ini){
        initials = ini;
        hoursWorked=0;
        isOccupied = false;
        isProjectManager = false;
    }

    public  String getInitials(){
        return initials;
    }

    public String printDeveloper(){
        return "Initials: " + initials + " is occupied?: " + isOccupied +"\n" ;
    }
    public void addHours(int hours){
        hoursWorked+=hours;
    }


    public int getHours(){ return hoursWorked; }

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

    public void setSick(){
        GregorianCalendar calendar = new GregorianCalendar();
        occupiedFromThisDate.setTime(Calendar.getInstance().getTime());
        occupiedUntilThisDate.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+1);
    }

    public void setHoliday(GregorianCalendar startDate, GregorianCalendar endDate) {
        occupiedFromThisDate = startDate;
        occupiedUntilThisDate = endDate;
    }
}
