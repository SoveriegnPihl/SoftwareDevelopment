package dtu.softwarehus;

import dtu.employees.Developer;
import dtu.project.Project;
import dtu.project.Report;

import java.util.ArrayList;
import java.util.Scanner;

public class SoftwareHuset {
    ArrayList<Report> reports;
    ArrayList<Developer> developers;
    ArrayList<Developer> availableDevelopers;
    ArrayList<Project> projects;
    static Scanner scanner = new Scanner(System.in);

    public SoftwareHuset(){

        projects = new ArrayList<>();
        reports = new ArrayList<>();
        developers = new ArrayList<>();
        availableDevelopers = new ArrayList<>();

        System.out.println("What would you like to do?");
        System.out.println("4: List all available developers");
        System.out.println("3: List all developers");
        System.out.println("2: Create project");
        System.out.println("1: See all projects");
        System.out.println("0: Exit");
        int input = scanner.nextInt();

        while( input < 0 || input > 5){
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

            input = scanner.nextInt();
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
        developers.add(dev);
    }

    public void listProjects(){
        for (Project var : projects){
            var.printProject();
            System.out.println("");
        }
    }

    public void listDevelopers(){
        for (Developer var : developers){
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
        for (Developer var : developers){
            if(!var.isOccupied()){
                availableDevelopers.add(var);
            }
        }
    }

}
