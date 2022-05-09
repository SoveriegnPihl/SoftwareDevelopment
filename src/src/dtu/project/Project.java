package dtu.project;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import static javax.swing.UIManager.put;

public class Project {
    static int nextId = 1;
    public String name;
    int id;
    public GregorianCalendar startDate;
    public GregorianCalendar endDate;
    public int budget, budgetUsed;

    public List<Developer> developers = new ArrayList<>();
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

    public int getEstimatedTime() {
        int projectEstimatedTime = 0;
        for (Activity A : activities.values()){
            projectEstimatedTime += A.getEstimatedTime();
        }

        return projectEstimatedTime;
    }

    public int getUsedTime(){
        int usedTime = 0;
        for (Activity A : activities.values()){
            usedTime += A.totalRegisteredHours;
        }
        return usedTime;
    }

    public int getBudgetUsed(){return (budgetUsed*(-1));}

    public int getEstimatedBudget() {
        int projectEstimatedBudget = 0;
        for (Activity A : activities.values()){
            projectEstimatedBudget += A.getBudget();
        }

        return projectEstimatedBudget;
    }

    public void addActivity(Activity activity){
        if(activity.getStartDate().compareTo(startDate) == 1 && activity.getEndDate().compareTo(endDate) == -1){    //1
            assert startDate != null && endDate != null && activity.getName() != null;                              //"Precondition";
            budgetUsed -= activity.getBudget();                                                                     //2
            activities.put(activity.getName(), activity);                                                           //3
            System.out.println("Activity added");                                                                   //4
            assert activities.containsKey(activity.getName());                                                      //"Postcondition";
        } else {
            System.out.println("ikke inden for datoen af projektet");                                               //5
        }
        //assert activities.containsKey(activity.getName());
    }


   public boolean findActivity(String activityName){
        for (String actName : activities.keySet()){
            if(actName.equals(activityName)){
                return true;
            }
        }
        return false;
    }

    public void addDeveloper (Developer dev){
        boolean exists = false;
        for (Developer d : developers){
            if (d.getInitials().equals(dev.getInitials())){
                exists = true;
            }
        }
        System.out.println(exists);
        if (!exists){
            developers.add(dev);
        }
    }

    public boolean developerIsInProject (Developer dev){
        return developers.contains(dev);
    }

    public String getDateDay (String time){
        if (time.equals("start")) {
            return String.valueOf(startDate.get(Calendar.DAY_OF_MONTH));
        } else {
            return String.valueOf(endDate.get(Calendar.DAY_OF_MONTH));
        }
    }

    public String getDateMonth (String time){
        if (time.equals("start")) {
            return String.valueOf(startDate.get(Calendar.MONTH));

        } else {
            return String.valueOf(endDate.get(Calendar.MONTH));
        }
    }

    public String getDateYear (String time){
        if (time.equals("start")) {
            return String.valueOf(startDate.get(Calendar.YEAR));
        } else {
            return String.valueOf(endDate.get(Calendar.YEAR));
        }
    }

    public void printProject () {
        System.out.println("Project id: " + id + " start date: " + startDate.getTime() + " end date: " + endDate.getTime() + " budget " + budget);
    }

    public void setNewDateAndBudget(int[] newStartDate, int[] newEndDate, int newBudget) {
        startDate = new GregorianCalendar(newStartDate[2], newStartDate[1], newStartDate[0]);
        endDate = new GregorianCalendar(newEndDate[2], newEndDate[1], newEndDate[0]);
        budget = newBudget + budgetUsed;
        SoftwareHuset.writeToCSV("projects");
    }

}

