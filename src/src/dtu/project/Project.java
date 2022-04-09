package dtu.project;

import dtu.employees.*;
import dtu.softwarehus.SoftwareHuset;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

public class Project {
    static int nextId = 1;
    public String name;
    int id;
    public GregorianCalendar startDate;
    public GregorianCalendar endDate;
    public int budget, budgetUsed;
    Manager pm;


    private List<Developer> developers = new ArrayList<>();
    // public HashMap<Activity, int[]> activities = new HashMap<>();
    public HashMap<String, Activity> activities = new HashMap<>();
    public Project(GregorianCalendar start, GregorianCalendar end, int budget) {
        startDate = start;
        endDate = end;
        this.budget = budget;
        budgetUsed = 0;
        id = (Project.nextId++) + 22000;
    }

    public int getId() {
        return id;
    }

    public int getBudget(){return budget;}

    public void addActivity(Activity activity){
        if(activity.getStartDate().compareTo(startDate) == 1 && activity.getEndDate().compareTo(endDate) == -1){
            budgetUsed -= activity.getBudget();
            budget += budgetUsed;
            activities.put(activity.getName(), activity);
            System.out.println("Activity added");
        } else {
            System.out.println("ikke inden for datoen af projektet");
        }
    }
            public ArrayList<Activity> userActivities (Developer user){
                System.out.println(user.getInitials());
                ArrayList<Activity> developerList = new ArrayList<>();
                System.out.println(developerIsInProject(user)+" bruger i projekt");

                if (developerIsInProject(user)) {
                    for (Activity activity : activities.values()) {
                        System.out.println(activity.name+ " act navn");
                        System.out.println(activity.getDevelopers().toString());
                        if (activity.developers.containsKey(user)) {
                            System.out.println("YEEES contains key");
                            developerList.add(activity);
                        }
                    }

        }
        return developerList;
    }
    public double getReportedTimeForActivity (Activity activity){
        double reportedTime = 0;
        for (TimeRegistration t : activity.getTimeRegistrations()) {
            reportedTime += t.getAmountOfTime();
        }
        return reportedTime;
    }


   /* public boolean findActivity(String activityName){
        for (Activity a : activities){
            if(a.name.equals(activityName)){
                return true;
            }
        }
        return false;
    }*/

    public void addDeveloper (Developer dev){
        developers.add(dev);
    }

    public boolean developerIsInProject (Developer dev){
        return developers.contains(dev);
    }

    public List<Developer> developerList () {
        return developers;
    }
    public int getDateDay (String time){
        if (time.equals("start")) {
            return startDate.get(Calendar.DAY_OF_MONTH);
        } else {
            return endDate.get(Calendar.DAY_OF_MONTH);
        }
    }

    public int getDateMonth (String time){
        if (time.equals("start")) {
            return startDate.get(Calendar.MONTH);

        } else {
            return endDate.get(Calendar.MONTH);
        }
    }

    public int getDateYear (String time){
        if (time.equals("start")) {
            return startDate.get(Calendar.YEAR);
        } else {
            return endDate.get(Calendar.YEAR);
        }
    }

    public void printProject () {
        System.out.println("Project id: " + id + " start date: " + startDate.getTime() + " end date: " + endDate.getTime() + " budget " + budget);
    }

    public void setNewDateAndBudget(int[] newStartDate, int[] newEndDate, int newBudget) {
        startDate = new GregorianCalendar(newStartDate[2], newStartDate[1], newStartDate[0]);
        endDate = new GregorianCalendar(newEndDate[2], newEndDate[1], newEndDate[0]);
        budget = newBudget - budgetUsed;
        SoftwareHuset.writeToCSV("projects");
    }
}

