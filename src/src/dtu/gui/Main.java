package dtu.gui;

import dtu.employees.Developer;
import dtu.softwarehus.SoftwareHuset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    SoftwareHuset softwareHuset;
    DeveloperPage developerPage;
    ProjectManagerPage projectManagerPage;
    CreateProjectPage newProjectPage;
    //initialize button, panel, label, and text field
    static JFrame frame;
    JButton loginBtn, createProjectBtn;
    JPanel newPanel;
    JLabel userLabel;
    JTextField textField1;
    private JPanel panel1;
    boolean managerCheckBox = false;
    Developer user;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Main screen = new Main();
                SoftwareHuset.startProgram();
                screen.frame.setLocationRelativeTo(null);
               // screen.frame.setSize(500,500);
                screen.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the application.
     * @throws Exception
     */

    public Main() throws Exception {
    softwareHuset = new SoftwareHuset();
    initialize();
    }

    private void initialize() throws Exception {
        createPage();

        //create label for username
        userLabel = new JLabel();
        userLabel.setText("Username");      //set label value for textField1

        //create text field to get username from the user
        textField1 = new JTextField(15);    //set length of the text

        //create submit button
        loginBtn = new JButton("Login"); //set label to button

        createProjectBtn = new JButton("Create project");


        //create checkbox
        JCheckBox checkBox1 = new JCheckBox("As project manager");
        userLabel.setBounds(75, 25, 193, 29);
        newPanel.add(userLabel);    //set username label to panel
        textField1.setBounds(225, 25, 193, 29);
        newPanel.add(textField1);   //set text field to panel
        checkBox1.setBounds(50, 55, 193, 29);
        newPanel.add(checkBox1);
        loginBtn.setBounds(55, 100, 193, 45);
        newPanel.add(loginBtn);
        createProjectBtn.setBounds(255, 100, 193, 45);
        newPanel.add(createProjectBtn);

        checkBox1.addActionListener(e -> managerCheckBox = !managerCheckBox);

        loginBtn.addActionListener(e -> {
            String userValue = textField1.getText();        //get user entered username from the textField1
            DeveloperPage.setUser(SoftwareHuset.getDeveloper(userValue));
            ProjectManagerPage.setUser(SoftwareHuset.getDeveloper(userValue));


            if (SoftwareHuset.isDeveloper(userValue)) {
                user = SoftwareHuset.getDeveloper(userValue);
                if (managerCheckBox && SoftwareHuset.isManager(userValue)) {
                    setVisible(false);
                    ProjectManagerPage.createList(user);
                    ProjectManagerPage.setVisible(true); }
                else if (managerCheckBox) {
                    createMessage("Developer is not project manager");
                }
                if (!managerCheckBox)  {
                    setVisible(false);
                    DeveloperPage.setVisible(true);
                }
                } else {
                createMessage("No such user found");
            }
        });



        createProjectBtn.addActionListener(e -> {
            setVisible(false);
            newProjectPage.setVisible(true);


        });
        newProjectPage = new CreateProjectPage(softwareHuset,this);
        developerPage = new DeveloperPage(softwareHuset, this);
        projectManagerPage= new ProjectManagerPage(softwareHuset,this);

    }
    private void createPage() {
        frame = new JFrame();
        frame.setBounds(100, 100, 500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new CardLayout(0, 0));
        newPanel = new JPanel();
        frame.getContentPane().add(newPanel);
        newPanel .setLayout(null);
        newPanel .setBorder(BorderFactory.createTitledBorder("Main page"));
    }
    public static void createMessage(String message){
        JFrame alertFrame = new JFrame();
        alertFrame.setLocationRelativeTo(null);
        JOptionPane.showMessageDialog(alertFrame, message);
    }


    public void setVisible(boolean setVisi){
        newPanel.setVisible(setVisi);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void addPanel (JPanel panel ){
        frame.getContentPane().add(panel);
    }

    }

