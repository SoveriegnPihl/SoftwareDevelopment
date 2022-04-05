package dtu.softwarehus;

import dtu.employees.Developer;
import dtu.employees.Manager;
import dtu.project.Project;
import dtu.project.Report;

import java.io.File;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class SoftwareHuset {
    static ArrayList<Report> reports;
    public static HashMap<String, Developer> developers;
    static HashMap<String, Manager> projectManagers;
    static ArrayList<Developer> availableDevelopers;
    public static  ArrayList<Project> projects;
    private DateServer dateServer;

    public SoftwareHuset() {


    }

    public void startProgram(){
        readProjectsFromCSV("src/src/dtu/data/projects.csv","src/src/dtu/data/developers.csv");

        /*reports = new ArrayList<>();
        developers = new HashMap<>();
        projectManagers = new HashMap<>();
        availableDevelopers = new ArrayList<>();
        addDeveloper1("ekki");
        addDeveloper1("vic7");
        addDeveloper1("jako");
        addDeveloper1("jlm");
        developers.get("ekki").setOccupied(false);
        developers.get("vic7").setOccupied(false);
        developers.get("jako").setOccupied(true);
        developers.get("jlm").setOccupied(false);

        developers.get("ekki").setToProjectManager();
        Project testProject = new Project("22001", 1,2,4);
        projects.add(testProject);*/

    }

    public static void readProjectsFromCSV(String filePathProj, String filePathDevs){
        projects = new ArrayList<>();
        developers = new HashMap<>();

        try{
            Scanner sc1 = new Scanner(new File(filePathProj));
            Scanner sc2 = new Scanner(new File(filePathDevs));

            while (sc1.hasNextLine()){
                String[] att = sc1.nextLine().split(",");
                Project project = createProjectFromCSV(att);

                projects.add(project);
            }
            sc1.close();

            while (sc2.hasNext()){
                String initials = sc2.next();

                Developer developer = new Developer(initials);
                developers.put(initials, developer);
            }
            sc2.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static Project createProjectFromCSV(String[] att){
        String name = att[0];
        int startW = Integer.parseInt(att[1]);
        int endW = Integer.parseInt(att[2]);
        int budget = Integer.parseInt(att[3]);

        return new Project(name, startW, endW, budget);

    }

    public void addDeveloper1(String name) {

            Developer newDeveloper = new Developer(name);
            developers.put(name,newDeveloper);
            if(developers.containsKey(name)){
                System.out.println("Success");
            }
        }
        
    public void setDateServer(DateServer dateServer) {
        this.dateServer = dateServer;
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

    public Developer getDeveloper(String name){
        return developers.get(name);
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

    public void addPm(String name) {
        System.out.println("Please input initials");

        if (developers.containsKey(name)) {
            Manager PM = new Manager(name);
            projectManagers.put(name,PM);
            System.out.println("success");
        } else {
                System.out.println("no developer found");
        }
    }

    public static boolean isDeveloper(String ini){
        return developers.containsKey(ini);
    }

    public boolean findProject(String projectId){
        for (Project proj : projects){
            if(proj.name.equals(projectId)){
                return true;
            }
        }
        return false;
    }

}


