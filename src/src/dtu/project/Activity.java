package dtu.project;

import dtu.employees.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Activity {
    int estimatedTime;
    Developer developer;
    HashMap<Developer,int[]> developers = new HashMap<>();
    public String name;

    public Activity (String name, int time){
        this.name = name;
        this.estimatedTime = time;
    }
    public void addDev(Developer dev, int start,int end){
        int[] act= {start, end};
        developers.put(dev,act);
    }

    public String getActivity(){
        return name;
    }
    public void setEstimatedTime(int time){
        estimatedTime = time;
    }


}
