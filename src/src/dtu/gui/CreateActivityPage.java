package dtu.gui;

import dtu.project.Activity;
import dtu.project.Project;
import dtu.softwarehus.SoftwareHuset;

import javax.swing.*;


public class CreateActivityPage {
    JButton saveBtn;
    JPanel createProjectPanel;
    JLabel startDateLabel, endDateLabel, budgetLabel, projectLabel, activityNameLabel, estTimeLabel;
    JTextField startDateTxtField, endDateTxtField, estTimeTxtField, projectTxtField, activityTxtField, budgetTxtField;
    SoftwareHuset softwareHuset;
    Main parentWindow;

    public CreateActivityPage(SoftwareHuset softwareHuset, Main parentWindow) {
        this.softwareHuset = softwareHuset;
        this.parentWindow = parentWindow;
        initialize();
    }
    public void initialize(){
        createProjectPanel = new JPanel();
        parentWindow.addPanel(createProjectPanel);
        createProjectPanel.setLayout(null);

        //adding labels to screen
        addLabelsToScreen();

        //adding textfields to screen
        addTextFieldsToScreen();

        //create save button
        saveBtn = new JButton("Save"); //set label to button
        saveBtn.setBounds(150, 350, 193, 29);
        createProjectPanel.add(saveBtn);

        saveBtn.addActionListener(e -> {
            Activity newActivity = new Activity(activityTxtField.getText(), Integer.parseInt(estTimeTxtField.getText()));
            Project projectToAddTo = SoftwareHuset.projects.get(Integer.parseInt(projectTxtField.getText()));

            projectToAddTo.addActivity(newActivity, Integer.parseInt(startDateTxtField.getText()), Integer.parseInt(endDateTxtField.getText()),
                    Integer.parseInt(budgetTxtField.getText()), Integer.parseInt(estTimeTxtField.getText()));

            setVisible(false);
            clear();
            DeveloperPage.setVisible(true);

        });

    }
    public void setVisible(boolean visi){
        createProjectPanel.setVisible(visi);
    }

    private void clear() {
        activityTxtField.setText("");
        startDateTxtField.setText("");
        endDateTxtField.setText("");
        projectTxtField.setText("");
        estTimeTxtField.setText("");
        budgetTxtField.setText("");
    }

    private void addLabelsToScreen(){
        activityNameLabel = new JLabel();
        activityNameLabel.setText("Activity name");
        activityNameLabel.setBounds(25, 50, 193, 29);

        startDateLabel = new JLabel();
        startDateLabel.setText("Activity start date");
        startDateLabel.setBounds(25, 100, 193, 29);

        endDateLabel = new JLabel();
        endDateLabel.setText("Activity end date");
        endDateLabel.setBounds(25, 150, 193, 29);

        estTimeLabel = new JLabel();
        estTimeLabel.setText("Estimated time");
        estTimeLabel.setBounds(25, 200, 193, 29);

        budgetLabel = new JLabel();
        budgetLabel.setText("Budget");
        budgetLabel.setBounds(25, 250, 193, 29);

        projectLabel = new JLabel();
        projectLabel.setText("Which project to assign activity to");
        projectLabel.setBounds(25, 300, 193, 29);

        createProjectPanel.add(startDateLabel);
        createProjectPanel.add(endDateLabel);
        createProjectPanel.add(budgetLabel);
        createProjectPanel.add(projectLabel);
        createProjectPanel.add(activityNameLabel);
        createProjectPanel.add(estTimeLabel);
    }

    private void addTextFieldsToScreen(){
        activityTxtField = new JTextField(15);
        activityTxtField.setBounds(250, 50, 193, 29);

        startDateTxtField = new JTextField(15);
        startDateTxtField.setBounds(250, 100, 193, 29);

        endDateTxtField = new JTextField(15);
        endDateTxtField.setBounds(250, 150, 193, 29);

        estTimeTxtField = new JTextField(15);
        estTimeTxtField.setBounds(250, 200, 193, 29);

        budgetTxtField = new JTextField(15);
        budgetTxtField.setBounds(250, 250, 193, 29);

        projectTxtField = new JTextField(15);
        projectTxtField.setBounds(250, 300, 193, 29);

        createProjectPanel.add(startDateTxtField);
        createProjectPanel.add(endDateTxtField);
        createProjectPanel.add(projectTxtField);
        createProjectPanel.add(estTimeTxtField);
        createProjectPanel.add(activityTxtField);
        createProjectPanel.add(budgetTxtField);
    }

}