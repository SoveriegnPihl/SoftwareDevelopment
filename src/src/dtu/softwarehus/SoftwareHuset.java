package dtu.softwarehus;

import dtu.employees.Developer;
import dtu.project.Activity;
import dtu.project.Project;
import dtu.project.Report;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Arrays;

public class SoftwareHuset {
    static ArrayList<Report> reports;
    public static HashMap<String, Developer> developers;
    public static HashMap<Integer,String> projectManagers;
    static ArrayList<Developer> availableDevelopers;
    public static HashMap<Integer, Project> projects;
    public static HashMap<String, Activity> allActivities;
    private DateServer dateServer;
    public static ArrayList<String[]> csvProjectData,csvDeveloperData, csvActivityData;

    static ErrorMessageHolder errorMessageHolder = new ErrorMessageHolder();
    public SoftwareHuset() {
    }

    public static void startProgram() {
        readFromCSV("src/src/dtu/data/projects.csv", "src/src/dtu/data/developers.csv","src/src/dtu/data/activities.csv");

        reports = new ArrayList<>();
        projectManagers = new HashMap<>();
        availableDevelopers = new ArrayList<>();
        assignPM("vic7",projects.get(22002).getId());
        assignPM("ekki",projects.get(22001).getId());

        projects.get(22001).addDeveloper(developers.get("vic7"));
        projects.get(22002).addDeveloper(developers.get("ekki"));

    }

