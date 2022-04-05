package dtu.project;

import dtu.employees.*;
import dtu.softwarehus.SoftwareHuset;
import io.cucumber.java.en_old.Ac;

import java.util.ArrayList;

public class Project {
    static int nextId = 1;

    public String name;
    int id;
    int startWeek;
    int endWeek;
    int budget;
    Manager pm;

    ArrayList<Developer> developers = new ArrayList<Developer>();
    ArrayList<Activity> activities;

    public Project(String n, int sW, int eW, int b){
        name = n;
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

    Report createReport(){
        return new Report();
    }

    public void addActivity(Activity activity){
        activities.add(activity);
    }

    public boolean findActivity(String activityName){
        for (Activity a : activities){
            if(a.name.equals(activityName)){
                return true;
            }
        }
        return false;
    }

    public void addDeveloper(Developer dev){
        developers.add(dev);
    }

    public boolean developerIsInProject(Developer dev){ return developers.contains(dev); }

    public void printProject(){
        System.out.println("Project name: " + name + " project id: " + id + " start week: " + startWeek + " endweek: " + endWeek + " budget " + budget);
    }
}
