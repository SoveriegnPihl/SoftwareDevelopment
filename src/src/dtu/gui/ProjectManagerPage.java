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
    private JLabel selectLabel;
    int yCountR=100,yCountL=100;
    private JButton addDev,viewDev,addDevProj,addActi,backB,getReport,createProjBut,changeProjBut;

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
        addDev = makeLeftButton("Add developer");
        viewDev = makeLeftButton("View *available* developers");
        addDevProj = makeLeftButton("Add developer to project");
        addActi = makeLeftButton("Add project activity");
        getReport = makeLeftButton("Get project report");
        backB = makeLeftButton("Back");

        //createProjBut = makeRightButton("Create project"); //set label to button
        changeProjBut = makeRightButton("Change selected project"); //set label to button

        selectLabel = new JLabel();
        selectLabel.setText("Select project");
        selectLabel.setBounds(100, 25, 193, 29);
        projectManagerPage.add(selectLabel);

        viewDev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OptionPane OP = new OptionPane(user1,"View available developers");
            }
        });

        changeProjBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                manageProjectPage.setLabels(projectList.getItemAt(projectList.getSelectedIndex()).toString());
                manageProjectPage.setVisible(true);
            }

        });
        backB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                removeList();
                parentWindow.setVisible(true);
            }
        });
        manageProjectPage = new manageProjectPage(softwareHuset,parentWindow);

    }
    public static void setVisible(boolean visi){
        projectManagerPage.setVisible(visi);
    }
   public static void createList(Developer user){
       String[] list = SoftwareHuset.projectList(user).toArray(new String[0]);

       projectList = new JComboBox(list);
       projectList.setBounds(225, 25, 193, 29);
       projectManagerPage.add(projectList);

    }
    public static void removeList(){
        projectManagerPage.remove(projectList);

    }

    public static void setUser(Developer user){
        user1 = user;
    }
    public JButton makeLeftButton(String name){
        JButton b1 = new JButton(name);
        b1.setBounds(25, yCountL, 193, 29);
        projectManagerPage.add(b1);
        yCountL+=50;
        return b1;
    }
    public JButton makeRightButton(String name){
        JButton b1 = new JButton(name);
        b1.setBounds(285, yCountR, 193, 29);
        projectManagerPage.add(b1);
        yCountR+=50;
        return b1;
    }

}


