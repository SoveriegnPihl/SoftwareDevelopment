package dtu.softwarehus;

import dtu.employees.Developer;
import dtu.project.Project;
import dtu.project.Report;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SoftwareHuset {
    static ArrayList<Report> reports;
    public static HashMap<String, Developer> developers;
    static HashMap<Integer,String> projectManagers;
    static ArrayList<Developer> availableDevelopers;
    static HashMap<Integer, Project> projects;
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
    }

    public static void readFromCSV(String filePathProj, String filePathDevs){
        projects = new HashMap<>();
        developers = new HashMap<>();
        csvProjectData = new ArrayList<>();

        try{
            Scanner sc1 = new Scanner(new File(filePathProj));
            Scanner sc2 = new Scanner(new File(filePathDevs));

            while (sc1.hasNextLine()){
                String[] att = sc1.nextLine().split(",");
                createProject(Integer.parseInt(att[1]),Integer.parseInt(att[2]),Integer.parseInt(att[3]));
                csvProjectData.add(att);
            }
            sc1.close();

            while (sc2.hasNext()){
                String[] initialsarr = {sc2.next()};
                addDeveloper(initialsarr[0]);
                csvDeveloperData.add(initialsarr);
            }
            sc2.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void addDeveloper(String name) {
        csvDeveloperData = new ArrayList<>();

        Developer newDeveloper = new Developer(name);
        developers.put(name,newDeveloper);
        if(developers.containsKey(name)){
            System.out.println("Success");
        }
    }

    public void setDateServer(DateServer dateServer) {
        this.dateServer = dateServer;
    }


    public static int createProject(int startWeek, int endWeek, int budget){

        System.out.println("Please input name, start & end week and budget");

        Project toAdd = new Project(startWeek, endWeek, budget);
        toAdd.printProject();

        csvProjectData.add(new String[] {String.valueOf(toAdd.getId()), String.valueOf(startWeek), String.valueOf(endWeek), String.valueOf(budget)});

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
    public static ArrayList<String> projectList(Developer developer){
        ArrayList<String> projectlist = new ArrayList<>();
        String name = developer.getInitials();
        for (Integer var : projectManagers.keySet()){
            if (projectManagers.get(var).equals(name)){
                projectlist.add(var.toString());
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

    public void writeToCSV(String file){

        if (Objects.equals(file, "projects")){
            try(PrintWriter writer = new PrintWriter("src/src/dtu/data/projects.csv")){
                csvProjectData.stream().map(this::convertToCSV).forEach(writer::println);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else if (Objects.equals(file, "developers")){
            try(PrintWriter writer = new PrintWriter("src/src/dtu/data/developers.csv")){
                csvDeveloperData.stream().map(this::convertToCSV).forEach(writer::println);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else{
            System.out.println("ikke en eksisterende csv fil");
        }

    }

    public String convertToCSV(String[] data) {
        return Stream.of(data).map(this::escapeSpecialCharacters).collect(Collectors.joining(","));
    }

    public String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }

}