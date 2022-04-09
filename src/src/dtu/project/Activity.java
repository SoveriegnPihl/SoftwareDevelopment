package dtu.project;

import dtu.employees.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

public class Activity {
    int estimatedTime;
    HashMap<Developer,int[]> developers= new HashMap<>();
    public String name;
    GregorianCalendar startDate, endDate;
    int budget;
    List<TimeRegistration> timeRegistrations = new ArrayList<>();

    public Activity (String name, int time){
        this.name = name;
        this.estimatedTime = time;
    }
    public void addDev(Developer dev, int start,int end){
        int[] act= {start, end};
        developers.put(dev,act);
    }
    public ArrayList<Developer> getDevelopers(){
        return new ArrayList<>(developers.keySet());
    }

    public String getName(){
        return name;
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

    public GregorianCalendar getStartDate(){return startDate;}

    public GregorianCalendar getEndDate(){return endDate;}

    public int getBudget() {return budget;}


    public void setEstimatedTime(int time){
        estimatedTime = time;
    }

    public void registerTime(TimeRegistration timeRegistration) {
        timeRegistrations.add(timeRegistration);
    }

    public List<TimeRegistration> getTimeRegistrations() {
        return timeRegistrations;
    }

    public TimeRegistration getTimeRegistrationForEmployeeOnDate(Developer developer, GregorianCalendar date) {
        for (TimeRegistration t : this.timeRegistrations) {
            if (t.match(developer, date)) {
                return t;
            }
        }
        return null;
    }

}
