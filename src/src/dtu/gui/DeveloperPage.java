package dtu.gui;//import required classes and packages
import dtu.employees.Developer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//create NewPage class to create a new page on which user will navigate  
public class DeveloperPage extends JFrame
{
    Developer user;
    JButton b1,b2,b3,b4,b5,b6,b7,b8;
    JPanel newPanel;
    JLabel userLabel;
    JTextField textField1;
    private JPanel panel1;
    private JCheckBox checkBox1;
    boolean managerCheckBox = false;
    //constructor  
    DeveloperPage(Developer user)
    {
        this.user = user;

        //create a welcome label and set it to the new page
        //create submit button

        b1 = new JButton("Register hours worked"); //set label to button
        b2 = new JButton("View hours worked"); //set label to button
        b3 = new JButton("Register sick day"); //set label to button
        b5 = new JButton("Assign project manager"); //set label to button
        b6 = new JButton("Add project activity"); //set label to button
        b7 = new JButton("Create project");
        b8 = new JButton("Other activities");

        //create panel to put form elements
        newPanel = new JPanel();
        newPanel.setLayout(null);
        newPanel.setBorder(BorderFactory.createTitledBorder(
                "Developer page"));
        b1.setBounds(50, 50, 193, 29);
        newPanel.add(b1);
        b2.setBounds(50, 100, 193, 29);
        newPanel.add(b2);

        b3.setBounds(50, 150, 193, 29);
        newPanel.add(b3);

        b6.setBounds(50, 200, 193, 29);
        newPanel.add(b6);

        b5.setBounds(325, 100, 193, 29);
        newPanel.add(b5);
        b7.setBounds(325, 50, 193, 29);
        newPanel.add(b7);
        b8.setBounds(325, 150, 193, 29);
        newPanel.add(b8);


        add(newPanel);


        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               OptionPane OP = new OptionPane(user,"Register hours worked");
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OptionPane OP = new OptionPane(user,"View hours worked");
            }
        });

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OptionPane OP = new OptionPane(user,"Register sick day");
            }
        });


        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OptionPane OP = new OptionPane(user,"Assign project manager");
            }
        });

        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                CreateProjectPage OP = new CreateProjectPage();
                OP.setSize(500,500);  //set size of the frame
                OP.setLocationRelativeTo(null);
                OP.setVisible(true);


            }
        });
        b8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                OtherActivity otherActivity = new OtherActivity();
                otherActivity.setSize(500,500);  //set size of the frame
                otherActivity.setLocationRelativeTo(null);
                otherActivity.setVisible(true);


            }
        });
        setDefaultCloseOperation(javax.swing.
                WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Welcome");
        setSize(600, 600);
    }
}  