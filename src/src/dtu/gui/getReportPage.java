package dtu.gui;
import dtu.project.Project;
import dtu.softwarehus.SoftwareHuset;

import javax.swing.*;


public class getReportPage {
    JFrame frame;
    Project projectToManage;
    JButton saveBtn;
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
        btnBack.setBounds(21, 300, 59, 29);
        getReportPanel.add(btnBack);

        //adding labels to screen
        addLabelsToScreen();

        //adding textfields to screen
        addTextFieldsToScreen();

        //adding save button
        saveBtn = new JButton("Save"); //set label to button
        saveBtn.setBounds(150, 300, 193, 29);
        getReportPanel.add(saveBtn);


        saveBtn.addActionListener(e -> {

            setVisible(false);
            ProjectManagerPage.setVisible(true);
        });

    }
    public void setVisible(boolean visi){
        getReportPanel.setVisible(visi);
    }

    public void setProject(String project ){
        projectToManage = SoftwareHuset.projects.get(Integer.parseInt(project));

        projectIDLabel = new JLabel();
        projectIDLabel.setText("Project ID: " + projectToManage.getId());
        projectIDLabel.setBounds(15,15,150,30);

        timeLabel = new JLabel();
        timeLabel.setText("Time estimated for project: " + projectToManage.getEstimatedTime() +
                " hours. Time used " + projectToManage.getUsedTime() + " hours, " +
                (projectToManage.getUsedTime()/ (double) projectToManage.getEstimatedTime())*100 + "% used");
        timeLabel.setBounds(15,40,400,30);

        budgetLabel = new JLabel();
        budgetLabel.setText("Budget estimated for project: " + projectToManage.getBudget() +
                "kr. Budget used "+ projectToManage.getBudgetUsed() + "kr, " +
                (projectToManage.getBudgetUsed()/ (double) projectToManage.getBudget())*100 + "% used");
        budgetLabel.setBounds(15,60,400,30);
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
        actListLabel.setBounds(175,130,80,30);

        doneActListLabel = new JLabel();
        doneActListLabel.setText("Done activities");
        doneActListLabel.setBounds(310,130,95,30);


        getReportPanel.add(devListLabel);
        getReportPanel.add(actListLabel);
        getReportPanel.add(doneActListLabel);

    }

    private void addTextFieldsToScreen(){

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