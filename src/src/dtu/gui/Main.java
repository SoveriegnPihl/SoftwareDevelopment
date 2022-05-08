package dtu.gui;

import dtu.employees.Developer;
import dtu.softwarehus.SoftwareHuset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// lavet af Victor Larsen-Saldeen

public class Main {
    SoftwareHuset softwareHuset;
    DeveloperPage developerPage;
    ProjectManagerPage projectManagerPage;
    CreateProjectPage newProjectPage;
    //initialize button, panel, label, and text field
    static JFrame frame;
    JButton loginBtn, createProjectBtn;
    JPanel rightPanel;
    JLabel userLabel;
    JTextField textField1;
    private JPanel panel1;
    boolean managerCheckBox = false;
    Developer user;
    static Main screen;
    private JPanel leftPanel;
    JPanel mainPanel;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                screen = new Main();
                SoftwareHuset.startProgram();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    static void setFrameSize(int i, int i1) {
        frame.setSize(i,i1);
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
        userLabel.setText("Initials");

        JLabel signInLabel = new JLabel();
        signInLabel.setText("Sign in");

        //create text field to get username from the user
        textField1 = new JTextField(15);    //set length of the text

        //create submit button
        loginBtn = new JButton("Login"); //set label to button
        JButton createDeveloperButton = new JButton("Create developer");
        createProjectBtn = new JButton("Create project");


        //create checkbox
        JCheckBox checkBox1 = new JCheckBox("As project manager");
        userLabel.setBounds(25, 50, 193, 29);
        rightPanel.add(userLabel);    //set username label to panel
        signInLabel.setBounds(25, 10, 193, 50);
        rightPanel.add(signInLabel);

        textField1.setBounds(100, 50, 100, 29);
        rightPanel.add(textField1);   //set text field to panel
        checkBox1.setBounds(25, 90, 193, 29);
        rightPanel.add(checkBox1);
        loginBtn.setBounds(25, 130, 193, 45);
        rightPanel.add(loginBtn);
        createDeveloperButton.setBounds(25, 40, 193, 45);
        leftPanel.add( createDeveloperButton);
        createProjectBtn.setBounds(25, 100, 193, 45);
        leftPanel.add(createProjectBtn);

        checkBox1.addActionListener(e -> managerCheckBox = !managerCheckBox);

        loginBtn.addActionListener(e -> {
            String userValue = textField1.getText();        //get user entered username from the textField1
            DeveloperPage.setUser(SoftwareHuset.getDeveloper(userValue));
            ProjectManagerPage.setUser(SoftwareHuset.getDeveloper(userValue));


            if (SoftwareHuset.isDeveloper(userValue)) {
                user = SoftwareHuset.getDeveloper(userValue);
                if (managerCheckBox && SoftwareHuset.isManager(userValue)) {
                    Main.setFrameSize(500,500);
                    setVisible(false);
                    Main.setLocation();
                    ProjectManagerPage.createList(user);
                    ProjectManagerPage.setVisible(true);
                }
                else if (managerCheckBox) {
                    createMessage("Developer is not project manager");
                }
                if (!managerCheckBox)  {
                    Main.setFrameSize(500,500);
                    setVisible(false);
                    DeveloperPage.setVisible(true);
                    Main.setLocation();
                }
                } else {
                createMessage("No such user found");
            }
        });



        createProjectBtn.addActionListener(e -> {
            setFrameSize(500,500);
            setVisible(false);
            newProjectPage.setVisible(true);
            Main.setLocation();


        });
        createDeveloperButton.addActionListener(e -> {
            OptionPane OP = new OptionPane("Add developer");



        });
        newProjectPage = new CreateProjectPage(softwareHuset,this);
        developerPage = new DeveloperPage(softwareHuset, this);
        projectManagerPage= new ProjectManagerPage(softwareHuset,this);

    }
    private void createPage() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(new CardLayout(0, 0));

        mainPanel = new JPanel();
        frame.getContentPane().add(mainPanel);
        mainPanel.setLayout(null);
        mainPanel.setBorder(BorderFactory.createTitledBorder("Main page"));
        rightPanel = new JPanel();
        rightPanel.setBounds(25,25,250,300);
        mainPanel.add(rightPanel);
        rightPanel .setBorder(BorderFactory.createTitledBorder("Login"));
        rightPanel.setLayout(null);
        leftPanel = new JPanel();
        mainPanel.add(leftPanel);
        leftPanel.setBounds(325,25,250,300);
        leftPanel.setLayout(null);
        leftPanel .setBorder(BorderFactory.createTitledBorder("Functions"));

        //frame.pack();
    }
    public static void createMessage(String message){
        JFrame alertFrame = new JFrame();
        alertFrame.setLocationRelativeTo(null);
        JOptionPane.showMessageDialog(alertFrame, message);
    }
    public static void setLocation(){
        frame.setLocationRelativeTo(null);
    }

    public void setVisible(boolean setVisi){
        mainPanel.setVisible(setVisi);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void addPanel (JPanel panel ){
        frame.getContentPane().add(panel);
    }

    }

