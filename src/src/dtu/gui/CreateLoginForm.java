package dtu.gui;
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
    JButton b1;
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
        softwareHuset = new SoftwareHuset();
        softwareHuset.startProgram();

        //create label for username
        userLabel = new JLabel();
        userLabel.setText("Username");      //set label value for textField1

        //create text field to get username from the user
        textField1 = new JTextField(15);    //set length of the text

        //create submit button
        b1 = new JButton("Login"); //set label to button

        //create checkbox
        checkBox1 = new JCheckBox("As project manager");


        //create panel to put form elements
        newPanel = new JPanel(new GridLayout(2, 1));
        newPanel.add(userLabel);    //set username label to panel
        newPanel.add(textField1);   //set text field to panel
        newPanel.add(checkBox1);
        newPanel.add(b1);

        //set button to panel
        //set border to panel
        add(newPanel, BorderLayout.CENTER);


        setTitle("LOGIN FORM");         //set title to the login form

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
            }
        });

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userValue = textField1.getText();        //get user entered username from the textField1

                if (softwareHuset.isDeveloper(userValue)) {
                    Developer user = softwareHuset.getDeveloper(userValue);
                    if (managerCheckBox) {
                        //create instance of the NewPage
                        ProjectManagerPage pmPage = new ProjectManagerPage(user);

                        //make page visible to the user
                        setVisible(false);
                        pmPage.setLocationRelativeTo(null);
                        pmPage.setVisible(true);


                    } else  {
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

    }

}