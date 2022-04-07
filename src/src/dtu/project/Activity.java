package dtu.project;

import dtu.employees.*;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class Activity {
    int estimatedTime;
    Developer developer;
    HashMap<Developer,int[]> developers;
    public String name;
    GregorianCalendar startDate, endDate;
    int budget;

    public Activity (String name, int time){
        this.name = name;
        this.estimatedTime = time;
        developers = new HashMap<>();

    }
    public void addDev(Developer dev, int start,int end){
        int[] act= {start, end};
        developers.put(dev,act);
    }

    public void setDateInterval(GregorianCalendar start, GregorianCalendar end){
        startDate = start;
        endDate = end;
        System.out.println(startDate.compareTo(endDate));
        System.out.println(endDate.compareTo(startDate));
    }

    public void setBudget(int budget){
        this.budget = budget;
    }

    public String getName(){
        return name;
    }

    public GregorianCalendar getStartDate(){return startDate;}

    public GregorianCalendar getEndDate(){return endDate;}

    public int getBudget() {return budget;}

    public void setEstimatedTime(int time){
        estimatedTime = time;
    }


}
