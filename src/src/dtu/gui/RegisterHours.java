package dtu.gui;

import dtu.employees.Developer;
import dtu.project.Activity;
import dtu.project.OperationNotAllowedException;
import dtu.project.Project;
import dtu.project.TimeRegistration;
import dtu.softwarehus.SoftwareHuset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class RegisterHours {
    private static Project project12;
    JFrame frame;
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
        createPage();
            JButton btnBack = new JButton("Back");
            btnBack.addActionListener(e -> {
                setVisible(false);
                clear();
                DeveloperPage.setVisible(true);
            });
            btnBack.setBounds(21, 300, 59, 29);
            registerHours.add(btnBack);

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
            registerHours.add(selectActivity);
            registerHours.add(userLabel);
            registerHours.add(selectLabel);
            registerHours.add(submitButton);
            registerHours.add(checkBox1);
            registerHours.add(userLabel5);
            registerHours.add(writeProject);
             userLabel5.setVisible(false);
             writeProject.setVisible(false);

            submitButton.addActionListener(e -> {
                double hours = hoursCB.getSelectedIndex();
                if(minCB.getSelectedIndex() == 1){
                    hours+=0.5;
                }
                if(!checked) {
                   Activity activity = project12.activities.get(activityCombo.getSelectedItem());
                   activity.registerHours(loggedInUser,hours);
                    System.out.println(activity.getRegisteredHours());

               } else {
                    int i = activeBox.getSelectedIndex();
                   Activity activity2 = (Activity) project11.activities.keySet().toArray()[i];
                    activity2.registerHours(loggedInUser,hours);
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

        list.forEach(project -> {projectsComboBox.addItem(String.valueOf(project.getId()));});
        activityCombo = new JComboBox<>();
        activityCombo.setBounds(250, 110, 193, 29);
        registerHours.add(activityCombo);

        String project = projectsComboBox.getItemAt(0);
        System.out.println(project.toString()+" hej");


        //activityList = SoftwareHuset.getProject(project).userActivities(user).toArray(new Activity[0]);

         project12 =  SoftwareHuset.getProject(projectsComboBox.getItemAt(0));
         project12.activities.values().forEach(activity -> {activityCombo.addItem(activity.name);});

        projectsComboBox.addActionListener(e -> {
            project12 =  SoftwareHuset.getProject((String) projectsComboBox.getSelectedItem());
        activityCombo.removeAllItems();
            project12.activities.values().forEach(activity -> activityCombo.addItem(activity.name)) ;

        });
    }



    public static void removeList(){
        activityCombo.remove(activityCombo);

    }
    private void createPage() {
        frame = new JFrame();
        frame.setBounds(100, 100, 500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new CardLayout(0, 0));
        registerHours = new JPanel();
        frame.getContentPane().add(registerHours);
        parentWindow.addPanel(registerHours);
        registerHours.setLayout(null);
        registerHours.setBorder(BorderFactory.createTitledBorder("Register hours page"));
    }


}