    public static void readFromCSV(String filePathProj, String filePathDevs, String filePathAktivities){
        projects = new HashMap<>();
        developers = new HashMap<>();
        allActivities = new HashMap<>();
        csvProjectData = new ArrayList<>();
        csvDeveloperData = new ArrayList<>();
        csvActivityData = new ArrayList<>();

        try{
            Scanner sc1 = new Scanner(new File(filePathProj));
            Scanner sc2 = new Scanner(new File(filePathDevs));
            Scanner sc3 = new Scanner(new File(filePathAktivities));

            while (sc1.hasNextLine()){
                String[] projArr = sc1.nextLine().split(",");
                System.out.println(Arrays.toString(projArr));
                GregorianCalendar start = new GregorianCalendar(Integer.parseInt(projArr[1]),
                        Integer.parseInt(projArr[2]),Integer.parseInt(projArr[3]));
                GregorianCalendar end = new GregorianCalendar(Integer.parseInt(projArr[4]),
                        Integer.parseInt(projArr[5]),Integer.parseInt(projArr[6]));
                createProject(start,end,Integer.parseInt(projArr[7]));
            }
            sc1.close();

            while (sc2.hasNextLine()){
                String[] devArr = sc2.nextLine().split(",");
                addDeveloper(devArr);
            }
            sc2.close();

            while (sc3.hasNextLine()){
                String[] actArr = sc3.nextLine().split(",");
                addProjectActivities(projects.get(Integer.parseInt(actArr[0])), actArr);
            }
            sc3.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void addProjectActivities(Project project, String[] activityArray) {
        Activity activityToAdd = new Activity(activityArray[1], Integer.parseInt(activityArray[8]));
        activityToAdd.setProjectAssignedTo(project.getId());

        GregorianCalendar start = new GregorianCalendar(Integer.parseInt(activityArray[2]), Integer.parseInt(activityArray[3]),
                Integer.parseInt(activityArray[4]));
        GregorianCalendar end = new GregorianCalendar(Integer.parseInt(activityArray[5]),Integer.parseInt(activityArray[6]),
                Integer.parseInt(activityArray[7]));

        activityToAdd.setDateInterval(start, end);
        activityToAdd.setBudget(Integer.parseInt(activityArray[10]));
        activityToAdd.setTotalRegisteredHours(Double.parseDouble(activityArray[9]));
        allActivities.put(activityArray[1],activityToAdd);

        project.addActivity(activityToAdd);
        csvActivityData.add(activityArray);
        writeToCSV("activities");
    }

    public static void addDeveloper(String[] readData) {
        Developer newDeveloper = new Developer(readData[0]);
        newDeveloper.setHolidayDates(readData);
        newDeveloper.setSickDates(readData);
        developers.put(readData[0],newDeveloper);

        if (!newDeveloper.hasOccupation && !newDeveloper.isSick){
            csvDeveloperData.add(new String[] {readData[0], "noOcc", "noSick"});
        }
        else if(!newDeveloper.hasOccupation && newDeveloper.isSick){
            csvDeveloperData.add(new String[] {readData[0], "noOcc", readData[2],readData[3],readData[4],
                    readData[5],readData[6],readData[7],});
        }
        else if(newDeveloper.hasOccupation && !newDeveloper.isSick){
            csvDeveloperData.add(new String[] {readData[0], readData[1],readData[2],readData[3],readData[4],
                    readData[5],readData[6], "noSick"});
        }
        else{
            csvDeveloperData.add(readData);
        }
        writeToCSV("developers");

        if(developers.containsKey(readData[0])){
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
       if(!isManager(dev)){
        if(projects.containsKey(projectID) && developers.containsKey(dev)) {
            projectManagers.put(projectID, dev);
        }
       }
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

        for (Project var : projects.values()){

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

    public static Developer getDeveloper(String name){
        return developers.get(name);
    }

    public static String listAvailableDevelopers(){
        StringBuilder str = new StringBuilder();

        for (Developer dev : developers.values()){
            str.append(dev.getAvailability());
        }
        return str.toString();
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
    public static Project getProject(String id){
        return projects.get(Integer.valueOf(id));
    }

    public static void updateCSVFile(String file) {

        if(Objects.equals(file, "projects")){
            csvProjectData.clear();
            for (Project p : projects.values()){
                csvProjectData.add(new String[] {String.valueOf(p.getId()), p.getDateYear("start"),
                        p.getDateMonth("start"),p.getDateDay("start"),
                        p.getDateYear("end"), p.getDateMonth("end"),
                        p.getDateDay("end"), String.valueOf(p.getBudget())});
            }
            writeToCSV("projects");
        }
        else if (Objects.equals(file, "developers")){
            csvDeveloperData.clear();
            for (Developer d : developers.values()){
                if(!d.hasOccupation && !d.isSick){
                    csvDeveloperData.add(new String[]{d.getInitials(), "noOcc", "noSick"});
                }
                else if (!d.hasOccupation && d.isSick){
                    csvDeveloperData.add(new String[]{d.getInitials(), "noOcc", d.getOccDateYear("start", "sick"),
                            d.getOccDateMonth("start", "sick"), d.getOccDateDay("start", "sick"),
                            d.getOccDateYear("end", "sick"), d.getOccDateMonth("end", "sick"),
                            d.getOccDateDay("end", "sick")});
                }
                else if (d.hasOccupation && !d.isSick){
                    csvDeveloperData.add(new String[]{d.getInitials(), d.getOccDateYear("start", "holiday"),
                            d.getOccDateMonth("start", "holiday"), d.getOccDateDay("start", "holiday"),
                            d.getOccDateYear("end", "holiday"), d.getOccDateMonth("end", "holiday"),
                            d.getOccDateDay("end", "holiday"), "noSick"});
                }
                else {
                    csvDeveloperData.add(new String[]{d.getInitials(), d.getOccDateYear("start", "holiday"),
                            d.getOccDateMonth("start", "holiday"), d.getOccDateDay("start", "holiday"),
                            d.getOccDateYear("end", "holiday"), d.getOccDateMonth("end", "holiday"),
                            d.getOccDateDay("end", "holiday"),d.getOccDateYear("start", "sick"),
                            d.getOccDateMonth("start", "sick"), d.getOccDateDay("start", "sick"),
                            d.getOccDateYear("end", "sick"), d.getOccDateMonth("end", "sick"),
                            d.getOccDateDay("end", "sick")});
                }
            }
            writeToCSV("developers");
        }
        else if(Objects.equals(file, "activities")){
            csvActivityData.clear();
            for (Activity A : allActivities.values()){
                csvActivityData.add(new String[] {A.getProjectAssignedTo(), A.getName(), String.valueOf(A.getStartDate().get(Calendar.YEAR)),
                        String.valueOf(A.getStartDate().get(Calendar.MONTH)),String.valueOf(A.getStartDate().get(Calendar.DAY_OF_MONTH)),
                        String.valueOf(A.getEndDate().get(Calendar.YEAR)),String.valueOf(A.getEndDate().get(Calendar.MONTH)),
                        String.valueOf(A.getEndDate().get(Calendar.DAY_OF_MONTH)),String.valueOf(A.getEstimatedTime()),
                        String.valueOf(A.getTotalRegisteredHours()), String.valueOf(A.getBudget())});
            }
            writeToCSV("activities");
        }
        else{
            System.out.println("forkert filnavn angivet");
        }

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
        else if (Objects.equals(file, "activities")){
            try(PrintWriter writer = new PrintWriter("src/src/dtu/data/activities.csv")){
                csvActivityData.stream().map(SoftwareHuset::convertToCSV).forEach(writer::println);

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