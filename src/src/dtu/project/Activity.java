package dtu.project;

import dtu.employees.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Activity {
    int estimatedTime;
    Developer developer;
    HashMap<String, Developer> developers;
    String name;

    public Activity (String name, int tid){
        this.name = name;
        this.estimatedTime = tid;
    }

    public String getActivity(){
        return name;
    }
    public void setEstimatedTime(int tid){
        estimatedTime = tid;
    }


}
