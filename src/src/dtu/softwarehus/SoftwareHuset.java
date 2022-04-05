package dtu.softwarehus;

import dtu.employees.Developer;
import dtu.employees.Manager;
import dtu.project.Project;
import dtu.project.Report;

import java.util.ArrayList;
import java.util.HashMap;

public class SoftwareHuset {
    static ArrayList<Report> reports;
    public static HashMap<String, Developer> developers;
    static HashMap<Integer,String> projectManagers;
    static ArrayList<Developer> availableDevelopers;
    static HashMap<Integer, Project> projects;
    public SoftwareHuset() { }

    public static void startProgram(){

        projects = new HashMap<>();
        reports = new ArrayList<>();
        developers = new HashMap<>();
        projectManagers = new HashMap<>();
        availableDevelopers = new ArrayList<>();
        addDeveloper("ekki");
        addDeveloper("vic7");
        addDeveloper("jako");
        addDeveloper("jlm");
        developers.get("ekki").setOccupied(false);
        developers.get("vic7").setOccupied(false);
        developers.get("jako").setOccupied(true);
        developers.get("jlm").setOccupied(false);

        int project = createProject("22001", 1,2,4);
        assignPM("ekki",project);

    }

    public static void addDeveloper(String name) {

            Developer newDeveloper = new Developer(name);
            developers.put(name,newDeveloper);
            if(developers.containsKey(name)){
                System.out.println("Success");
            }
        }


    public static int createProject(String name, int startWeek, int endWeek, int budget){

        System.out.println("Please input name, start & end week and budget");

        Project toAdd = new Project(name, startWeek, endWeek, budget);
        toAdd.printProject();
        projects.put(toAdd.getId(),toAdd);
        return toAdd.getId();

    }

    public static void assignPM(String dev, int projectID){
        projectManagers.put(projectID,dev);
    }

    public void listProjects(){
        for (Project var : projects.values()){
            var.printProject();
            System.out.println("");
        }
    }
    public static String[] projectList(Developer developer){
        String[] projectlist = new String[5];
        int count = 0;
        String name = developer.getInitials();
        for (Integer var : projectManagers.keySet()){
        if (projectManagers.get(var).equals(name)){
            projectlist[count]=var.toString();
        }
        }
        return projectlist;
    }

    public String listDevelopers(){
        StringBuilder str = new StringBuilder();

        for (Developer var : developers.values()){
            str.append(var.printDeveloper());
        }
        return str.toString();
    }

    public static Developer getDeveloper(String name){
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

    public static boolean isDeveloper(String ini){
        return developers.containsKey(ini);
    }

    public static boolean isManager(String ini){
        return projectManagers.containsValue(ini);
    }

    public boolean findProject(String projectId){
        for (Project proj : projects.values()){
            System.out.println(proj.name);
            if(proj.name.equals(projectId)){
                return true;
            }
        }
        return false;
    }

}


