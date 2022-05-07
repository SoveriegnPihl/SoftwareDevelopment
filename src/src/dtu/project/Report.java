package dtu.project;

import dtu.project.employees.Developer;

public class Report {
    Project project;
    String name;
    int startWeek;
    int endWeek;
    int budget;
    Developer projectManager;

    public Report(Project project){
        this.project = project;
    }

    public void getReport(){
        System.out.println();
    }





}
