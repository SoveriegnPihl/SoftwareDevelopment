/*package dtu.gui;
//import required classes and packages
import dtu.employees.Developer;
import dtu.gui.DeveloperPage;
import dtu.gui.ProjectManagerPage;
import dtu.softwarehus.SoftwareHuset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//create CreateLoginForm class to create login form
//class extends JFrame to create a window where our component add
//class implements ActionListener to perform an action on button click
public class CreateLoginForm extends JFrame  {
    //initialize button, panel, label, and text field
    JButton b1,b2;
    JPanel newPanel;
    JLabel userLabel;
    JTextField textField1;
    private JPanel panel1;
    private JCheckBox checkBox1;
    boolean managerCheckBox = false;
    SoftwareHuset softwareHuset;
    boolean b;

    //calling constructor
    public CreateLoginForm() {
        //softwareHuset = new SoftwareHuset();

        //create label for username
        userLabel = new JLabel();
        userLabel.setText("Username");      //set label value for textField1

        //create text field to get username from the user
        textField1 = new JTextField(15);    //set length of the text

        //create submit button
        b1 = new JButton("Login"); //set label to button

        b2 = new JButton("Create project");


        //create checkbox
        checkBox1 = new JCheckBox("As project manager");


        //create panel to put form elements
        newPanel = new JPanel();
        newPanel.setLayout(null);
        newPanel.setBorder(BorderFactory.createTitledBorder(
                "Start page"));

        userLabel.setBounds(75, 25, 193, 29);
        newPanel.add(userLabel);    //set username label to panel
        textField1.setBounds(225, 25, 193, 29);
        newPanel.add(textField1);   //set text field to panel
        checkBox1.setBounds(50, 55, 193, 29);
        newPanel.add(checkBox1);
        b1.setBounds(55, 100, 193, 45);
        newPanel.add(b1);
        b2.setBounds(255, 100, 193, 45);
        newPanel.add(b2);

        //set button to panel
        //set border to panel
        add(newPanel);


        setTitle("LOGIN FORM");         //set title to the login form
        setDefaultCloseOperation(javax.swing.
                WindowConstants.DISPOSE_ON_CLOSE);
        setSize(500, 200);

        checkBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                managerCheckBox = !managerCheckBox;
                /* sinds cleanup
                 if (!managerCheckBox) {
                    managerCheckBox = true;
                } else {
                    managerCheckBox = false;
                }
                 */
/*
            }
        });

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userValue = textField1.getText();        //get user entered username from the textField1

                if (SoftwareHuset.isDeveloper(userValue)) {
                    Developer user = SoftwareHuset.getDeveloper(userValue);
                    if (managerCheckBox && SoftwareHuset.isManager(userValue)) {
                        //create instance of the NewPage
                        ProjectManagerPage pmPage = new ProjectManagerPage(user);

                        //make page visible to the user
                        setVisible(false);
                        pmPage.setLocationRelativeTo(null);
                        pmPage.setVisible(true);


                    } else if (!managerCheckBox)  {
                        //check whether the credentials are authentic or not

                        //create instance of the NewPage
                        DeveloperPage dPage = new DeveloperPage(user);

                        //make page visible to the user
                        setVisible(false);
                        dPage.setLocationRelativeTo(null);
                        dPage.setVisible(true);

                    }

                }
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                CreateProjectPage OP = new CreateProjectPage();
                OP.setSize(500,500);  //set size of the frame
                OP.setLocationRelativeTo(null);
                OP.setVisible(true);

            }
        });

    }

}*/