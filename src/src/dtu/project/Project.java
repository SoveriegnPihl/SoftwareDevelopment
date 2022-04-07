package dtu.project;

import dtu.employees.*;
import dtu.softwarehus.SoftwareHuset;
import io.cucumber.java.en_old.Ac;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

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
    public int startWeek,endWeek, budget,id;
    private List<Developer> developers = new ArrayList<>();
    public HashMap<Activity, int[]> activities = new HashMap<>() ;

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
    public  ArrayList<Activity> userActivities(Developer user){
        ArrayList<Activity> developerList = new ArrayList<>();
        if(developerIsInProject(user)) {
            for (Activity activity : activities.keySet()) {
                if (activity.developers.containsKey(user)) {
                    developerList.add(activity);
                }
            }

        }
        return developerList;
    }
    public double getReportedTimeForActivity(Activity activity)  {
        double reportedTime = 0;
        for (TimeRegistration t : activity.getTimeRegistrations()) {
            reportedTime += t.getAmountOfTime();
        }
        return reportedTime;
    }

    public void addActivity(Activity activity, int startW, int endW, int budget1, int estimatedTime){
        int[] act = {startW, endW, budget1, estimatedTime};

        System.out.println(startWeek+" "+endWeek+" "+budget+" "+id);
        System.out.println(act[0]+" > " +startWeek+" "+act[1]+" < "+endWeek+" "+act[2]+" < "+ this.budget);
        if(act[0] >= startWeek && act[1] <= endWeek && act[2] <= this.budget){
            this.budget -= budget1;
            activities.put(activity,act);
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

    public  List<Developer> developerList(){
        return developers;
    }
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
