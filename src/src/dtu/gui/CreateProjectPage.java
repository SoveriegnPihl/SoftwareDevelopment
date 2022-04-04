package dtu.gui;
import dtu.employees.Developer;
import dtu.gui.DeveloperPage;
import dtu.gui.ProjectManagerPage;
import dtu.project.Project;
import dtu.softwarehus.SoftwareHuset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//create CreateLoginForm class to create login form
//class extends JFrame to create a window where our component add
//class implements ActionListener to perform an action on button click
public class CreateProjectPage extends JFrame  {
    //initialize button, panel, label, and text field
    JButton b1;
    JPanel newPanel;
    JLabel userLabel,userLabel2,userLabel3,userLabel4;
    JTextField startDate,endDate,projectManager,budget1;
    private JPanel panel1;
    private JCheckBox checkBox1;
    boolean managerCheckBox = false;
    SoftwareHuset softwareHuset;
    boolean b;

    //calling constructor
    public CreateProjectPage(Developer user) {
        softwareHuset = new SoftwareHuset();

        //create label for username
        userLabel = new JLabel();
        userLabel.setText("Start date");      //set label value for textField1
        userLabel.setBounds(25, 50, 193, 29);

        userLabel2 = new JLabel();
        userLabel2.setText("End date");
        userLabel2.setBounds(25, 100, 193, 29);

        userLabel3 = new JLabel();
        userLabel3.setText("Budget");
        userLabel3.setBounds(25, 150, 193, 29);

        userLabel4 = new JLabel();
        userLabel4.setText("Assign project manager");
        userLabel4.setBounds(25, 200, 193, 29);

        //create text field to get username from the user
        startDate = new JTextField(15); //set length of the text
        startDate.setBounds(250, 50, 193, 29);

        //create text field to get username from the user
        endDate = new JTextField(15);
         endDate.setBounds(250, 100, 193, 29);

        budget1 = new JTextField(15);
        budget1.setBounds(250, 150, 193, 29);
        //create submit button

        projectManager = new JTextField(15);
        projectManager.setBounds(250, 200, 193, 29);
        //create submit button
        b1 = new JButton("Create project"); //set label to button
        b1.setBounds(150, 300, 193, 29);


        //create panel to put form elements
        newPanel = new JPanel();
        newPanel.setLayout(null);
        newPanel.add(startDate);    //set username label to panel
        newPanel.add(endDate);   //set text field to panel
        newPanel.add(projectManager);
        newPanel.add(budget1);
        newPanel.add(userLabel);
        newPanel.add(userLabel2);
        newPanel.add(userLabel3);
        newPanel.add(userLabel4);
        newPanel.add(b1);

        //set button to panel
        //set border to panel
        add(newPanel);


        setTitle("Create Project");         //set title to the login form


        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String startWeek = startDate.getText();        //get user entered username from the textField1
                String endWeek = endDate.getText();
                String budget = budget1.getText();
                String projectManager1 = projectManager.getText();
                String name = "hej";


                int project = softwareHuset.createProject(name,Integer.parseInt(startWeek),Integer.parseInt(endWeek),Integer.parseInt(budget));
                if(!projectManager1.isEmpty()){
                    softwareHuset.assignPM(projectManager1,project);

                    ProjectManagerPage pmPage = new ProjectManagerPage(user);
                    //make page visible to the user
                    setVisible(false);
                    pmPage.setLocationRelativeTo(null);
                    pmPage.setVisible(true);

                }



            }
        });

    }

}