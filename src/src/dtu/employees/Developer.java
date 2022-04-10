package dtu.employees;

import dtu.project.Activity;
import dtu.project.Project;
import dtu.softwarehus.SoftwareHuset;
import io.cucumber.java.ca.Cal;

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
    public GregorianCalendar occupiedUntilThisDate;
    public GregorianCalendar occupiedFromThisDate;
    public GregorianCalendar today = new GregorianCalendar();
    public boolean hasOccupation;

    public Developer(String ini){
        initials = ini;
        hoursWorked = 0;
        isOccupied = false;
        isProjectManager = false;
        hasOccupation = false;
    }

    public String getInitials(){
        return initials;
    }

    public String getAvailability(){
        if(!hasOccupation){
            return "Developer: " + initials + " is NOT occupied today" + "\n";
        }else{
            if (occupiedFromThisDate.compareTo(today) == -1 && occupiedUntilThisDate.compareTo(today) == 1){
                return "Developer: " + initials + " is occupied today" + "\n";
            }else{
                return "Developer: " + initials + " is NOT occupied today" + "\n";
            }
        }
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

    public String getOccDateDay (String time){
        if (time.equals("start")) {
            return String.valueOf(occupiedFromThisDate.get(Calendar.DAY_OF_MONTH));
        } else {
            return String.valueOf(occupiedUntilThisDate.get(Calendar.DAY_OF_MONTH));
        }
    }

    public String getOccDateMonth (String time){
        if (time.equals("start")) {
            return String.valueOf(occupiedFromThisDate.get(Calendar.MONTH));

        } else {
            return String.valueOf(occupiedUntilThisDate.get(Calendar.MONTH));
        }
    }

    public String getOccDateYear (String time){
        if (time.equals("start")) {
            return String.valueOf(occupiedFromThisDate.get(Calendar.YEAR));
        } else {
            return String.valueOf(occupiedUntilThisDate.get(Calendar.YEAR));
        }
    }

    public void setOccupationDates(String[] dateInterval){
        if(dateInterval[1].equals("noOcc")){
            occupiedFromThisDate = new GregorianCalendar();
            occupiedUntilThisDate = new GregorianCalendar();
        }
        else {
            GregorianCalendar startDate = new GregorianCalendar(Integer.parseInt(dateInterval[1]),Integer.parseInt(dateInterval[2]),
                    Integer.parseInt(dateInterval[3]));

            GregorianCalendar endDate = new GregorianCalendar(Integer.parseInt(dateInterval[4]),Integer.parseInt(dateInterval[5]),
                    Integer.parseInt(dateInterval[6]));

            //GregorianCalendar today = new GregorianCalendar();

            if(endDate.compareTo(today) == -1){
                occupiedFromThisDate = new GregorianCalendar();
                occupiedUntilThisDate = new GregorianCalendar();
                hasOccupation = false;
            }
            else {
                occupiedFromThisDate = startDate;
                occupiedUntilThisDate = endDate;
                hasOccupation = true;
            }
        }
    }

    public void setSick(){
        GregorianCalendar today = new GregorianCalendar();
        occupiedFromThisDate.setTime(Calendar.getInstance().getTime());
        occupiedUntilThisDate.set(Calendar.DAY_OF_MONTH, today.get(Calendar.DAY_OF_MONTH)+1);
    }

    public void setHoliday(GregorianCalendar startDate, GregorianCalendar endDate) {
        occupiedFromThisDate = startDate;
        occupiedUntilThisDate = endDate;
        hasOccupation = true;
        SoftwareHuset.updateCSVFile("developers");
    }
}
