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

public class RegisterHours {
    private static JPanel registerHours;
    private final Main parentWindow;
    private static JComboBox<Object> activityCombo;
    private static JComboBox<String> projectsComboBox,projectComboNotAssigned;
    private static JComboBox<Object> activeBox;
    private static Developer loggedInUser;
    private JLabel selectLabel,userLabel5;
    private boolean checked = false;
    JCheckBox checkBox1;
    private JComboBox<Integer> hoursCB;
    static Activity[] activityList;
    static Project project11;


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
                GregorianCalendar date = new GregorianCalendar(2022, Calendar.MARCH, 2);
                TimeRegistration timeRegistration = new TimeRegistration(loggedInUser, date, hoursCB.getSelectedIndex());
                if(!checked) {
                    Activity activity = activityList[activityCombo.getSelectedIndex()];
                   activity.registerTime(timeRegistration);
                   System.out.println(timeRegistration.getAmountOfTime());
                   System.out.println(activity.getTimeRegistrationForEmployeeOnDate(loggedInUser, date).toString());
                   System.out.println(SoftwareHuset.projects.get(22001).getReportedTimeForActivity(activity));
               } else {
                    int i = activeBox.getSelectedIndex();
                   Activity activity2 = (Activity) project11.activities.keySet().toArray()[i];
                   activity2.registerTime(timeRegistration);
               }
                setVisible(false);
                removeList();
                clear();
                DeveloperPage.setVisible(true);


            });

        }
    }

    public void setLabelVisible(boolean visible){
        userLabel5.setVisible(visible);
        projectComboNotAssigned.setVisible(visible);
        activeBox.setVisible(visible);
        selectLabel.setVisible(!visible);
        projectsComboBox.setVisible(!visible);
        activityCombo.setVisible(!visible);

    }

    public void setVisible(boolean visi){
        registerHours.setVisible(visi);
    }
    public void clear() {

    }

    public static void createProjectList(){
        String[] list = SoftwareHuset.fullProjectList().toArray(new String[0]);
        projectComboNotAssigned = new JComboBox<>(list);
        projectComboNotAssigned.setBounds(250, 75, 193, 29);
        registerHours.add(projectComboNotAssigned);

        project11 =  SoftwareHuset.getProject(projectComboNotAssigned.getItemAt(0));

        activeBox = new JComboBox<>();
        activeBox.setBounds(250, 110, 193, 29);
        registerHours.add(activeBox);

        for (Activity activity : project11.activities.values()) {
            activeBox.addItem(activity.name);
        }
        projectComboNotAssigned.addActionListener(e -> {
        project11 =  SoftwareHuset.getProject(projectComboNotAssigned.getItemAt(projectComboNotAssigned.getSelectedIndex()));
        activeBox.removeAllItems();

        for (Activity activity : project11.activities.values()) {
            activeBox.addItem(activity.name);
            }
        });
    }
    public static void createList(Developer user){
        ArrayList<Project> list = SoftwareHuset.projectListDeveloper(user);
        projectsComboBox = new JComboBox<>();
        projectsComboBox.setBounds(250, 75, 193, 29);
        registerHours.add(projectsComboBox);

        for (Project project: list) {
            projectsComboBox.addItem(String.valueOf(project.getId()));
        }
        activityCombo = new JComboBox<>();
        activityCombo.setBounds(250, 110, 193, 29);
        registerHours.add(activityCombo);

        String project = projectsComboBox.getItemAt(0);
        activityList = SoftwareHuset.getProject(project).userActivities(loggedInUser).toArray(new Activity[0]);
        for (Activity activity : activityList) {
            activityCombo.addItem(activity.name);
        }

        projectsComboBox.addActionListener(e -> {
        String project1 = projectsComboBox.getItemAt(projectsComboBox.getSelectedIndex());
        activityCombo.removeAllItems();
        activityList = SoftwareHuset.getProject(project1).userActivities(loggedInUser).toArray(new Activity[0]);
        for (Activity activity : activityList) {
            activityCombo.addItem(activity.name);
            }
        });
    }
    public static void removeList(){
        activityCombo.remove(activityCombo);

    }


}
