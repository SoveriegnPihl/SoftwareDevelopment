package dtu.softwarehus;

import dtu.employees.Developer;
import dtu.employees.Manager;
import dtu.project.Project;
import dtu.project.Report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SoftwareHuset {
    ArrayList<Report> reports;
    HashMap<String, Developer> developers;
    HashMap<String, Manager> projectManagers;
    HashMap<Developer,Integer> hoursWorked;
    ArrayList<Developer> availableDevelopers;
    ArrayList<Project> projects;
    static Scanner scanner = new Scanner(System.in);

    public SoftwareHuset(){

        projects = new ArrayList<>();
        reports = new ArrayList<>();
        developers = new HashMap<>();
        hoursWorked = new HashMap<>();
        projectManagers = new HashMap<>();
        availableDevelopers = new ArrayList<>();

        System.out.println("What would you like to do?");
        System.out.println("8: Hour report");
        System.out.println("7: Add hours");
        System.out.println("6: Assign project manager");
        System.out.println("5: Add developer");
        System.out.println("4: List all available developers");
        System.out.println("3: List all developers");
        System.out.println("2: Create project");
        System.out.println("1: See all projects");
        System.out.println("0: Exit");
        int input = scanner.nextInt();

        while( input < 0 || input > 9){
            input = scanner.nextInt();
        }

        fakeTest();
        while (input != 0){

            if(input == 1){
                listProjects();
            }
            if(input == 2){
                createProject();
            }
            if(input == 3){

                listDevelopers();
            }
            if(input == 4){

                whoIsAvailable();
                listAvailableDevelopers();
            }
            if(input == 5){

                addDeveloper1();
            }
            if(input == 6){
                addPm();
            }
            if(input == 7){
                addHours();
            }
            if(input == 8){
                workedHoursReport();
            }

            input = scanner.nextInt();
        }

    }

    private void addDeveloper1() {
            System.out.println("Please input initials");
            String name = scanner.next();
            Developer newDeveloper = new Developer(name);
            developers.put(name,newDeveloper);
            if(developers.containsKey(name)){
                System.out.println("Success");
            }
        }


    public void createProject(){

        System.out.println("Please input name, start & end week and budget");
        String name = scanner.next();
        int startWeek = scanner.nextInt();
        int endWeek = scanner.nextInt();
        int budget = scanner.nextInt();
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

    public void listDevelopers(){
        for (Developer var : developers.values()){
            var.printDeveloper();
        }
    }

    public void fakeTest(){
        Developer dev1 = new Developer("abc");
        Developer dev2 = new Developer("def");
        Developer dev3 = new Developer("ghi");
        Developer dev4 = new Developer("jkl");

        dev1.setOccupied();
        dev2.setOccupied();
        dev4.setOccupied();
        dev4.setUnOccupied();

        addDeveloper(dev1);
        addDeveloper(dev2);
        addDeveloper(dev3);
        addDeveloper(dev4);
    }

    public void listAvailableDevelopers(){
        for (Developer var : availableDevelopers){
            var.printDeveloper();
        }
    }

    public void whoIsAvailable(){
        for (Developer var : developers.values()){
            if(!var.isOccupied()){
                availableDevelopers.add(var);
            }
        }
    }

    private void addPm() {
        System.out.println("Please input initials");
        String name = scanner.next();

        if (developers.containsKey(name)) {
            Manager PM = new Manager(name);
            projectManagers.put(name,PM);
            System.out.println("success");
        } else {
                System.out.println("no developer found");
        }
    }
    private void addHours(){
        System.out.println("Please input your initials");
        String name = scanner.next();
        if (developers.containsKey(name)) {
            System.out.println("How many hours?");
            if(hoursWorked.containsKey(developers.get(name))){
                hoursWorked.put(developers.get(name), hoursWorked.get(developers.get(name))+scanner.nextInt());
            } else {
                hoursWorked.put(developers.get(name),scanner.nextInt());
            }
            System.out.println("Success");
        }
    }
    private void workedHoursReport(){
        System.out.println("Please input your initials");
        String name = scanner.next();
        if (developers.containsKey(name)) {
            System.out.println("number of hours= "+ hoursWorked.get(developers.get(name)));
        }
    }

    }

