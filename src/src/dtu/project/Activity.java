package dtu.project;

import dtu.employees.*;

import java.util.ArrayList;

public class Activity {
    int estimatedTime;
    ArrayList<Developer> developers;
    String name;
    int projectID;

    public Activity (String projectName,String name, int estimatedTime) {
        this.estimatedTime = estimatedTime;
        this.name = name;
        this.projectName = projectName;
        projectId = (project.nextId++) + 22000;


    }


    public void printActivity(){
        System.out.println("Project name: " + projectName + " project id: " + projectId + " activity name: " + name + " estimated time: " + estimatedTime);
    }

}
