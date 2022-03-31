package dtu.softwarehus;

import dtu.employees.Developer;
import dtu.employees.Manager;
import dtu.project.Project;
import dtu.project.Report;

import java.util.ArrayList;
import java.util.HashMap;

public class SoftwareHuset {
    ArrayList<Report> reports;
    static HashMap<String, Developer> developers;
    HashMap<String, Manager> projectManagers;
    static HashMap<Developer,Integer> hoursWorked;
    ArrayList<Developer> availableDevelopers;
    ArrayList<Project> projects;

    Developer dev1;
    Developer dev2;
    Developer dev3;
    Developer dev4;


    public SoftwareHuset() { }

    public void startProgram(){

        projects = new ArrayList<>();
        reports = new ArrayList<>();
        developers = new HashMap<>();
        hoursWorked = new HashMap<>();
        projectManagers = new HashMap<>();
        availableDevelopers = new ArrayList<>();
        addDeveloper1("abc");
        addDeveloper1("def");
        addDeveloper1("ghi");
        addDeveloper1("jlm");
        developers.get("abc").setOccupied();
        developers.get("def").setOccupied();
        developers.get("ghi").setOccupied();
        developers.get("jlm").setUnOccupied();

    }

    private void addDeveloper1(String name) {

            Developer newDeveloper = new Developer(name);
            developers.put(name,newDeveloper);
            if(developers.containsKey(name)){
                System.out.println("Success");
            }
        }


    public void createProject(String name, int startWeek, int endWeek, int budget){

        System.out.println("Please input name, start & end week and budget");

        Project toAdd = new Project(name, startWeek, endWeek, budget);
        toAdd.printProject();
        projects.add(toAdd);
        System.out.println("Success");
    }

    public void addDeveloper(Developer dev){

        developers.put(dev.getInitials(),dev);
    }

    public void listProjects(){
        for (Project var : projects){
            var.printProject();
            System.out.println("");
        }
    }

    public String listDevelopers(){
        StringBuilder str = new StringBuilder();

        for (Developer var : developers.values()){
            str.append(var.printDeveloper());
        }
        return str.toString();
    }



    public String listAvailableDevelopers(){
        StringBuilder str = new StringBuilder();

        for (Developer var : availableDevelopers){
            str.append(var.printDeveloper());
        }
        return str.toString();
    }

    public void whoIsAvailable(){
        for (Developer var : developers.values()){
            if(!var.isOccupied()){
                availableDevelopers.add(var);
            }
        }
    }

    private void addPm(String name) {
        System.out.println("Please input initials");

        if (developers.containsKey(name)) {
            Manager PM = new Manager(name);
            projectManagers.put(name,PM);
            System.out.println("success");
        } else {
                System.out.println("no developer found");
        }
    }
    public void addHours(String name,int hours){
        System.out.println("Please input your initials");
        System.out.println(name);
        if (developers.containsKey(name)) {
            System.out.println("How many hours?");
            if (hoursWorked.containsKey(developers.get(name))) {
                hoursWorked.put(developers.get(name), hoursWorked.get(developers.get(name)) + hours);
            } else {
                hoursWorked.put(developers.get(name), hours);

            }
            System.out.println("Success hours");
        }
            else {
            System.out.println("No such user found");
        }
    }
     public int workedHoursReport(String name){
        if (developers.containsKey(name)) {
            return hoursWorked.get(developers.get(name));
        }
        return 0;
    }
    public static boolean isDeveloper(String ini){
        return developers.containsKey(ini);
    }

    }

