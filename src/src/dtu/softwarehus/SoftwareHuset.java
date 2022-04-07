package dtu.softwarehus;

import dtu.employees.Developer;
import dtu.project.Activity;
import dtu.project.Project;
import dtu.project.Report;
import io.cucumber.java.bs.A;

import java.io.File;
import java.io.PrintWriter;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SoftwareHuset {
    static ArrayList<Report> reports;
    public static HashMap<String, Developer> developers;
    static HashMap<Integer,String> projectManagers;
    static ArrayList<Developer> availableDevelopers;
    public static HashMap<Integer, Project> projects;
    //public static  ArrayList<Project> projects;
    private DateServer dateServer;
    public static ArrayList<String[]> csvProjectData;
    public static ArrayList<String[]> csvDeveloperData;

    public SoftwareHuset() {
    }

    public static void startProgram() {
        readFromCSV("src/src/dtu/data/projects.csv", "src/src/dtu/data/developers.csv");

        reports = new ArrayList<>();
        projectManagers = new HashMap<>();
        availableDevelopers = new ArrayList<>();
        assignPM("ekki",projects.get(22001).getId());

        projects.get(22001).addDeveloper(developers.get("vic7"));
        projects.get(22002).addDeveloper(developers.get("ekki"));
        Activity activity = new Activity("fodbold",5);
        Activity activity2 = new Activity("film",5);
        projects.get(22001).addActivity(activity,2,2,0,5);
        projects.get(22001).addActivity(activity2,2,2,0,5);
        activity.addDev(developers.get("vic7"),1,2);
        activity2.addDev(developers.get("vic7"),1,2);
    }

    public static void readFromCSV(String filePathProj, String filePathDevs){
        projects = new HashMap<>();
        developers = new HashMap<>();
        csvProjectData = new ArrayList<>();
        csvDeveloperData = new ArrayList<>();


        try{
            Scanner sc1 = new Scanner(new File(filePathProj));
            Scanner sc2 = new Scanner(new File(filePathDevs));

            while (sc1.hasNextLine()){
                String[] att = sc1.nextLine().split(",");
                System.out.println(Arrays.toString(att));
                GregorianCalendar start = new GregorianCalendar(Integer.parseInt(att[1]), Integer.parseInt(att[2]),Integer.parseInt(att[3]));
                GregorianCalendar end = new GregorianCalendar(Integer.parseInt(att[4]),Integer.parseInt(att[5]),Integer.parseInt(att[6]));
                createProject(start,end,Integer.parseInt(att[7]));
                //csvProjectData.add(att);

            }
            sc1.close();

            while (sc2.hasNext()){
                String[] initialsarr = {sc2.next()};
                addDeveloper(initialsarr[0]);
                //csvDeveloperData.add(initialsarr);
            }
            sc2.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void addDeveloper(String name) {
        String[] nameArr = {name};

        Developer newDeveloper = new Developer(name);
        developers.put(name,newDeveloper);

        csvDeveloperData.add(nameArr);
        writeToCSV("developers");

        if(developers.containsKey(name)){
            System.out.println("Success");
        }
    }

    public void setDateServer(DateServer dateServer) {
        this.dateServer = dateServer;
    }

    public static int createProject(GregorianCalendar start, GregorianCalendar end, int budget){
        Project newProject = new Project(start,end, budget);
        newProject.printProject();

        projects.put(newProject.getId(),newProject);
        csvProjectData.add(new String[] {String.valueOf(newProject.getId()), String.valueOf(newProject.getDateYear("start")),
                String.valueOf(newProject.getDateMonth("start")),String.valueOf(newProject.getDateDay("start")),
                String.valueOf(newProject.getDateYear("end")), String.valueOf(newProject.getDateMonth("end")),
                String.valueOf(newProject.getDateDay("end")), String.valueOf(budget)});

        writeToCSV("projects");

        return newProject.getId();

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
    public static ArrayList<String> projectListManagers(Developer developer){
        ArrayList<String> projectlist = new ArrayList<>();
        String name = developer.getInitials();
        for (Integer var : projectManagers.keySet()){
            if (projectManagers.get(var).equals(name)){
                projectlist.add(var.toString());
            }
        }
        return projectlist;
    }
    public static ArrayList<Project> projectListDeveloper(Developer developer){
        ArrayList<Project> projectlist2 = new ArrayList<>();

        System.out.println("WTF:"+ projects.get(22001).getId());

        for (Project var : projects.values()){
            System.out.println("ID ER "+var.getId()); // siger id er 220002, id 22002
            System.out.println("det er "+var.developerIsInProject(developer));
            if (var.developerIsInProject(developer)){ //true hver gang
                projectlist2.add(var);
            }
        }
        return projectlist2;

    }

    public static ArrayList<String> fullProjectList(){
        ArrayList<String> projectlist = new ArrayList<>();
        for (Project project : projects.values()){
                projectlist.add(String.valueOf(project.getId()));
        }
        return projectlist;
    }

    public static String listDevelopers(){
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

    public static void writeToCSV(String file){

        if (Objects.equals(file, "projects")){
            try(PrintWriter writer = new PrintWriter("src/src/dtu/data/projects.csv")){
                csvProjectData.stream().map(SoftwareHuset::convertToCSV).forEach(writer::println);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else if (Objects.equals(file, "developers")){
            try(PrintWriter writer = new PrintWriter("src/src/dtu/data/developers.csv")){
                csvDeveloperData.stream().map(SoftwareHuset::convertToCSV).forEach(writer::println);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else{
            System.out.println("ikke en eksisterende csv fil");
        }

    }

    public static String convertToCSV(String[] data) {
        return Stream.of(data).map(SoftwareHuset::escapeSpecialCharacters).collect(Collectors.joining(","));
    }

    public static String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }

}