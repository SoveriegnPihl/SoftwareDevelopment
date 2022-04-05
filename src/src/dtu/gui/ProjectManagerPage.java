package dtu.gui;
import dtu.employees.Developer;
import dtu.softwarehus.SoftwareHuset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectManagerPage extends JFrame {
    Developer user;
    JButton b1,b2,b3,b4,b5,b6;
    JPanel newPanel;
    JLabel userLabel;
    JTextField textField1;
    private JPanel panel1;
    private JCheckBox checkBox1;
    boolean managerCheckBox = false;
    //constructor
    ProjectManagerPage(Developer user)
    {
        this.user = user;
        //create a welcome label and set it to the new page
        //create submit button

        b1 = new JButton("Add developer"); //set label to button
        b2 = new JButton("View *available* developers"); //set label to button
        b4 = new JButton("Add developer to project"); //set label to button
        b5 = new JButton("Add project activity"); //set label to button
        b5 = new JButton("Get project report"); //set label to button

        b3 = new JButton("Create project"); //set label to button
        b6 = new JButton("Modify project"); //set label to button

        //create panel to put form elements
        newPanel = new JPanel();
        newPanel.setLayout(null);
        newPanel.setBorder(BorderFactory.createTitledBorder(
                "Manager page"));
        b1.setBounds(50, 50, 193, 29);
        newPanel.add(b1);
        b2.setBounds(50, 100, 193, 29);
        newPanel.add(b2);
        b4.setBounds(50, 150, 193, 29);
        newPanel.add(b4);
        b5.setBounds(50, 200, 193, 29);
        newPanel.add(b5);

        b3.setBounds(350, 50, 193, 29);
        newPanel.add(b3);
        b6.setBounds(350, 100, 193, 29);
        newPanel.add(b6);

        String[] list = SoftwareHuset.projectList(user);

        JComboBox projectList = new JComboBox(list);
        projectList.setSelectedIndex(list.length);
        projectList.setBounds(350, 150, 193, 29);
        newPanel.add(projectList);

        add(newPanel);



        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OptionPane OP = new OptionPane(user,"View available developers");
            }
        });

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                CreateProjectPage OP = new CreateProjectPage();
                OP.setSize(500,500);  //set size of the frame
                OP.setLocationRelativeTo(null);
                OP.setVisible(true);
            }

        });



        setDefaultCloseOperation(javax.swing.
                WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Welcome");
        setSize(600, 500);
    }
}

