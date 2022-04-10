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
    static Main screen;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                screen = new Main();
                SoftwareHuset.startProgram();
                frame.setLocationRelativeTo(null);
                setFrameSize(500,250);
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
        newPanel.add(userLabel);    //set username label to panel
        signInLabel.setBounds(25, 10, 193, 50);
        newPanel.add(signInLabel);

        textField1.setBounds(100, 50, 100, 29);
        newPanel.add(textField1);   //set text field to panel
        checkBox1.setBounds(25, 90, 193, 29);
        newPanel.add(checkBox1);
        loginBtn.setBounds(25, 130, 193, 45);
        newPanel.add(loginBtn);
        createDeveloperButton.setBounds(255, 25, 193, 45);
        newPanel.add( createDeveloperButton);
        createProjectBtn.setBounds(255, 75, 193, 45);
        newPanel.add(createProjectBtn);

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
                    ProjectManagerPage.createList(user);
                    ProjectManagerPage.setVisible(true); }
                else if (managerCheckBox) {
                    createMessage("Developer is not project manager");
                }
                if (!managerCheckBox)  {
                    Main.setFrameSize(500,500);
                    setVisible(false);
                    DeveloperPage.setVisible(true);
                }
                } else {
                createMessage("No such user found");
            }
        });



        createProjectBtn.addActionListener(e -> {
            setFrameSize(500,500);
            setVisible(false);
            newProjectPage.setVisible(true);


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

