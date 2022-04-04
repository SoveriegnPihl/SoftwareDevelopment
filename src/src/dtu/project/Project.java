package dtu.project;

import dtu.employees.*;
import dtu.softwarehus.SoftwareHuset;

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

    public Project(String name, int startW, int endW, int budget){
        this.name = name;
        startWeek = startW;
        endWeek = endW;
        this.budget = budget;
        id = (Project.nextId++) + 22000;
    }

    public void listDevelopers(){

    }

    public void addDeveloper(Developer dev){
        developers.add(dev);
    }

    public boolean developerIsInProject(Developer dev){ return developers.contains(dev); }

    public void printProject(){
        System.out.println("Project name: " + name + " project id: " + id + " start week: " + startWeek + " endweek: " + endWeek + " budget " + budget);
    }
}
