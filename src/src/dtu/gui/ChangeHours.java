package dtu.gui;

import dtu.project.Activity;
import dtu.project.Developer;
import dtu.project.Project;
import dtu.project.SoftwareHuset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// lavet af Victor Larsen-Saldeen

public class ChangeHours {
    static Activity[] activityList;
    static Project project11;
    private static Project project12;
    private static JPanel changeHours;
    private static JComboBox<Object> activityCombo;
    private static JComboBox<String> projectsComboBox, projectComboNotAssigned;
    private static JComboBox<Object> activeBox;
    private static Developer loggedInUser;
    private static JPanel topPanel;
    private final Main parentWindow;
    JFrame frame;
    JCheckBox checkBox1;
    Activity activity;
    JLabel userLabel;
    JTextField changeHoursField;
    boolean checked = false;
    private JLabel selectLabel, userLabel5;
    private JComboBox<Integer> hoursCB;
    private JPanel bottomPanel;

    public ChangeHours(Developer loggedInUser, Main parentWindow) {
        ChangeHours.loggedInUser = loggedInUser;
        this.parentWindow = parentWindow;
        initialize();
    }

    public static void createProjectList() {
        String[] list = DeveloperPage.fullProjectList().toArray(new String[0]);
        projectComboNotAssigned = new JComboBox<>(list);
        projectComboNotAssigned.setBounds(250, 25, 193, 29);
        topPanel.add(projectComboNotAssigned);

        project11 = SoftwareHuset.getProject(projectComboNotAssigned.getItemAt(0));
        activeBox = new JComboBox<>();
        activeBox.setBounds(250, 65, 193, 29);
        topPanel.add(activeBox);

        for (Activity activity : project11.activities.values()) {
            activeBox.addItem(activity.name);
        }
        projectComboNotAssigned.addActionListener(e -> {
            project11 = SoftwareHuset.getProject(projectComboNotAssigned.getItemAt(projectComboNotAssigned.getSelectedIndex()));
            activeBox.removeAllItems();

            for (Activity activity : project11.activities.values()) {
                activeBox.addItem(activity.name);
            }
        });
    }

    public static void createList(Developer user) {
        ArrayList<Project> list = projectListDeveloper(user);
        projectsComboBox = new JComboBox<>();
        projectsComboBox.setBounds(250, 25, 193, 29);
        topPanel.add(projectsComboBox);

        list.forEach(project -> {
            projectsComboBox.addItem(String.valueOf(project.getId()));
        });
        activityCombo = new JComboBox<>();
        activityCombo.setBounds(250, 65, 193, 29);
        topPanel.add(activityCombo);

        String project = projectsComboBox.getItemAt(0);


        try {
            project12 = SoftwareHuset.getProject(projectsComboBox.getItemAt(0));
            project12.activities.values().forEach(activity -> {
                activityCombo.addItem(activity.name);
            });
        } catch (NumberFormatException e) {
        }

        projectsComboBox.addActionListener(e -> {
            project12 = SoftwareHuset.getProject((String) projectsComboBox.getSelectedItem());
            activityCombo.removeAllItems();
            project12.activities.values().forEach(activity -> activityCombo.addItem(activity.name));

        });
    }

    public static void removeList() {
        activityCombo.remove(activityCombo);

    }

    public static ArrayList<Project> projectListDeveloper(Developer developer) {
        ArrayList<Project> projectlist2 = new ArrayList<>();

        for (Project var : SoftwareHuset.projects.values()) {

            if (var.developerIsInProject(developer)) {
                projectlist2.add(var);
            }
        }
        return projectlist2;
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

            selectLabel = new JLabel();
            selectLabel.setText("Select project");
            selectLabel.setBounds(25, 25, 193, 29);

            JLabel selectActivity = new JLabel();
            selectActivity.setText("Select activity");
            selectActivity.setBounds(25, 65, 193, 29);

            userLabel5 = new JLabel();
            userLabel5.setText("Select project id");
            userLabel5.setBounds(25, 25, 193, 29);

            JTextField writeProject = new JTextField(15);
            writeProject.setBounds(250, 65, 193, 29);

            JButton searchButton = new JButton("Search registered hours"); //set label to button
            searchButton.setBounds(249, 110, 193, 29);


            searchButton.addActionListener(e -> {
                try {
                    checked = true;
                    activity = project12.activities.get(activityCombo.getSelectedItem());
                    System.out.println(activity.getTotalRegisteredHours());
                    if (activity.registeredHours.get(loggedInUser) == null) {
                        userLabel.setText("Registered hours for selected activity = " + "0.0" + "\n" + " out of " + loggedInUser.getRegisteredHoursToday() + " today");
                    } else {
                        userLabel.setText("Registered hours for selected activity = " + activity.registeredHours.get(loggedInUser) + "\n" + " out of " + loggedInUser.getRegisteredHoursToday() + " today");
                    }
                    userLabel.setVisible(true);
                } catch (Exception ea) {
                    userLabel.setText("Select project and activity");
                }
            });
            userLabel = new JLabel();
            userLabel.setBounds(25, 150, 500, 29);
            userLabel.setFont(new Font("cambria", Font.BOLD, 15));
            topPanel.add(userLabel);
            userLabel.setVisible(false);

            JLabel changeHours = new JLabel();
            changeHours.setText("Add or remove hours (-)");
            changeHours.setBounds(25, 190, 300, 29);
            topPanel.add(changeHours);
            changeHoursField = new JTextField(15);
            changeHoursField.setBounds(250, 190, 193, 29);
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
                if (checked) {
                    try {
                        double hourChange = Double.parseDouble(changeHoursField.getText());
                        if (activity.registeredHours.containsKey(loggedInUser)) {
                            if (activity.registeredHours.get(loggedInUser) + hourChange >= 0) {
                                activity.registerHours(loggedInUser, Double.parseDouble(changeHoursField.getText()));
                                checked = false;
                                setVisible(false);
                                removeList();
                                clear();
                                DeveloperPage.setVisible(true);
                            } else {
                                JOptionPane.showMessageDialog(null, "You can't remove more hours than registered!");
                            }
                        }
                    } catch (NumberFormatException ea) {
                        JOptionPane.showMessageDialog(null, "Please only use numbers");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Check hours first please");
                }


            });

        }
    }

    public void setLabelVisible(boolean visible) {
        userLabel5.setVisible(visible);
        projectComboNotAssigned.setVisible(visible);
        activeBox.setVisible(visible);
        selectLabel.setVisible(!visible);
        projectsComboBox.setVisible(!visible);
        activityCombo.setVisible(!visible);

    }

    public void setVisible(boolean visi) {
        changeHours.setVisible(visi);
    }

    public void clear() {

    }

    private void createPage() {
        changeHours = new JPanel();
        parentWindow.addPanel(changeHours);
        changeHours.setLayout(null);
        changeHours.setBorder(BorderFactory.createTitledBorder("Manage hours"));
        topPanel = new JPanel();
        topPanel.setBounds(25, 25, 450, 250);
        changeHours.add(topPanel);
        topPanel.setBorder(BorderFactory.createTitledBorder("View hours"));
        topPanel.setLayout(null);
        bottomPanel = new JPanel();
        changeHours.add(bottomPanel);
        bottomPanel.setBounds(25, 300, 450, 100);
        bottomPanel.setLayout(null);
        bottomPanel.setBorder(BorderFactory.createTitledBorder("Submit"));

    }

}
