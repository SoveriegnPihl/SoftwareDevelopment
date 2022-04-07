package dtu.gui;//import required classes and packages
import dtu.employees.Developer;

import dtu.softwarehus.SoftwareHuset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//create NewPage class to create a new page on which user will navigate  
public class DeveloperPage {
    static Developer loggedInUser;
    static JPanel developerPage;
    JFrame frame;
    SoftwareHuset softwareHuset;
    Main parentWindow;
    CreateActivityPage createActivityPage;
    CreateProjectPage createProjectPage;
    int yCountL =50, yCountR = 50;

    DeveloperPage(SoftwareHuset softwareHuset, Main parentWindow) throws Exception {
        this.softwareHuset = softwareHuset;
        this.parentWindow = parentWindow;
        initialize();
    }

    public void initialize() throws Exception {
        frame = new JFrame();
        frame.setBounds(100, 100, 500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new CardLayout(0, 0));

        developerPage = new JPanel();
        frame.getContentPane().add(developerPage);
        parentWindow.addPanel(developerPage);
        developerPage.setLayout(null);
        developerPage.setBorder(BorderFactory.createTitledBorder("Developer page"));

        JButton regHbtn = makeLeftButton("Register hours worked");
        JButton viewHbtn = makeLeftButton("View hours worked");
        JButton regSickBtn = makeLeftButton("Register sick day");
        JButton regHoliBtn = makeLeftButton("Register holiday");
        JButton addActBtn = makeLeftButton("Add activity to project");
        JButton backBtn = makeLeftButton("Back");

        JButton addPmBtn = makeRightButton("Assign project manager");


        regHbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OptionPane OP = new OptionPane(loggedInUser, "Register hours worked");

            }
        });

       viewHbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OptionPane OP = new OptionPane(loggedInUser, "View hours worked");
            }
        });

        addPmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OptionPane OP = new OptionPane(loggedInUser, "Assign project manager");
            }
        });

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                parentWindow.setVisible(true);
            }
        });

        addActBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                createActivityPage.setVisible(true);

            }
        });

        regHoliBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //holiday

            }
        });

        regSickBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OptionPane OP = new OptionPane(loggedInUser,"Register sick day");

            }
        });

        createActivityPage = new CreateActivityPage(softwareHuset,parentWindow);
        createProjectPage = new CreateProjectPage(softwareHuset, parentWindow);

    }
        public static void setVisible(boolean visi){
            developerPage.setVisible(visi);
        }

        public static void setUser(Developer user){
        loggedInUser = user;
        }

    public JButton makeLeftButton(String name){
        JButton b1 = new JButton(name);
        b1.setBounds(25, yCountL, 193, 29);
        developerPage.add(b1);
        yCountL+=50;
        return b1;
    }
    public JButton makeRightButton(String name){
        JButton b1 = new JButton(name);
        b1.setBounds(285, yCountR, 193, 29);
        developerPage.add(b1);
        yCountR+=50;
        return b1;
    }

    public void addPanel (JPanel panel ){
        frame.getContentPane().add(panel);
    }

}  