package dtu.gui;
import dtu.project.Developer;
import dtu.project.SoftwareHuset;
import javax.swing.*;

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
    int yCountL =50, yCountR = 50;
    private JPanel developerPage2;

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
        JButton addActBtn = makeLeftButton("Add activity to project");
        JButton backBtn = makeLeftButton("Back");

        JButton addPmBtn = makeRightButton("Assign project manager");
        JButton createDevBtn = makeRightButton("Create developer");


        regHoursBtn.addActionListener(e -> {
        RegisterHours registerHours = new RegisterHours(loggedInUser,parentWindow);
        setVisible(false);
        RegisterHours.createList(loggedInUser);
        registerHours.setVisible(true);
        });

       viewHoursBtn.addActionListener(e -> {
           ChangeHours changeHours = new ChangeHours(loggedInUser,parentWindow);
           setVisible(false);
           changeHours.createList(loggedInUser);
           changeHours.setVisible(true);
       });

        addPmBtn.addActionListener(e -> {
            //OptionPane OP = new OptionPane(loggedInUser, "Assign project manager");
            createAssignPM();
        });

        createDevBtn.addActionListener(e -> {
            OptionPane OP = new OptionPane(loggedInUser,"Add developer");
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
            createActivityPage.setVisible(true);
        });

        regHoliBtn.addActionListener(e -> {
            setVisible(false);
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
            developerPage2.setBorder(BorderFactory.createTitledBorder("Developer Page 2"));
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

            String[] list = SoftwareHuset.fullProjectList().toArray(new String[0]);
            JComboBox<String> projectCombo = new JComboBox<>(list);
            projectCombo.setBounds(250, 100, 193, 29);
            developerPage2.add(projectCombo);

            JLabel selProject = new JLabel();
            selProject.setText("Select project");
            selProject.setBounds(25, 100, 193, 29);
            developerPage2.add(selProject);

            JButton b1 = new JButton("Save");
            b1.setBounds(140,200, 250, 50);
            developerPage2.add(b1);
            b1.addActionListener(e -> {
                Main.setFrameSize(500,500);
                SoftwareHuset.assignPM((String) developerCombo.getSelectedItem(), Integer.parseInt((String) projectCombo.getSelectedItem()));
                developerPage2.setVisible(false);
                developerPage.setVisible(true);
            });

            JButton b2 = new JButton("Back");
            b2.setBounds(140,250, 250, 50);
            developerPage2.add(b2);
            b2.addActionListener(e -> {
                            Main.setFrameSize(500,500);
                            developerPage2.setVisible(false);
                            developerPage.setVisible(true);
                        });



            developerPage2.setVisible(true);

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