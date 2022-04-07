package dtu.project;

import dtu.employees.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

public class Activity {
    int estimatedTime;
    Developer developer;
    HashMap<Developer,int[]> developers = new HashMap<>();
    public String name;
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

    public String getActivity(){
        return name;
    }

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
