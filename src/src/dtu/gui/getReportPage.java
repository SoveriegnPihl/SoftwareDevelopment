package dtu.gui;
import dtu.project.Project;
import dtu.project.SoftwareHuset;

import java.text.DecimalFormat;

import javax.swing.*;


public class getReportPage {
    JFrame frame;
    Project projectToManage;
    JButton saveBtn;
    static JComboBox<Object> developerList, activityList;
    JPanel getReportPanel;
    JLabel projectIDLabel, timeLabel, budgetLabel, devListLabel, actListLabel, doneActListLabel;
    JPanel devListPanel, actListPanel, doneActListPanel;
    SoftwareHuset softwareHuset;
    Main parentWindow;

    public getReportPage(SoftwareHuset softwareHuset, Main parentWindow) {
        this.softwareHuset = softwareHuset;
        this.parentWindow = parentWindow;
        initialize();
    }
    public void initialize(){
        createPage();

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e -> {
            setVisible(false);
            clear();
            ProjectManagerPage.setVisible(true);
        });
        btnBack.setBounds(21, 300, 150, 50);
        getReportPanel.add(btnBack);

        //adding labels to screen
        addLabelsToScreen();

        //adding textfields to screen
        addTextFieldsToScreen();

        //adding save button
        saveBtn = new JButton("Save"); //set label to button
        saveBtn.setBounds(220, 300, 200, 50);
        getReportPanel.add(saveBtn);

        saveBtn.addActionListener(e -> {
            setVisible(false);
            clear();
            ProjectManagerPage.setVisible(true);
        });

    }
    public void setVisible(boolean visi){
        getReportPanel.setVisible(visi);
    }

    public void setProject(String project ){
        projectToManage = SoftwareHuset.projects.get(Integer.parseInt(project));

        //Update combobox with developers
        for (String developer : SoftwareHuset.developers.keySet()) {
            developerList.addItem(developer);
        }

        //Update combobox with activities
        for (String activity : SoftwareHuset.allActivities.keySet()) {
            activityList.addItem(activity);
        }


        projectIDLabel = new JLabel();
        projectIDLabel.setText("Project ID: " + projectToManage.getId());
        projectIDLabel.setBounds(15,35,150,30);

        timeLabel = new JLabel();
        timeLabel.setText("Estimated time: " + projectToManage.getEstimatedTime() +
                " hours - Time used " + projectToManage.getUsedTime() + " hours - " +
                (projectToManage.getUsedTime()/ (double) projectToManage.getEstimatedTime())*100 + "% used");
        timeLabel.setBounds(15,60,400,30);

        //Rounding decimals in double
        DecimalFormat rounding = new DecimalFormat("###.##");

        budgetLabel = new JLabel();
        budgetLabel.setText("Estimated budget: " + projectToManage.getBudget() +
                "kr - Budget used "+ projectToManage.getBudgetUsed() + "kr - " +
                rounding.format((projectToManage.getBudgetUsed()/ (double) projectToManage.getBudget())*100) + "% used");
        budgetLabel.setBounds(15,80,400,30);
        getReportPanel.add(projectIDLabel);
        getReportPanel.add(timeLabel);
        getReportPanel.add(budgetLabel);


    }

    private void addLabelsToScreen(){
        devListLabel = new JLabel();
        devListLabel.setText("Developers");
        devListLabel.setBounds(45,130,70,30);

        actListLabel = new JLabel();
        actListLabel.setText("Activities");
        actListLabel.setBounds(195,130,80,30);

        doneActListLabel = new JLabel();
        doneActListLabel.setText("Done activities");
        doneActListLabel.setBounds(330,130,95,30);


        getReportPanel.add(devListLabel);
        getReportPanel.add(actListLabel);
        getReportPanel.add(doneActListLabel);

    }

    private void addTextFieldsToScreen(){

        //ComboBox for list of developers
        developerList = new JComboBox<Object>();
        developerList.setBounds(35, 170, 100, 29);

        developerList.setBounds(35, 170, 100, 29);
        getReportPanel.add(developerList);

        //ComboBox for list of activities
        activityList = new JComboBox<Object>();
        activityList.setBounds(165, 170, 100, 29);

        activityList.setBounds(165, 170, 125, 29);
        getReportPanel.add(activityList);

    }

    private void createPage() {
        getReportPanel = new JPanel();
        parentWindow.addPanel(getReportPanel);
        getReportPanel.setLayout(null);
        getReportPanel.setBorder(BorderFactory.createTitledBorder("Get project report page"));
    }

    public void clear() {


    }
}