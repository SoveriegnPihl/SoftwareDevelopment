package dtu.employees;

import dtu.project.Activity;
import dtu.project.Project;
import dtu.softwarehus.SoftwareHuset;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Developer {
    public String initials;
    int hoursWorked;
    List<Activity> activities = new ArrayList<>();
    public boolean isSick;
    boolean isProjectManager;
    public GregorianCalendar occupiedUntilThisDate, occupiedFromThisDate, sickFromThisDate, sickUntilThisDate;
    public GregorianCalendar today = new GregorianCalendar();
    public boolean hasOccupation;
    public boolean occupationRunout;
    double registeredHoursToday = 0;

    public Developer(String ini){
        initials = ini;
        hoursWorked = 0;
        isSick = false;
        isProjectManager = false;
        hasOccupation = false;
        occupationRunout = false;
    }

    public String getInitials(){
        return initials;
    }

    public String getAvailability(){
        String isAvailable = "Developer: " + initials + " is NOT occupied today" + "\n";
        String isNotAvailable = "Developer: " + initials + " is occupied today" + "\n";

        if(!hasOccupation){
            return isAvailable;
        }
        else if(!isSick){
            return isAvailable;
        }
        else{
            if (occupiedFromThisDate.compareTo(today) == -1 && occupiedUntilThisDate.compareTo(today) == 1){
                return isNotAvailable;
            }
            else if(sickFromThisDate.compareTo(today) == -1 && sickUntilThisDate.compareTo(today) == 1){
                return isNotAvailable;
            }
            else{
                return isAvailable;
            }
        }
    }

    public void addHoursToday(double hours){
        registeredHoursToday+=hours;
    }

    public double getRegisteredHoursToday(){
        return registeredHoursToday;
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
    /*
    public void setOccupied(boolean occupation){
        isOccupied = occupation;
    }

    public boolean isOccupied(){
        return isOccupied;
    }*/

    public void setToProjectManager(){isProjectManager = true;}

    public boolean isProjectManager() {return isProjectManager;}

    public String getOccDateDay (String time, String kind){
        if (time.equals("start")) {
            if (kind.equals("holiday")){
                return String.valueOf(occupiedFromThisDate.get(Calendar.DAY_OF_MONTH));
            } else {
                return String.valueOf(sickFromThisDate.get(Calendar.DAY_OF_MONTH));
            }

        } else {
            if (kind.equals("holiday")){
                return String.valueOf(occupiedUntilThisDate.get(Calendar.DAY_OF_MONTH));
            } else {
                return String.valueOf(sickUntilThisDate.get(Calendar.DAY_OF_MONTH));
            }
        }
    }

    public String getOccDateMonth (String time, String kind){
        if (time.equals("start")) {
            if (kind.equals("holiday")){
                return String.valueOf(occupiedFromThisDate.get(Calendar.MONTH));
            } else {
                return String.valueOf(sickFromThisDate.get(Calendar.MONTH));
            }

        } else {
            if (kind.equals("holiday")){
                return String.valueOf(occupiedUntilThisDate.get(Calendar.MONTH));
            } else {
                return String.valueOf(sickUntilThisDate.get(Calendar.MONTH));
            }
        }
    }

    public String getOccDateYear (String time, String kind){
        if (time.equals("start")) {
            if (kind.equals("holiday")){
                return String.valueOf(occupiedFromThisDate.get(Calendar.YEAR));
            } else {
                return String.valueOf(sickFromThisDate.get(Calendar.YEAR));
            }

        } else {
            if (kind.equals("holiday")){
                return String.valueOf(occupiedUntilThisDate.get(Calendar.YEAR));
            } else {
                return String.valueOf(sickUntilThisDate.get(Calendar.YEAR));
            }
        }
    }

    public void setHolidayDates(String[] dateInterval){
        if(dateInterval[1].equals("noOcc")){
            occupiedFromThisDate = new GregorianCalendar();
            occupiedUntilThisDate = new GregorianCalendar();
        }
        else {
            occupiedFromThisDate = new GregorianCalendar(Integer.parseInt(dateInterval[1]),Integer.parseInt(dateInterval[2]),
                    Integer.parseInt(dateInterval[3]));

            occupiedUntilThisDate = new GregorianCalendar(Integer.parseInt(dateInterval[4]),Integer.parseInt(dateInterval[5]),
                    Integer.parseInt(dateInterval[6]));

            if(occupiedUntilThisDate.compareTo(today) == -1){
                occupiedFromThisDate = new GregorianCalendar();
                occupiedUntilThisDate = new GregorianCalendar();
                occupationRunout = true;
                hasOccupation = false;
            }
            else {
                occupationRunout = false;
                hasOccupation = true;
            }
        }
    }

    public void setSickDates(String[] dateInterval){
        if(dateInterval[2].equals("noSick")){
            sickFromThisDate = new GregorianCalendar();
            sickUntilThisDate = new GregorianCalendar();
        }
        else if (hasOccupation && dateInterval[7].equals("noSick")){
            sickFromThisDate = new GregorianCalendar();
            sickUntilThisDate = new GregorianCalendar();
        }
        else if (occupationRunout && dateInterval[7].equals("noSick")){
            sickFromThisDate = new GregorianCalendar();
            sickUntilThisDate = new GregorianCalendar();
            occupationRunout = false;
        }
        else {
            if(hasOccupation){
                sickFromThisDate = new GregorianCalendar(Integer.parseInt(dateInterval[7]), Integer.parseInt(dateInterval[8]),
                        Integer.parseInt(dateInterval[9]));

                sickUntilThisDate = new GregorianCalendar(Integer.parseInt(dateInterval[10]), Integer.parseInt(dateInterval[11]),
                        Integer.parseInt(dateInterval[12]));
            }
            else{
                sickFromThisDate = new GregorianCalendar(Integer.parseInt(dateInterval[2]), Integer.parseInt(dateInterval[3]),
                        Integer.parseInt(dateInterval[4]));

                sickUntilThisDate = new GregorianCalendar(Integer.parseInt(dateInterval[5]), Integer.parseInt(dateInterval[6]),
                        Integer.parseInt(dateInterval[7]));
            }

            if (sickUntilThisDate.compareTo(today) == -1) {
                sickFromThisDate = new GregorianCalendar();
                sickUntilThisDate = new GregorianCalendar();
                isSick = false;
            }
            else {
                isSick = true;
            }
        }
    }

    public void setSick(){
        GregorianCalendar today = new GregorianCalendar();
        sickFromThisDate.setTime(Calendar.getInstance().getTime());
        sickUntilThisDate.set(Calendar.DAY_OF_MONTH, today.get(Calendar.DAY_OF_MONTH)+1);
        isSick = true;
        SoftwareHuset.updateCSVFile("developers");
    }

    public void setHoliday(GregorianCalendar startDate, GregorianCalendar endDate) {
        occupiedFromThisDate = startDate;
        occupiedUntilThisDate = endDate;
        hasOccupation = true;
        SoftwareHuset.updateCSVFile("developers");
    }
}
