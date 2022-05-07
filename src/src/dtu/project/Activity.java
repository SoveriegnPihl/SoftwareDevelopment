package dtu.project;

import dtu.employees.*;
import dtu.softwarehus.SoftwareHuset;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

public class Activity {
    int estimatedTime, projectAssignedTo;
    HashMap<Developer,int[]> developers;
    public String name;
    GregorianCalendar startDate, endDate;
    int budget;
    List<TimeRegistration> timeRegistrations = new ArrayList<>();
    public HashMap<Developer, Double> registeredHours = new HashMap<>();
    double totalRegisteredHours = 0;

    public Activity (String name, int time){
        this.name = name;
        this.estimatedTime = time;
        developers = new HashMap<>();
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
    }
    public void registerHours(Developer developer, double hours){
        developer.addHoursToday(hours);
        totalRegisteredHours+=hours;
        if(registeredHours.containsKey(developer)){
            registeredHours.put(developer, registeredHours.get(developer) + hours);
        } else {
            registeredHours.put(developer,hours);
        }
        SoftwareHuset.updateCSVFile("activities");
    }
    public double getTotalRegisteredHours(){
        return totalRegisteredHours;
    }

    public void setTotalRegisteredHours(double hours){
        if(hours > -0.5) {
            totalRegisteredHours = hours;
        }
    }
    public double getRegisteredHours(){
        double amountOfRegisteredHours = 0;
        for (double i : registeredHours.values()) {
            amountOfRegisteredHours += i;
        }
    return amountOfRegisteredHours;
    }

    public void setProjectAssignedTo(int projectID){this.projectAssignedTo = projectID;}

    public String getProjectAssignedTo(){return String.valueOf(projectAssignedTo);}

    public void setBudget(int budget){
        this.budget = budget;
    }

    public GregorianCalendar getStartDate(){return startDate;}

    public GregorianCalendar getEndDate(){return endDate;}

    public int getBudget() {return budget;}

    public void setEstimatedTime(int time){
        estimatedTime = time;
    }

    public int getEstimatedTime(){return estimatedTime;}

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
