package dtu.gui;

import dtu.employees.Developer;
import dtu.project.Activity;
import dtu.project.Project;
import dtu.project.TimeRegistration;
import dtu.softwarehus.SoftwareHuset;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class RegisterHours {
    private static JPanel registerHours;
    private final Main parentWindow;
    private static JComboBox<Object> projectList;
    private static JComboBox<String> projectList2,projectList3;
    private static JComboBox activeBox;
    private static Developer loggedInUser;
    private JLabel selectLabel,userLabel5;
    private boolean checked = false;
    JCheckBox checkBox1;
    private JComboBox<Integer> hoursCB;
    static Activity[] activityList;
    static List<String> firstActivityList;
    static List<String> secondActivityList;


    public RegisterHours(Developer loggedInUser, Main parentWindow) {
        RegisterHours.loggedInUser = loggedInUser;
        this.parentWindow = parentWindow;
        initialize();
    }
    
    public void initialize() {
        {
            registerHours = new JPanel();
            parentWindow.addPanel(registerHours);
            registerHours.setLayout(null);

            checkBox1 = new JCheckBox("Not assigned to project");
            checkBox1.setBounds(150, 25, 193, 29);

            selectLabel = new JLabel();
            selectLabel.setText("Select project");
            selectLabel.setBounds(25, 75, 193, 29);

            JLabel selectActivity = new JLabel();
            selectActivity.setText("Select activity");
            selectActivity.setBounds(25, 110, 193, 29);

            userLabel5 = new JLabel();
            userLabel5.setText("Type project id");
            userLabel5.setBounds(25, 75, 193, 29);

            JTextField writeProject = new JTextField(15);
            writeProject.setBounds(250, 75, 193, 29);

            //create label for username
            JLabel userLabel = new JLabel();
            userLabel.setText("Enter time");      //set label value for textField1
            userLabel.setBounds(25, 150, 193, 29);

            JLabel userLabel2 = new JLabel();
            userLabel2.setText("Date");
            userLabel2.setBounds(25, 200, 193, 29);

            //create text field to get username from the user
            JTextField endDate = new JTextField(15);
            endDate.setBounds(250, 200, 193, 29);

            JLabel hourLbl = new JLabel();
            hourLbl.setText("Hours:");
            hourLbl.setBounds(210, 150, 250, 40);
            registerHours.add(hourLbl);

            JLabel minLbl = new JLabel();
            minLbl.setText("Min:");
            minLbl.setBounds(375, 150, 250, 40);
            registerHours.add(minLbl);

            Integer[] comboBoxItemsMinutes = new Integer[2];
            comboBoxItemsMinutes[0] = 0;
            comboBoxItemsMinutes[1] = 30;

            Integer[] comboBoxItemsHours = new Integer[24];
            for (int i = 0; i < 24; i++) {
                comboBoxItemsHours[i] = i;
            }
            hoursCB = new JComboBox<>(comboBoxItemsHours);
            hoursCB.setBounds(250, 155, 70, 30);
            registerHours.add(hoursCB);

            JComboBox<Integer> minCB = new JComboBox<>(comboBoxItemsMinutes);
            minCB.setBounds(400, 155, 70, 30);
            registerHours.add(minCB);

            checkBox1.addActionListener(e -> {
                checked = !checked;
                createProjectList();
                setLabelVisible(checked);
            });

            JButton submitButton = new JButton("Submit"); //set label to button
            submitButton.setBounds(150, 300, 193, 29);

            registerHours.setLayout(null);
            registerHours.add(endDate);
            registerHours.add(selectActivity);
            registerHours.add(userLabel);
            registerHours.add(userLabel2);
            registerHours.add(selectLabel);
            registerHours.add(submitButton);
            registerHours.add(checkBox1);
            registerHours.add(userLabel5);
            registerHours.add(writeProject);
             userLabel5.setVisible(false);
             writeProject.setVisible(false);

            submitButton.addActionListener(e -> {
                GregorianCalendar date = new GregorianCalendar(2022, Calendar.MARCH,2);
                TimeRegistration timeRegistration = new TimeRegistration(loggedInUser,date, hoursCB.getSelectedIndex());
                Activity activity = activityList[projectList.getSelectedIndex()];
                activity.registerTime(timeRegistration);
                setVisible(false);
                removeList();
                clear();
                DeveloperPage.setVisible(true);
                System.out.println(timeRegistration.getAmountOfTime());
                System.out.println(activity.getTimeRegistrationForEmployeeOnDate(loggedInUser,date).toString());
                System.out.println(SoftwareHuset.projects.get(22001).getReportedTimeForActivity(activity));
            });

        }
    }

    public void setLabelVisible(boolean visible){
        userLabel5.setVisible(visible);
        projectList3.setVisible(visible);
        activeBox.setVisible(visible);
        selectLabel.setVisible(!visible);
        projectList2.setVisible(!visible);
        projectList.setVisible(!visible);

    }

    public void setVisible(boolean visi){
        registerHours.setVisible(visi);
    }
    public void clear() {

    }

    public static void createProjectList(){
        String[] list = SoftwareHuset.fullProjectList().toArray(new String[0]);
        projectList3 = new JComboBox<>(list);
        projectList3.setBounds(250, 75, 193, 29);
        registerHours.add(projectList3);
        secondActivityList = new ArrayList<>();
        String project = projectList3.getItemAt(0);
        Project project1 =  SoftwareHuset.getProject(project);

        for (Activity activity : project1.activities.keySet()) {
            secondActivityList.add(activity.name);
        }

        projectList3.addActionListener(e -> {
            String project2 = projectList3.getItemAt(projectList3.getSelectedIndex());
        Project project11 =  SoftwareHuset.getProject(project2);

        for (int i = 0; i < secondActivityList.size(); i++) {
            secondActivityList.remove(i);
        }

        for (Activity activity : project11.activities.keySet()) {
            secondActivityList.add(activity.name);
        }
        activeBox.removeAllItems();
        activeBox.setModel(new DefaultComboBoxModel<>(secondActivityList.toArray()));

            });
        activeBox = new JComboBox<>(secondActivityList.toArray(new String[0]));
        activeBox.setBounds(250, 110, 193, 29);
        registerHours.add(activeBox);

    }
    public static void createList(Developer user){
        ArrayList<Project> list = SoftwareHuset.projectListDeveloper(user);
        List<String> projectList123 = new ArrayList<>();
        for (Project project: list) {
            projectList123.add(String.valueOf(project.getId()));
        }
        projectList2 = new JComboBox<>(projectList123.toArray(new String[0]));
        projectList2.setBounds(250, 75, 193, 29);
        projectList2.setSelectedIndex(0);
        registerHours.add(projectList2);
        firstActivityList = new ArrayList<>();
        String project = projectList2.getItemAt(0);
        System.out.println(projectList2.getItemAt(0));
        activityList = SoftwareHuset.getProject(project).userActivities(loggedInUser).toArray(new Activity[0]);
        for (Activity activity : activityList) {
            firstActivityList.add(activity.name);
        }

        projectList2.addActionListener(e -> {
        String project1 = projectList2.getItemAt(projectList2.getSelectedIndex());
        activityList = SoftwareHuset.getProject(project1).userActivities(loggedInUser).toArray(new Activity[0]);
            for (int i = 0; i < firstActivityList.size(); i++) {
                firstActivityList.remove(i);
            }
        for (Activity activity : activityList) {
            firstActivityList.add(activity.name);
        }
        System.out.println(firstActivityList);
            projectList.removeAllItems();
            projectList.setModel(new DefaultComboBoxModel<>(firstActivityList.toArray()));

        });
        projectList = new JComboBox<>(firstActivityList.toArray(new String[0]));
        projectList.setBounds(250, 110, 193, 29);
        registerHours.add(projectList);
    }
    public static void removeList(){
        projectList.remove(projectList);

    }


}
