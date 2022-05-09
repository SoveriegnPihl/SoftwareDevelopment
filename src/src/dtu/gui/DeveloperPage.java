package dtu.gui;
import dtu.project.Developer;
import dtu.project.Project;
import dtu.project.SoftwareHuset;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// lavet af Victor Larsen-Saldeen

public class DeveloperPage {
    static Developer loggedInUser;
    static JPanel developerPage;
    JFrame frame;
    SoftwareHuset softwareHuset;
    Main parentWindow;
    CreateActivityPage createActivityPage;
    CreateProjectPage createProjectPage;
    RegisterHoliday registerHoliday;
    int yCountL =30, yCountR = 30;
    private JPanel developerPage2;
    static JPanel topPanel;
    private JPanel bottomPanel;

    DeveloperPage(SoftwareHuset softwareHuset, Main parentWindow) {
        this.softwareHuset = softwareHuset;
        this.parentWindow = parentWindow;
        initialize();
    }

    public void initialize()  {

        createPage();

        JButton regHoursBtn = makeLeftButton("Register hours worked");
        JButton viewHoursBtn = makeLeftButton("View hours worked");
        JButton regSickBtn = makeLeftButton("Register sick day");
        JButton regHoliBtn = makeLeftButton("Register holiday");

        JButton addActBtn = makeRightButton("Add activity to project");
        JButton addPmBtn = makeRightButton("Assign project manager");
        JButton createDevBtn = makeRightButton("Create developer");
        JButton backBtn = makeRightButton("Back");

        regHoursBtn.addActionListener(e -> {
        RegisterHours registerHours = new RegisterHours(loggedInUser,parentWindow);
        setVisible(false);
        Main.setFrameSize(500,400);
        RegisterHours.createList(loggedInUser);
        registerHours.setVisible(true);
        });

        JLabel welcomeLabel = new JLabel("Developer page");
        welcomeLabel.setFont(new Font("cambria",Font.PLAIN,30));
        welcomeLabel.setBounds(130,5,400,70);
        topPanel.add(welcomeLabel);
       viewHoursBtn.addActionListener(e -> {
           ChangeHours changeHours = new ChangeHours(loggedInUser,parentWindow);
           setVisible(false);
           changeHours.createList(loggedInUser);
           changeHours.setVisible(true);
       });

        addPmBtn.addActionListener(e -> {
            Main.setFrameSize(500,300);
            createAssignPM();
        });

        createDevBtn.addActionListener(e -> {
            OptionPane OP = new OptionPane("Add developer");
        });

        backBtn.addActionListener(e -> {
            Main.setFrameSize(600,400);
            Main.setLocation();
            setVisible(false);
            parentWindow.setVisible(true);
        });

        addActBtn.addActionListener(e -> {
            setVisible(false);
            createActivityPage.setOriginWindow("DeveloperPage");
            createActivityPage.setProjs();
            createActivityPage.setVisible(true);
        });

        regHoliBtn.addActionListener(e -> {
            setVisible(false);
            Main.setFrameSize(450,400);
            registerHoliday.setVisible(true);
        });

        regSickBtn.addActionListener(e -> {
            new OptionPane(loggedInUser,"Register sick day");
        });

        createActivityPage = new CreateActivityPage(softwareHuset, parentWindow);
        createProjectPage = new CreateProjectPage(softwareHuset, parentWindow);
        registerHoliday = new RegisterHoliday(softwareHuset, parentWindow);
    }

    private void createPage() {
        developerPage = new JPanel();
        parentWindow.addPanel(developerPage);
        developerPage.setLayout(null);
        developerPage.setBorder(BorderFactory.createTitledBorder("Developer Page"));
        topPanel = new JPanel();
        topPanel.setBounds(15,25,450,75);
        developerPage.add(topPanel);
        topPanel .setBorder(BorderFactory.createTitledBorder("Welcome"));
        topPanel.setLayout(null);
        bottomPanel = new JPanel();
        developerPage.add(bottomPanel);
        bottomPanel.setBounds(15,125,450,325);
        bottomPanel.setLayout(null);
        bottomPanel.setBorder(BorderFactory.createTitledBorder("Use functionality"));
    }

    public static void setVisible(boolean visi){
            developerPage.setVisible(visi);
        }

        public static void setUser(Developer user){
        loggedInUser = user;
        }

        public void createAssignPM(){
            setVisible(false);
            developerPage2 = new JPanel();
            parentWindow.addPanel(developerPage2);
            developerPage2.setLayout(null);
            developerPage2.setBorder(BorderFactory.createTitledBorder("Assign A Project Manager"));
            JLabel selDev = new JLabel();
            selDev.setText("Select developer to assign");      //set label value for textField1
            selDev.setBounds(25, 50, 193, 29);
            developerPage2.add(selDev);

            JComboBox<Object> developerCombo = new JComboBox<>();
            for (String developer : SoftwareHuset.developers.keySet()) {
                developerCombo.addItem(developer);
            }

            developerCombo.setBounds(250, 50, 193, 29);
            developerPage2.add(developerCombo);

            String[] list = fullProjectList().toArray(new String[0]);
            JComboBox<String> projectCombo = new JComboBox<>(list);
            projectCombo.setBounds(250, 100, 193, 29);
            developerPage2.add(projectCombo);

            JLabel selProject = new JLabel();
            selProject.setText("Select project");
            selProject.setBounds(65, 100, 193, 29);
            developerPage2.add(selProject);

            JButton b1 = new JButton("Save");
            b1.setBounds(190,200, 193, 29);
            developerPage2.add(b1);
            b1.addActionListener(e -> {
                Main.setFrameSize(500,500);
                SoftwareHuset.assignPM((String) developerCombo.getSelectedItem(), Integer.parseInt((String) projectCombo.getSelectedItem()));
                developerPage2.setVisible(false);
                developerPage.setVisible(true);
            });

            JButton b2 = new JButton("Back");
            b2.setBounds(45,200, 70, 29);
            developerPage2.add(b2);
            b2.addActionListener(e -> {
                            Main.setFrameSize(500,500);
                            developerPage2.setVisible(false);
                            Main.setFrameSize(500,500);
                            developerPage.setVisible(true);
                        });



            developerPage2.setVisible(true);

        }

    public JButton makeLeftButton(String name){
        JButton b1 = new JButton(name);
        b1.setBounds(15, yCountL, 193, 29);
        bottomPanel.add(b1);
        yCountL+=50;
        return b1;
    }
    public JButton makeRightButton(String name){
        JButton b1 = new JButton(name);
        b1.setBounds(245, yCountR, 193, 29);
        bottomPanel.add(b1);
        yCountR+=50;
        return b1;
    }

    public void addPanel (JPanel panel ){
        frame.getContentPane().add(panel);
    }

    public static ArrayList<String> fullProjectList(){
        ArrayList<String> projectlist = new ArrayList<>();
        for (Project project : SoftwareHuset.projects.values()){
            projectlist.add(String.valueOf(project.getId()));
        }
        return projectlist;
    }
}  