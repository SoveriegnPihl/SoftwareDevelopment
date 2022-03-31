package dtu.softwarehus;
//import required classes and packages
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//create CreateLoginForm class to create login form
//class extends JFrame to create a window where our component add
//class implements ActionListener to perform an action on button click
class CreateLoginForm extends JFrame  {
    //initialize button, panel, label, and text field
    JButton b1;
    JPanel newPanel;
    JLabel userLabel, passLabel;
    JTextField textField1;
    private JPanel panel1;
    private JCheckBox checkBox1;
    boolean managerCheckBox = false;

    //calling constructor
    CreateLoginForm() {

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
                if (!managerCheckBox) {
                    managerCheckBox = true;
                } else {
                    managerCheckBox = false;
                }
            }
        });

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userValue = textField1.getText();        //get user entered username from the textField1
                SoftwareHuset softwareHuset = new SoftwareHuset();
                if(managerCheckBox && softwareHuset.isDeveloper(userValue)){
                    //create instance of the NewPage
                    ProjectManagerPage pmPage = new ProjectManagerPage(userValue);

                    //make page visible to the user
                    pmPage.setVisible(true);

                    //create a welcome label and set it to the new page
                    JLabel wel_label = new JLabel("Welcome project manager: " + userValue);
                    pmPage.getContentPane().add(wel_label);
                } else if (softwareHuset.isDeveloper(userValue)) {
                    //check whether the credentials are authentic or not

                    //create instance of the NewPage
                    DeveloperPage dPage = new DeveloperPage(userValue);

                    //make page visible to the user
                    dPage.setVisible(true);

                }
            }
        });

/*
        //define abstract method actionPerformed() which will be called on button click
        //perform action on button click
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)     //pass action listener as a parameter
            {
                String userValue = textField1.getText();        //get user entered username from the textField1

                //check whether the credentials are authentic or not

                //create instance of the NewPage
                NewPage page = new NewPage();

                //make page visible to the user
                page.setVisible(true);

                //create a welcome label and set it to the new page
                JLabel wel_label = new JLabel("Welcome: " + userValue);
                page.getContentPane().add(wel_label);

            }

        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }*/
    }
}