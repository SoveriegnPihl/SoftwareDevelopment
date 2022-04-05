package dtu.gui;
import dtu.employees.Developer;
import dtu.softwarehus.SoftwareHuset;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectManagerPage {
    static Developer user1;
    manageProjectPage manageProjectPage;
    JButton b1,b2,b3,b4,b5,b6,b7;
    static JPanel projectManagerPage;
    JLabel userLabel;
    JTextField textField1;
    private JPanel panel1;
    private JCheckBox checkBox1;
    boolean managerCheckBox = false;
    SoftwareHuset softwareHuset;
    Main parentWindow;
    static JComboBox projectList;
    //constructor
    ProjectManagerPage(SoftwareHuset softwareHuset, Main parentWindow) {
        this.softwareHuset = softwareHuset;
        this.parentWindow = parentWindow;
        initialize();
    }
    public void initialize(){

        projectManagerPage = new JPanel();
        parentWindow.addPanel(projectManagerPage);
        projectManagerPage.setLayout(null);
       // projectManagerPage.setBorder(BorderFactory.createTitledBorder(
        b1 = new JButton("Add developer"); //set label to button
        b2 = new JButton("View *available* developers"); //set label to button
        b4 = new JButton("Add developer to project"); //set label to button
        b5 = new JButton("Add project activity"); //set label to button
        b5 = new JButton("Get project report"); //set label to button
        b7 = new JButton("Back");

        b3 = new JButton("Create project"); //set label to button
        b6 = new JButton("Manage selected project"); //set label to button

        //create panel to put form elements

        b1.setBounds(25, 50, 193, 29);
        projectManagerPage.add(b1);
        b2.setBounds(25, 100, 193, 29);
        projectManagerPage.add(b2);
        b4.setBounds(25, 150, 193, 29);
        projectManagerPage.add(b4);
        b5.setBounds(25, 200, 193, 29);
        projectManagerPage.add(b5);
        b7.setBounds(25, 250, 193, 29);
        projectManagerPage.add(b7);

        b3.setBounds(275, 50, 193, 29);
       // projectManagerPage.add(b3);
        b6.setBounds(285, 50, 193, 29);
        projectManagerPage.add(b6);

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OptionPane OP = new OptionPane(user1,"View available developers");
            }
        });

        /*b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                CreateProjectPage OP = new CreateProjectPage();
                OP.setSize(500,500);  //set size of the frame
                OP.setLocationRelativeTo(null);
                OP.setVisible(true);
            }

        });*/

        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                manageProjectPage.setVisible(true);
                System.out.println(projectList.getItemAt(projectList.getSelectedIndex()));
            }

        });
        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                parentWindow.setVisible(true);
            }
        });
        manageProjectPage = new manageProjectPage(softwareHuset,parentWindow);


    }
    public void setVisible(boolean visi){
        projectManagerPage.setVisible(visi);
    }
   public static void createList(Developer user){
       String[] list = SoftwareHuset.projectList(user).toArray(new String[0]);

       projectList = new JComboBox(list);
       projectList.setBounds(285, 100, 193, 29);
       projectManagerPage.add(projectList);

    }

    public static void setUser(Developer user){
        user1 = user;
    }
}

