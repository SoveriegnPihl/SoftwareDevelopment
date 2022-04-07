package dtu.gui;
import dtu.employees.Developer;

import dtu.softwarehus.SoftwareHuset;

import javax.swing.*;
import java.awt.event.*;


//create CreateLoginForm class to create login form
//class extends JFrame to create a window where our component add
//class implements ActionListener to perform an action on button click
public class CreateProjectPage {
    //initialize button, panel, label, and text field
    JButton createProjectBtn;
    JPanel createProjectPanel;
    JLabel userLabel,userLabel2,userLabel3,userLabel4;
    JTextField startDate,endDate,projectManager,budget1;
    SoftwareHuset softwareHuset;
    Main parentWindow;
    Developer user;

    //calling constructor
    public CreateProjectPage( SoftwareHuset softwareHuset, Main parentWindow) {
        this.softwareHuset = softwareHuset;
        this.parentWindow = parentWindow;
        initialize();
    }
    public void initialize(){
        createProjectPanel = new JPanel();
        parentWindow.addPanel(createProjectPanel);
        createProjectPanel.setLayout(null);


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
        createProjectBtn = new JButton("Create project"); //set label to button
        createProjectBtn.setBounds(150, 300, 193, 29);


        createProjectPanel.setLayout(null);
        createProjectPanel.add(startDate);    //set username label to panel
        createProjectPanel.add(endDate);   //set text field to panel
        createProjectPanel.add(projectManager);
        createProjectPanel.add(budget1);
        createProjectPanel.add(userLabel);
        createProjectPanel.add(userLabel2);
        createProjectPanel.add(userLabel3);
        createProjectPanel.add(userLabel4);
        createProjectPanel.add(createProjectBtn);



        createProjectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String startWeekTxt = startDate.getText();        //get user entered username from the textField1
                String endWeekTxt = endDate.getText();
                String budgetTxt = budget1.getText();
                String projectManagerTxt = projectManager.getText();


                int project = SoftwareHuset.createProject(Integer.parseInt(startWeekTxt),Integer.parseInt(endWeekTxt),Integer.parseInt(budgetTxt));
                if(!projectManagerTxt.isEmpty()) {
                    SoftwareHuset.assignPM(projectManagerTxt, project);
                }

                setVisible(false);
                clear();
                parentWindow.setVisible(true);

            }
        });

    }
    public void setVisible(boolean visi){
        createProjectPanel.setVisible(visi);
    }
    public void clear() {
        startDate.setText("");
        endDate.setText("");
        projectManager.setText("");
        budget1.setText("");
    }

}