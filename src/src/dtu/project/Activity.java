package dtu.project;

import java.util.GregorianCalendar;
import java.util.HashMap;

//Lavet af Jakob Kildegaard

public class Activity {
    public String name;
    public HashMap<Developer, Double> registeredHours = new HashMap<>();
    int estimatedTime, projectAssignedTo;
    HashMap<Developer, int[]> developers;
    GregorianCalendar startDate, endDate;
    int budget;
    double totalRegisteredHours = 0;

    public Activity(String name, int time) {
        this.name = name;
        this.estimatedTime = time;
        developers = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setDateInterval(GregorianCalendar start, GregorianCalendar end) {
        startDate = start;
        endDate = end;
    }

    public void registerHours(Developer developer, double hours) {
        double hoursAtPre = totalRegisteredHours;
        assert (totalRegisteredHours + hours >= 0) : "Precondition:";
        // remove hours. Double cannot be checked as null
        developer.addHoursToday(hours);                                                 //1
        totalRegisteredHours += hours;                                                    //2
        if (registeredHours.containsKey(developer)) {                                     //3
            registeredHours.put(developer, registeredHours.get(developer) + hours);     //4
        } else {
            registeredHours.put(developer, hours);                                       //5
        }
        assert totalRegisteredHours == hoursAtPre + hours : "Postcondition";
        SoftwareHuset.updateCSVFile("activities");                                      //6
    }

    public double getTotalRegisteredHours() {
        return totalRegisteredHours;
    }

    public void setTotalRegisteredHours(double hours) {
        if (hours > -0.5) {
            totalRegisteredHours = hours;
        }
    }

    public String getProjectAssignedTo() {
        return String.valueOf(projectAssignedTo);
    }

    public void setProjectAssignedTo(int projectID) {
        this.projectAssignedTo = projectID;
    }

    public GregorianCalendar getStartDate() {
        return startDate;
    }

    public GregorianCalendar getEndDate() {
        return endDate;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

}
