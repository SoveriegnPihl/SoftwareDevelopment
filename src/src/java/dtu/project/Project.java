package dtu.project;

import dtu.employees.Developer;
import dtu.employees.Manager;

import java.util.ArrayList;

public class Project {
    static int nextId = 1;

    int startWeek;
    int endWeek;
    int budget;
    String name;
    ArrayList<Developer> developers;
    int id;
    ArrayList<Activity> activities;
    Manager pm;


    public Project(String n, int sW, int eW, int b){
        startWeek = sW;
        endWeek = eW;
        budget = b;
        name = n;
        id = (Project.nextId++) + 22000;
    }

    public void listDevelopers(){

    }

    Report createReport(){
        return new Report();
    }

    public void addDeveloper(Developer dev){
        developers.add(dev);
    }

    public void printProject(){
        System.out.println("Project name: " + name + " project id: " + id + " start week: " + startWeek + " endweek: " + endWeek + " budget " + budget);
    }
}
