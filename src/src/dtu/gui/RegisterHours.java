package dtu.gui;

import dtu.employees.Developer;
import dtu.project.Activity;
import dtu.project.Project;
import dtu.project.TimeRegistration;
import dtu.softwarehus.SoftwareHuset;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;

public class RegisterHours {
    private static JPanel registerHours;
    private static JComboBox projectList;
    private static Developer loggedInUser;
    private static JComboBox projectList3;
    private static JComboBox activBox;
    private Main parentWindow;
    private JLabel selectLabel;
    private JLabel userLabel;
    private JLabel userLabel2;
    private JLabel userLabel4;
    private JTextField startDate;
    private JTextField endDate;
    private JButton sumbitButton;
    private boolean checked = false;
    private JTextField writeProject;
    private JLabel userLabel5;
    JCheckBox checkBox1;
    private JComboBox<Integer> hoursCB;
    private JComboBox minCB;
    static Activity[] activityList;
    static List<String> activilist;
    static List<String> activilist2;
    static JComboBox projectList2;

    public RegisterHours(Developer loggedInUser, Main parentWindow) {
        this.loggedInUser = loggedInUser;
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

            writeProject = new JTextField(15);
            writeProject.setBounds(250, 75, 193, 29);

            //create label for username
            userLabel = new JLabel();
            userLabel.setText("Enter time");      //set label value for textField1
            userLabel.setBounds(25, 150, 193, 29);

            userLabel2 = new JLabel();
            userLabel2.setText("Date");
            userLabel2.setBounds(25, 200, 193, 29);

            //create text field to get username from the user
            endDate = new JTextField(15);
            endDate.setBounds(250, 200, 193, 29);

            JLabel hourlbl = new JLabel();
            hourlbl.setText("Hours:");
            hourlbl.setBounds(210, 150, 250, 40);
            registerHours.add(hourlbl);

            JLabel minlbl = new JLabel();
            minlbl.setText("Min:");
            minlbl.setBounds(375, 150, 250, 40);
            registerHours.add(minlbl);

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

            minCB = new JComboBox<>(comboBoxItemsMinutes);
            minCB.setBounds(400, 155, 70, 30);
            registerHours.add(minCB);

            //create submit button

            checkBox1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    checked = !checked;
                    createProjectList();
                    setLabelVisible(checked);
                }
            });
            /*
            projectManager = new JTextField(15);
            projectManager.setBounds(250, 200, 193, 29);
*/            //create submit button
            sumbitButton = new JButton("Sumbit"); //set label to button
            sumbitButton.setBounds(150, 300, 193, 29);

            registerHours.setLayout(null);
            registerHours.add(endDate);   //set text field to panel
//            registerHours.add(projectManager);
             registerHours.add(selectActivity);
            registerHours.add(userLabel);
            registerHours.add(userLabel2);
            registerHours.add(selectLabel);
            // registerHours.add(userLabel4);
            registerHours.add(sumbitButton);
            registerHours.add(checkBox1);
            registerHours.add(userLabel5);
            registerHours.add(writeProject);
             userLabel5.setVisible(false);
             writeProject.setVisible(false);



            sumbitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                    String startWeekTxt = startDate.getText();        //get user entered username from the textField1
//                    String endWeekTxt = endDate.getText();
//
                    GregorianCalendar date = new GregorianCalendar(2,2,2022);
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
                }
            });

        }
    }


    public void setLabelVisible(boolean visible){
        userLabel5.setVisible(visible);
        projectList3.setVisible(visible);
        activBox.setVisible(visible);
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
        System.out.println(SoftwareHuset.fullProjectList().toString());
        projectList3 = new JComboBox(list);
        projectList3.setBounds(250, 75, 193, 29);
        registerHours.add(projectList3);
        activilist2 = new ArrayList<>();

        projectList3.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String project = (String) projectList3.getItemAt(projectList3.getSelectedIndex());
        Project project1 =  SoftwareHuset.getProject(project);

        for (Activity activity : ((Project) project1).activities.keySet()) {
            activilist2.add(activity.name);
        }
        System.out.println(activilist2);
           activBox.removeAllItems();
            activBox.setModel(new DefaultComboBoxModel(activilist2.toArray()));

            }
             });
        activBox = new JComboBox(activilist2.toArray(new String[0]));
        activBox.setBounds(250, 400, 193, 29);
        registerHours.add(activBox);

    }
    public static void createList(Developer user){
        ArrayList<Project> list = SoftwareHuset.projectListDeveloper(user);
        List<String> projectList123 = new ArrayList<>();
        for (Project project: list) {
            projectList123.add(String.valueOf(project.getId()));
        }
        projectList2 = new JComboBox(projectList123.toArray(new String[0]));
        projectList2.setBounds(250, 75, 193, 29);
            registerHours.add(projectList2);
        activilist = new ArrayList<>();
            projectList2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                //createActivityList((String) projectList2.getItemAt(projectList2.getSelectedIndex()));
                String project = (String) projectList2.getItemAt(projectList2.getSelectedIndex());
                activityList = SoftwareHuset.getProject(project).userActivities(loggedInUser).toArray(new Activity[0]);

                for (Activity activity : activityList) {
                    activilist.add(activity.name);
                }
                System.out.println(activilist);
                    projectList.removeAllItems();
                    projectList.setModel(new DefaultComboBoxModel(activilist.toArray()));
            }
          });
        projectList = new JComboBox(activilist.toArray(new String[0]));
        projectList.setBounds(250, 110, 193, 29);
        registerHours.add(projectList);
    }
    public static void removeList(){
        projectList.remove(projectList);

    }


}
