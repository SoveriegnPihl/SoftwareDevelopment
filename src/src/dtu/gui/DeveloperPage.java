package dtu.gui;//import required classes and packages
import dtu.employees.Developer;
import dtu.softwarehus.SoftwareHuset;
import javax.swing.*;
import java.awt.*;

//create NewPage class to create a new page on which user will navigate  
public class DeveloperPage {
    static Developer loggedInUser;
    static JPanel developerPage;
    JFrame frame;
    SoftwareHuset softwareHuset;
    Main parentWindow;
    CreateActivityPage createActivityPage;
    CreateProjectPage createProjectPage;
    RegisterHoliday registerHoliday;
    int yCountL =50, yCountR = 50;

    DeveloperPage(SoftwareHuset softwareHuset, Main parentWindow) {
        this.softwareHuset = softwareHuset;
        this.parentWindow = parentWindow;
        initialize();
    }

    public void initialize()  {
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


        regHbtn.addActionListener(e -> {
        RegisterHours registerHours = new RegisterHours(loggedInUser,parentWindow);
        setVisible(false);
        RegisterHours.createList(loggedInUser);
        registerHours.setVisible(true);
        });

       viewHbtn.addActionListener(e -> {
            new OptionPane(loggedInUser, "View hours worked");
       });

        addPmBtn.addActionListener(e -> {
            new OptionPane(loggedInUser, "Assign project manager");
        });

        backBtn.addActionListener(e -> {
            setVisible(false);
            parentWindow.setVisible(true);
        });

        addActBtn.addActionListener(e -> {
            setVisible(false);
            createActivityPage.setVisible(true);
        });

        regHoliBtn.addActionListener(e -> {
            setVisible(false);
            registerHoliday.setVisible(true);
        });

        regSickBtn.addActionListener(e -> {
            new OptionPane(loggedInUser,"Register sick day");
        });

        createActivityPage = new CreateActivityPage(softwareHuset,parentWindow);
        createProjectPage = new CreateProjectPage(softwareHuset, parentWindow);
        registerHoliday = new RegisterHoliday(softwareHuset, parentWindow);
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