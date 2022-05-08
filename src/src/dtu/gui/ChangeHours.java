package dtu.gui;

import dtu.project.Developer;
import dtu.project.Activity;
import dtu.project.Project;
import dtu.project.SoftwareHuset;

import javax.swing.*;
import java.util.ArrayList;

// lavet af Victor Larsen-Saldeen

public class ChangeHours {
    private static Project project12;
    JFrame frame;
    private static JPanel changeHours;
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
    Activity activity;
    JLabel userLabel;
    private static JPanel topPanel;
    private JPanel bottomPanel;
    JTextField changeHoursField;

    public ChangeHours(Developer loggedInUser, Main parentWindow) {
        this.loggedInUser = loggedInUser;
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
            btnBack.setBounds(30, 40, 80, 29);
            bottomPanel.add(btnBack);
/*
            checkBox1 = new JCheckBox("Not assigned to project");
            checkBox1.setBounds(150, 25, 193, 29);*/

            JLabel assignedOrNot = new JLabel();
            assignedOrNot.setText("Are you assigned to the project?");
            assignedOrNot.setBounds(10, 10, 250, 29);
            topPanel.add(assignedOrNot);

            JRadioButton r1=new JRadioButton("Yes");
            JRadioButton r2=new JRadioButton("No");
            r1.setBounds(275,10,75,30);
            r2.setBounds(375,10,75,30);
            r1.setSelected(true);
            ButtonGroup bg=new ButtonGroup();

            bg.add(r1);bg.add(r2);
            topPanel.add(r1);topPanel.add(r2);


            selectLabel = new JLabel();
            selectLabel.setText("Select project");
            selectLabel.setBounds(25, 50, 193, 29);

            JLabel selectActivity = new JLabel();
            selectActivity.setText("Select activity");
            selectActivity.setBounds(25, 90, 193, 29);

            userLabel5 = new JLabel();
            userLabel5.setText("Select project id");
            userLabel5.setBounds(25, 50, 193, 29);

            JTextField writeProject = new JTextField(15);
            writeProject.setBounds(250, 90, 193, 29);

            JButton searchButton = new JButton("Search registered hours"); //set label to button
            searchButton.setBounds(249,130 , 193, 29);

            r2.addActionListener(e -> {
                if(r2.isSelected()){
                    checked = !checked;
                    createProjectList();
                    setLabelVisible(checked);
                }
            });
            r1.addActionListener(e -> {
                if(r1.isSelected()){
                    checked = !checked;
                    createProjectList();
                    setLabelVisible(checked);
                }
            });

            searchButton.addActionListener(e -> {
            activity = project12.activities.get(activityCombo.getSelectedItem());
                System.out.println(activity.getTotalRegisteredHours());
            userLabel.setText("Registered hours for selected activity = "+ activity.registeredHours.get(loggedInUser) + "\n" + " out of "+loggedInUser.getRegisteredHoursToday()+" today");      //set label value for textField1
            userLabel.setVisible(true);
            });
            userLabel = new JLabel();
            userLabel.setBounds(25, 160, 500, 29);
            topPanel.add(userLabel);
            userLabel.setVisible(false);

            JLabel changeHours = new JLabel();
            changeHours.setText("Add or remove hours (-)");
            changeHours.setBounds(25 ,180,300,29);
            topPanel.add(changeHours);
            changeHoursField = new JTextField(15);
            changeHoursField.setBounds(250, 180, 193, 29);
            topPanel.add(changeHoursField);


            JButton submitButton = new JButton("Submit"); //set label to button
            submitButton.setBounds(174, 40, 193, 29);

            topPanel.setLayout(null);
            topPanel.add(selectActivity);
            topPanel.add(selectLabel);
            bottomPanel.add(submitButton);
            topPanel.add(userLabel5);
            topPanel.add(writeProject);
            topPanel.add(searchButton);
            userLabel5.setVisible(false);
            writeProject.setVisible(false);

            submitButton.addActionListener(e -> {

                activity.registerHours(loggedInUser,Double.parseDouble(changeHoursField.getText()));
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
        changeHours.setVisible(visi);
    }
    public void clear() {

    }

    public static void createProjectList(){
        String[] list = DeveloperPage.fullProjectList().toArray(new String[0]);
        projectComboNotAssigned = new JComboBox<>(list);
        projectComboNotAssigned.setBounds(250, 50, 193, 29);
        topPanel.add(projectComboNotAssigned);

        project11 =  SoftwareHuset.getProject(projectComboNotAssigned.getItemAt(0));
        activeBox = new JComboBox<>();
        activeBox.setBounds(250, 90, 193, 29);
        topPanel.add(activeBox);

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
        ArrayList<Project> list = projectListDeveloper(user);
        projectsComboBox = new JComboBox<>();
        projectsComboBox.setBounds(250, 50, 193, 29);
        topPanel.add(projectsComboBox);

        list.forEach(project -> {projectsComboBox.addItem(String.valueOf(project.getId()));});
        activityCombo = new JComboBox<>();
        activityCombo.setBounds(250, 90, 193, 29);
        topPanel.add(activityCombo);

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
        changeHours = new JPanel();
        parentWindow.addPanel(changeHours);
        changeHours.setLayout(null);
        changeHours.setBorder(BorderFactory.createTitledBorder("Register hours page"));
        topPanel = new JPanel();
        topPanel.setBounds(25,25,450,250);
        changeHours.add(topPanel);
        topPanel .setBorder(BorderFactory.createTitledBorder("View hours"));
        topPanel.setLayout(null);
        bottomPanel = new JPanel();
        changeHours.add(bottomPanel);
        bottomPanel.setBounds(25,300,450,100);
        bottomPanel.setLayout(null);
        bottomPanel.setBorder(BorderFactory.createTitledBorder("Change hours"));

    }
    public static ArrayList<Project> projectListDeveloper(Developer developer){
        ArrayList<Project> projectlist2 = new ArrayList<>();

        for (Project var : SoftwareHuset.projects.values()){

            if (var.developerIsInProject(developer)){
                projectlist2.add(var);
            }
        }
        return projectlist2;
    }

}
