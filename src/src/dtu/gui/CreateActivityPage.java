package dtu.gui;

import dtu.employees.Developer;
import dtu.project.Project;
import dtu.softwarehus.SoftwareHuset;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateActivityPage {
    Project thisProject;
    JButton saveBtn;
    JPanel createProjectPanel;
    JLabel startDateLabel, endDateLabel, budgetLabel, projectLabel;
    JTextField startDateTxtField, endDateTxtField, budgetTxtField, projectTxtField;
    SoftwareHuset softwareHuset;
    Main parentWindow;
    Developer user;

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
        saveBtn.setBounds(150, 300, 193, 29);
        createProjectPanel.add(saveBtn);

        /*
        b2 = new JButton("Add developer to project"); //set label to button
        b2.setBounds(25, 250, 193, 29);
        */

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("budget = " + Integer.parseInt(budgetTxtField.getText()) + ", projekt = " + Integer.parseInt(projectTxtField.getText()));
                /*
                thisProject.startWeek = Integer.parseInt(startDateTxtField.getText());
                thisProject.endWeek = Integer.parseInt(endDateTxtField.getText());
                thisProject.budget = Integer.parseInt(budgetTxtField.getText());
                */

               // String projectManager1 = projectManager.getText();
              //  String name = "New project";

                setVisible(false);
                clear();
                DeveloperPage.setVisible(true);

            }
        });

    }
    public void setVisible(boolean visi){
        createProjectPanel.setVisible(visi);
    }

    private void clear() {
        startDateTxtField.setText("");
        endDateTxtField.setText("");
        projectTxtField.setText("");
        budgetTxtField.setText("");
    }
    public void setLabels(String project ){

        thisProject = SoftwareHuset.projects.get(Integer.parseInt(project));

        startDateTxtField.setText(String.valueOf(thisProject.startWeek));
        endDateTxtField.setText(String.valueOf(thisProject.endWeek));
        budgetTxtField.setText(String.valueOf(thisProject.budget));
    }

    private void addLabelsToScreen(){
        startDateLabel = new JLabel();
        startDateLabel.setText("Start date");      //set label value for textField1
        startDateLabel.setBounds(25, 50, 193, 29);

        endDateLabel = new JLabel();
        endDateLabel.setText("End date");
        endDateLabel.setBounds(25, 100, 193, 29);

        budgetLabel = new JLabel();
        budgetLabel.setText("Budget");
        budgetLabel.setBounds(25, 150, 193, 29);

        projectLabel = new JLabel();
        projectLabel.setText("Which project to assign activity to");
        projectLabel.setBounds(25, 200, 193, 29);

        createProjectPanel.add(startDateLabel);
        createProjectPanel.add(endDateLabel);
        createProjectPanel.add(budgetLabel);
        createProjectPanel.add(projectLabel);
    }

    private void addTextFieldsToScreen(){
        startDateTxtField = new JTextField(15); //set length of the text
        startDateTxtField.setBounds(250, 50, 193, 29);

        endDateTxtField = new JTextField(15);
        endDateTxtField.setBounds(250, 100, 193, 29);

        budgetTxtField = new JTextField(15);
        budgetTxtField.setBounds(250, 150, 193, 29);

        projectTxtField = new JTextField(15);
        projectTxtField.setBounds(250, 200, 193, 29);

        createProjectPanel.add(startDateTxtField);
        createProjectPanel.add(endDateTxtField);
        createProjectPanel.add(projectTxtField);
        createProjectPanel.add(budgetTxtField);
    }

}