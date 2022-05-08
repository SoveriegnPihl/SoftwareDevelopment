package dtu.gui;
import dtu.project.Developer;
import dtu.project.Project;
import dtu.project.SoftwareHuset;
import javax.swing.*;
import java.util.ArrayList;

// lavet af Victor Larsen-Saldeen

public class ProjectManagerPage {
    static Developer loggedInUser;
    manageProjectPage manageProjectPage;
    CreateActivityPage createActivityPage;
    getReportPage getReportPage;
    static JPanel projectManagerPage;
    SoftwareHuset softwareHuset;
    Main parentWindow;
    static JComboBox<String> projectList;
    int yCountR=100,yCountL=100;
    private JFrame frame;
    private JPanel pmPage2;

    //constructor
    ProjectManagerPage(SoftwareHuset softwareHuset, Main parentWindow) {
        this.softwareHuset = softwareHuset;
        this.parentWindow = parentWindow;
        initialize();
    }
    public void initialize(){
        createPage();

        JButton addDevBtn = makeLeftButton("Add developer");
        JButton viewAvailableDevBtn = makeLeftButton("Available developers");
        JButton addDevToProjBtn = makeLeftButton("Add developer to project");
        JButton addActivityBtn = makeLeftButton("Add activity to project");
        JButton getReportBtn = makeLeftButton("Get project report");
        JButton backBtn = makeLeftButton("Back");
        JButton changeProjBtn = makeRightButton("Change selected project");

        JLabel selectLabel = new JLabel();
        selectLabel.setText("Select project");
        selectLabel.setBounds(100, 25, 193, 29);
        projectManagerPage.add(selectLabel);

        viewAvailableDevBtn.addActionListener(e -> {
            new OptionPane(loggedInUser,"View available developers");
        });

        changeProjBtn.addActionListener(e -> {
            setVisible(false);
            manageProjectPage.setLabels(projectList.getItemAt(projectList.getSelectedIndex()));
            manageProjectPage.setVisible(true);
        });

        getReportBtn.addActionListener(e -> {
            setVisible(false);
            Main.setFrameSize(400,400);
            getReportPage.setProject(projectList.getItemAt(projectList.getSelectedIndex()));
            getReportPage.setVisible(true);
        });

        backBtn.addActionListener(e -> {
            setVisible(false);
            removeList();
            Main.setFrameSize(600,400);
            parentWindow.setVisible(true);
        });

        addActivityBtn.addActionListener(e -> {
            setVisible(false);
            createActivityPage.setOriginWindow("ProjectManagerPage");
            createActivityPage.setVisible(true);
        });

        addDevToProjBtn.addActionListener(e -> {
          //  OptionPane OP = new OptionPane(loggedInUser,"Add developer to project");
           createAddDev();

        });

        addDevBtn.addActionListener(e -> {
            new OptionPane("Add developer");
        });

        manageProjectPage = new manageProjectPage(softwareHuset,parentWindow);
        createActivityPage = new CreateActivityPage(softwareHuset, parentWindow);
        getReportPage = new getReportPage(softwareHuset, parentWindow);


    }
    private void createPage() {
        projectManagerPage = new JPanel();
        parentWindow.addPanel(projectManagerPage);
        projectManagerPage.setLayout(null);
        projectManagerPage.setBorder(BorderFactory.createTitledBorder("Project manager page"));
    }
    public void createAddDev(){
        setVisible(false);
        pmPage2 = new JPanel();
        Main.setFrameSize(400,250);
        parentWindow.addPanel(pmPage2);
        pmPage2.setLayout(null);
        pmPage2.setBorder(BorderFactory.createTitledBorder("Add developer to project"));
        JLabel selDev = new JLabel();
        selDev.setText("Select developer to add:");      //set label value for textField1
        selDev.setBounds(15, 50, 193, 29);
        pmPage2.add(selDev);

        JComboBox<Object> developerCombo = new JComboBox<>();
        for (String developer : SoftwareHuset.developers.keySet()) {
            developerCombo.addItem(developer);
        }

        developerCombo.setBounds(180, 50, 193, 29);
        pmPage2.add(developerCombo);


        JButton b1 = new JButton("Save");
        b1.setBounds(150,150, 193, 29);
        pmPage2.add(b1);
        b1.addActionListener(e -> {

            Developer developer = SoftwareHuset.developers.get((String) developerCombo.getSelectedItem());
            Project project = SoftwareHuset.projects.get(Integer.parseInt((String) projectList.getSelectedItem()));
            project.addDeveloper(developer);

            pmPage2.setVisible(false);
            projectManagerPage.setVisible(true);
        });

        JButton b2 = new JButton("Back");
        b2.setBounds(47,150, 70, 29);
        pmPage2.add(b2);
        b2.addActionListener(e -> {
            Main.setFrameSize(500,500);
            pmPage2.setVisible(false);
            projectManagerPage.setVisible(true);
        });



        pmPage2.setVisible(true);

    }

    public static void setVisible(boolean visi){
        projectManagerPage.setVisible(visi);
    }

    public static void createList(Developer user){
       String[] list = projectListManagers(user).toArray(new String[0]);

       projectList = new JComboBox<>(list);
       projectList.setBounds(250, 25, 193, 29);
       projectManagerPage.add(projectList);

    }
    public static void removeList(){
        projectManagerPage.remove(projectList);

    }

    public static void setUser(Developer user){
        loggedInUser = user;
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
    public static ArrayList<String> projectListManagers(Developer developer){
        ArrayList<String> projectlist = new ArrayList<>();
        String name = developer.getInitials();
        for (Integer var : SoftwareHuset.projectManagers.keySet()){
            if (SoftwareHuset.projectManagers.get(var).equals(name)){
                projectlist.add(var.toString());
            }
        }
        return projectlist;
    }

}


