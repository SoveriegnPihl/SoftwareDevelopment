package dtu.project;

import dtu.employees.Developer;
import dtu.employees.Manager;

import java.util.ArrayList;

public class Project {
    int startWeek;
    int endWeek;
    int budget;
    ArrayList<Developer> developers;
    static int id;
    ArrayList<Activity> activities;
    Manager pm;


    public Project(){

    }

    void listDevelopers(){

    }

    Report createReport(){
        return new Report();
    }

    void addDeveloper(Developer dev){
        developers.add(dev);
    }
}
