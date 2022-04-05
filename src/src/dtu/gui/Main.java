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
    CreateProjectPage OP;
    //initialize button, panel, label, and text field
    JFrame frame;
    JButton b1,b2;
    JPanel panelMenu,newPanel;
    JLabel userLabel;
    JTextField textField1;
    private JPanel panel1;
    private JCheckBox checkBox1;
    boolean managerCheckBox = false;
    boolean b;
    Developer user;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main screen = new Main();
                    SoftwareHuset.startProgram();
                    screen.frame.setLocationRelativeTo(null);
                    screen.frame.setSize(500,500);
                    screen.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

    private void initialize(){
        frame = new JFrame();
        frame.setBounds(100, 100, 500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new CardLayout(0, 0));


        newPanel = new JPanel();
        frame.getContentPane().add(newPanel);
        newPanel.setLayout(null);
       // newPanel.setBorder(BorderFactory.createTitledBorder(
              //  "Main Menu"));

        //create label for username
        userLabel = new JLabel();
        userLabel.setText("Username");      //set label value for textField1

        //create text field to get username from the user
        textField1 = new JTextField(15);    //set length of the text

        //create submit button
        b1 = new JButton("Login"); //set label to button

        b2 = new JButton("Create project");


        //create checkbox
        checkBox1 = new JCheckBox("As project manager");
        userLabel.setBounds(75, 25, 193, 29);
        newPanel.add(userLabel);    //set username label to panel
        textField1.setBounds(225, 25, 193, 29);
        newPanel.add(textField1);   //set text field to panel
        checkBox1.setBounds(50, 55, 193, 29);
        newPanel.add(checkBox1);
        b1.setBounds(55, 100, 193, 45);
        newPanel.add(b1);
        b2.setBounds(255, 100, 193, 45);
        newPanel.add(b2);

        checkBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                managerCheckBox = !managerCheckBox;

            }
        });

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userValue = textField1.getText();        //get user entered username from the textField1
                DeveloperPage.setUser(SoftwareHuset.getDeveloper(userValue));
                ProjectManagerPage.setUser(SoftwareHuset.getDeveloper(userValue));

                if (SoftwareHuset.isDeveloper(userValue)) {
                    user = SoftwareHuset.getDeveloper(userValue);
                    if (managerCheckBox && SoftwareHuset.isManager(userValue)) {
                        //create instance of the NewPage
                        //make page visible to the user
                        setVisible(false);
                        ProjectManagerPage.createList(user);
                        projectManagerPage.setVisible(true);


                    } else if (!managerCheckBox)  {

                        setVisible(false);
                        developerPage.setVisible(true);

                    }

                }
            }
        });


        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                OP.setVisible(true);


            }
        });
        OP = new CreateProjectPage(softwareHuset,this);
        developerPage = new DeveloperPage(softwareHuset,this);
        projectManagerPage= new ProjectManagerPage(softwareHuset,this);

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

