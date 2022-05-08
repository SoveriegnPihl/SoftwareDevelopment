package dtu.gui;


import dtu.softwarehus.SoftwareHuset;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OtherActivity extends JFrame {
    //initialize button, panel, label, and text field
    JButton b1;
    JPanel newPanel;
    JLabel userLabel, userLabel2, userLabel3, userLabel4,userLabel5,userLabel6;
    JComboBox box1,box2,box3,box4,box5,box6,box7,box8;



    //calling constructor
    public OtherActivity() {

        //create label for hollidays

        userLabel = new JLabel();
        userLabel.setText("Holliday");
        userLabel.setBounds(100, 10, 193, 29);

        userLabel2 = new JLabel();
        userLabel2.setText("Start date");
        userLabel2.setBounds(25, 50, 193, 29);

        userLabel3 = new JLabel();
        userLabel3.setText("End date");
        userLabel3.setBounds(25, 100, 193, 29);

        //create label for workshops

        userLabel4 = new JLabel();
        userLabel4.setText("Workshops");
        userLabel4.setBounds(100, 150, 193, 29);

        userLabel5 = new JLabel();
        userLabel5.setText("Start date");
        userLabel5.setBounds(25, 190, 193, 29);

        userLabel6 = new JLabel();
        userLabel6.setText("End date");
        userLabel6.setBounds(25, 240, 193, 29);

        //create drop down menu to get date from the user
        String[] dates = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        box1 = new JComboBox(dates);
        box1.setBounds(100,50,65,29);


        //create drop down menu to get month from the user
        String[] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};
        box2 = new JComboBox(months);
        box2.setBounds(170,50,120,29);

        //create drop down menu to get date from the user
        box3 = new JComboBox(dates);
        box3.setBounds(100,100,65,29);

        //create drop down menu to get month from the user
        box4 = new JComboBox(months);
        box4.setBounds(170,100,120,29);

        //create drop down menu to get date from the user
        box5 = new JComboBox(dates);
        box5.setBounds(100,190,65,29);

        //create drop down menu to get month from the user
        box6 = new JComboBox(months);
        box6.setBounds(170,190,120,29);

        //create drop down menu to get date from the user
        box7 = new JComboBox(dates);
        box7.setBounds(100,240,65,29);

        //create drop down menu to get month from the user
        box8 = new JComboBox(months);
        box8.setBounds(170,240,120,29);





        //create submit button
        b1 = new JButton("Submit"); //set label to button
        b1.setBounds(150, 400, 193, 29);


        //create panel to put form elements
        newPanel = new JPanel();
        newPanel.setLayout(null);
        newPanel.add(box1);       //set username label to panel
        newPanel.add(box2);      //set text field to panel
        newPanel.add(box3);
        newPanel.add(box4);
        newPanel.add(box5);
        newPanel.add(box6);
        newPanel.add(box7);
        newPanel.add(box8);
        newPanel.add(userLabel);
        newPanel.add(userLabel2);
        newPanel.add(userLabel3);
        newPanel.add(userLabel4);
        newPanel.add(userLabel5);
        newPanel.add(userLabel6);
        newPanel.add(b1);

        //set button to panel
        //set border to panel
        add(newPanel);


        setTitle("Other activities");         //set title to the login form

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String startDateHolliday = box1.getSelectedItem().toString();        //get user entered username from the textField1
                String startMonthHolliday = box2.getSelectedItem().toString();
                String endDateHolliday = box3.getSelectedItem().toString();
                String endMonthHolliday = box4.getSelectedItem().toString();

                String startDateWorkshop = box5.getSelectedItem().toString();        //get user entered username from the textField1
                String startMonthWorkshop = box6.getSelectedItem().toString();
                String endDateWorkshop = box7.getSelectedItem().toString();
                String endMonthWorkshop = box8.getSelectedItem().toString();

                CreateLoginForm pmPage = new CreateLoginForm();
                //make page visible to the user
                setVisible(false);
                pmPage.setLocationRelativeTo(null);
                pmPage.setVisible(true);


            }

        });

    }
}