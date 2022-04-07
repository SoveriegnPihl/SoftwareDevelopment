package dtu.gui;
import dtu.employees.Developer;
import dtu.softwarehus.SoftwareHuset;
import javax.swing.*;

public class ProjectManagerPage {
    static Developer user1;
    manageProjectPage manageProjectPage;
    CreateActivityPage createActivityPage;
    static JPanel projectManagerPage;
    SoftwareHuset softwareHuset;
    Main parentWindow;
    static JComboBox<String> projectList;
    int yCountR=100,yCountL=100;

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

        JButton addDev = makeLeftButton("Add developer");
        JButton viewDev = makeLeftButton("View *available* developers");
        JButton addDevToProj = makeLeftButton("Add developer to project");
        JButton addActivityBut = makeLeftButton("Add activity to project");
        JButton getReport = makeLeftButton("Get project report");
        JButton backB = makeLeftButton("Back");
        JButton changeProjBut = makeRightButton("Change selected project");

        JLabel selectLabel = new JLabel();
        selectLabel.setText("Select project");
        selectLabel.setBounds(100, 25, 193, 29);
        projectManagerPage.add(selectLabel);

        viewDev.addActionListener(e -> {
            OptionPane OP = new OptionPane(user1,"View available developers");
        });

        changeProjBut.addActionListener(e -> {
            setVisible(false);
            manageProjectPage.setLabels(projectList.getItemAt(projectList.getSelectedIndex()));
            manageProjectPage.setVisible(true);
        });
        backB.addActionListener(e -> {
            setVisible(false);
            removeList();
            parentWindow.setVisible(true);
        });

        addActivityBut.addActionListener(e -> {
            setVisible(false);
            createActivityPage.setVisible(true);
        });

        addDevToProj.addActionListener(e -> {
            OptionPane OP = new OptionPane(user1,"Add developer to project");
        });

        addDev.addActionListener(e -> {
            OptionPane OP = new OptionPane(user1,"Add developer");
        });

        manageProjectPage = new manageProjectPage(softwareHuset,parentWindow);
        createActivityPage = new CreateActivityPage(softwareHuset, parentWindow);


    }
    public static void setVisible(boolean visi){
        projectManagerPage.setVisible(visi);
    }
   public static void createList(Developer user){
       String[] list = SoftwareHuset.projectListManagers(user).toArray(new String[0]);

       projectList = new JComboBox<>(list);
       projectList.setBounds(250, 25, 193, 29);
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


