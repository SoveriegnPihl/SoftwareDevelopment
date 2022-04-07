package dtu.project;

import dtu.employees.*;
import dtu.softwarehus.SoftwareHuset;
import io.cucumber.java.en_old.Ac;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class Project {
    static int nextId = 1;
    public String name;
    int id;
    public static GregorianCalendar startDate;
    public static GregorianCalendar endDate;
    public int budget;
    Manager pm;

    ArrayList<Developer> developers;
    public static HashMap<String, Activity> activities;

    public Project(GregorianCalendar start, GregorianCalendar end, int budget){
        startDate = start;
        endDate = end;
        this.budget = budget;
        id = (Project.nextId++) + 22000;
        developers = new ArrayList<>();
        activities = new HashMap<>();
    }

    public int getId(){
        return id;
    }

    public void addActivity(Activity activity){
        if(activity.getStartDate().compareTo(startDate) == 1 && activity.getEndDate().compareTo(endDate) == -1){
            this.budget -= activity.getBudget();
            activities.put(activity.getName(), activity);
        } else {
            System.out.println("ikke inden for datoen af projektet");
        }
    }

   /* public boolean findActivity(String activityName){
        for (Activity a : activities){
            if(a.name.equals(activityName)){
                return true;
            }
        }
        return false;
    }*/

    public void addDeveloper(Developer dev){
        developers.add(dev);
    }

    public boolean developerIsInProject(Developer dev){ return developers.contains(dev); }

    public int getDateDay(String time){
        if(time.equals("start")){
            return startDate.get(Calendar.DAY_OF_MONTH);
        }
        else{
            return endDate.get(Calendar.DAY_OF_MONTH);
        }
    }

    public int getDateMonth(String time){
        if(time.equals("start")){
            return startDate.get(Calendar.MONTH);

        }
        else{
            return endDate.get(Calendar.MONTH);
        }
    }

    public int getDateYear(String time){
        if(time.equals("start")){
            return startDate.get(Calendar.YEAR);
        }
        else{
            return endDate.get(Calendar.YEAR);
        }
    }

    public void printProject(){
        System.out.println("Project id: " + id + " start date: " + startDate.getTime() + " end date: " + endDate.getTime() + " budget " + budget);
    }
}
