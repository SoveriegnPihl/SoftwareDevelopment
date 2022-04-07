package dtu.project;

import dtu.employees.*;
import dtu.softwarehus.SoftwareHuset;
import io.cucumber.java.en_old.Ac;

import java.util.ArrayList;
import java.util.HashMap;

public class Project {
    static int nextId = 1;

    public String name;
    int id;
    public static int startWeek;
    public static int endWeek;
    public int budget;
    Manager pm;

    ArrayList<Developer> developers = new ArrayList<Developer>();
   // ArrayList<Activity> activities;
    public static HashMap<Activity, int[]> activities = new HashMap<>() ;

    public Project(int sW, int eW, int b){
        startWeek = sW;
        endWeek = eW;
        budget = b;
        id = (Project.nextId++) + 22000;
    }

    public void listDevelopers(){

    }

    public int getId(){
        return id;
    }



    public void addActivity(Activity activity, int startW, int endW, int budget, int estimatedTime){
        int[] act = {startW, endW, budget, estimatedTime};
        if(act[0] >= startWeek && act[1] <= endWeek && budget < this.budget){
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
        developers.add(dev);
    }

    public boolean developerIsInProject(Developer dev){ return developers.contains(dev); }

    public void printProject(){
        System.out.println("Project id: " + id + " start week: " + startWeek + " endweek: " + endWeek + " budget " + budget);
    }
}
