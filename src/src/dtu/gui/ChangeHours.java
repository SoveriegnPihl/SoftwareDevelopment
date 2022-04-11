package dtu.gui;

import dtu.employees.Developer;
import dtu.project.Activity;
import dtu.project.Project;
import dtu.softwarehus.SoftwareHuset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
            btnBack.setBounds(21, 300, 59, 29);
            changeHours.add(btnBack);

            checkBox1 = new JCheckBox("Not assigned to project");
            checkBox1.setBounds(150, 25, 193, 29);

            JLabel assignedOrNot = new JLabel();
            assignedOrNot.setText("Are you assigned to the project?");
            assignedOrNot.setBounds(10, 10, 193, 29);
            changeHours.add(assignedOrNot);

            JRadioButton r1=new JRadioButton("Yes");
            JRadioButton r2=new JRadioButton("No");
            r1.setBounds(250,10,75,30);
            r2.setBounds(350,10,75,30);
            r1.setSelected(true);
            ButtonGroup bg=new ButtonGroup();

            bg.add(r1);bg.add(r2);
            changeHours.add(r1);changeHours.add(r2);


            selectLabel = new JLabel();
            selectLabel.setText("Select project");
            selectLabel.setBounds(25, 75, 193, 29);

            JLabel selectActivity = new JLabel();
            selectActivity.setText("Select activity");
            selectActivity.setBounds(25, 110, 193, 29);

            userLabel5 = new JLabel();
            userLabel5.setText("Select project id");
            userLabel5.setBounds(25, 75, 193, 29);

            JTextField writeProject = new JTextField(15);
            writeProject.setBounds(250, 75, 193, 29);

            JButton searchButton = new JButton("Search registered hours"); //set label to button
            searchButton.setBounds(250,150 , 193, 29);

            checkBox1.addActionListener(e -> {
                checked = !checked;
                createProjectList();
                setLabelVisible(checked);
            });
            searchButton.addActionListener(e -> {
            activity = project12.activities.get(activityCombo.getSelectedItem());
                System.out.println(activity.getTotalRegisteredHours());
            userLabel.setText("Registered hours for selected activity = "+ activity.registeredHours.get(loggedInUser) + "\n" + " out of "+loggedInUser.getRegisteredHoursToday()+" today");      //set label value for textField1
            userLabel.setVisible(true);
            });
            userLabel = new JLabel();
            userLabel.setBounds(25, 250, 500, 29);
            changeHours.add(userLabel);
            userLabel.setVisible(false);
            JButton submitButton = new JButton("Submit"); //set label to button
            submitButton.setBounds(150, 300, 193, 29);

            changeHours.setLayout(null);
            changeHours.add(selectActivity);
            changeHours.add(selectLabel);
            changeHours.add(submitButton);
            changeHours.add(checkBox1);
            changeHours.add(userLabel5);
            changeHours.add(writeProject);
            changeHours.add(searchButton);
            userLabel5.setVisible(false);
            writeProject.setVisible(false);

            submitButton.addActionListener(e -> {

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
        String[] list = SoftwareHuset.fullProjectList().toArray(new String[0]);
        projectComboNotAssigned = new JComboBox<>(list);
        projectComboNotAssigned.setBounds(250, 75, 193, 29);
        changeHours.add(projectComboNotAssigned);

        project11 =  SoftwareHuset.getProject(projectComboNotAssigned.getItemAt(0));
        activeBox = new JComboBox<>();
        activeBox.setBounds(250, 110, 193, 29);
        changeHours.add(activeBox);

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
        changeHours.add(projectsComboBox);

        list.forEach(project -> {projectsComboBox.addItem(String.valueOf(project.getId()));});
        activityCombo = new JComboBox<>();
        activityCombo.setBounds(250, 110, 193, 29);
        changeHours.add(activityCombo);

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
        changeHours = new JPanel();
        frame.getContentPane().add(changeHours);
        parentWindow.addPanel(changeHours);
        changeHours.setLayout(null);
        changeHours.setBorder(BorderFactory.createTitledBorder("Register hours page"));
    }


}
