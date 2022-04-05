package dtu.project;

import dtu.employees.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Activity {
    int estimatedTime;
    Developer developer;
    HashMap<String, Developer> developers;
    public String name;

    public Activity (String name, int time){
        this.name = name;
        this.estimatedTime = time;
    }

    public String getActivity(){
        return name;
    }
    public void setEstimatedTime(int time){
        estimatedTime = time;
    }


}
