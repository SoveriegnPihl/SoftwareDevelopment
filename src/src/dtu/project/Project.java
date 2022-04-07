package dtu.project;

import dtu.employees.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Project {
    static int nextId = 1;
    public String name;
    public int startWeek,endWeek, budget,id;
    private List<Developer> developers = new ArrayList<>();
    public HashMap<Activity, int[]> activities = new HashMap<>() ;

    public Project(int sW, int eW, int b){
        startWeek = sW;
        endWeek = eW;
        budget = b;
        id = (Project.nextId++) + 22000;
    }

    public int getId(){
        return id;
    }

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
        if(act[0] >= startWeek && act[1] <= endWeek && act[2] < this.budget){
            this.budget -= budget;
            activities.put(activity,act);
        } else {
            System.out.println("add act virker ik");
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
        System.out.println("tilføjer dev til "+this.getId());
        developers.add(dev);
    }

    public boolean developerIsInProject(Developer dev){ return developers.contains(dev); }

    public  List<Developer> developerList(){
        return developers;
    }
    public void printProject(){
        System.out.println("Project id: " + id + " start week: " + startWeek + " endweek: " + endWeek + " budget " + budget);
    }
}
