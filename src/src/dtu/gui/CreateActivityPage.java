package dtu.gui;
import dtu.project.Developer;
import dtu.project.Project;
import dtu.project.SoftwareHuset;
import dtu.softwarehus.Utility;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Month;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

// lavet af Victor Larsen-Saldeen

public class CreateActivityPage {
    JButton saveBtn;
    JPanel createProjectPanel;
    JLabel startDateLabel, endDateLabel, budgetLabel, projectLabel, activityNameLabel, estTimeLabel, projectDateLabel;
    JTextField startDateTxtField, endDateTxtField, estTimeTxtField, projectTxtField, nameTxtField, budgetTxtField;
    SoftwareHuset softwareHuset;
    Main parentWindow;
    JComboBox<Month> monthSelStart, monthSelFin;
    JComboBox<Integer> yearSelStart, yearSelFin;
    JComboBox<Integer> allProjects;
    String originWindow;
    int year;
    private JFrame frame;
    JComboBox<Object> projectCombo;

    public CreateActivityPage(SoftwareHuset softwareHuset, Main parentWindow) {
        this.softwareHuset = softwareHuset;
        this.parentWindow = parentWindow;
        initialize();
    }
    public void initialize(){
        createPage();



        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                clear();
                if (originWindow.equals("ProjectManagerPage")){
                    ProjectManagerPage.setVisible(true);
                }else{
                    DeveloperPage.setVisible(true);
                }




            }
        });
        btnBack.setBounds(21, 350, 70, 29);
        createProjectPanel.add(btnBack);

        //adding labels to screen
        addLabelsToScreen();

        //adding textfields to screen
        addTextFieldsToScreen();

        //create save button
        saveBtn = new JButton("Save"); //set label to button
        saveBtn.setBounds(150, 350, 193, 29);
        createProjectPanel.add(saveBtn);

        saveBtn.addActionListener(e -> {
            String projectID = projectCombo.getSelectedItem().toString();
            String actName = nameTxtField.getText();
            String budget = budgetTxtField.getText();
            String estimatedTime = estTimeTxtField.getText();
            String timeUsed = "0.0";
            String startYear = String.valueOf(yearSelStart.getItemAt(yearSelStart.getSelectedIndex()));
            String startMonth = String.valueOf(monthSelStart.getSelectedIndex());
            String startDay = startDateTxtField.getText();

            String endYear = String.valueOf(yearSelFin.getItemAt(yearSelFin.getSelectedIndex()));
            String endMonth = String.valueOf(monthSelFin.getSelectedIndex());
            String endDay = endDateTxtField.getText();

            if (!(Utility.isInt(startDay) && Utility.isInt(endDay) && Utility.isInt(budget) && Utility.isInt(estimatedTime))) {
                if (!Utility.isInt(startDay)) {
                    JOptionPane.showMessageDialog(frame, "Start date isn't an int!");
                }
                if (!Utility.isInt(endDay)) {
                    JOptionPane.showMessageDialog(frame, "End date isn't an int!");
                }
                if (!Utility.isInt(estimatedTime)) {
                    JOptionPane.showMessageDialog(frame, "Estimated time date isn't an int!");
                }

                if (!Utility.isInt(budget)) {
                    JOptionPane.showMessageDialog(frame, "Budget date isn't an int!");
                }



            }
            else{

                if ((Integer.parseInt(startDateTxtField.getText()) > 0 && Integer.parseInt(startDateTxtField.getText()) > 0) &&
                        Integer.parseInt(startDateTxtField.getText()) < 31 && Integer.parseInt(startDateTxtField.getText()) < 31) {

                    GregorianCalendar startDate = new GregorianCalendar(Integer.parseInt(startYear), Integer.parseInt(startMonth), Integer.parseInt(startDay));
                    GregorianCalendar endDate = new GregorianCalendar(Integer.parseInt(endYear), Integer.parseInt(endMonth), Integer.parseInt(endDay));

                    if (startDate.compareTo(endDate) == -1 && endDate.compareTo(startDate) == 1) {
                        DeveloperPage.loggedInUser.setHoliday(startDate, endDate);
                        String[] activityValues = new String[]{projectID, actName, startYear, startMonth, startDay,
                                endYear, endMonth, endDay, estimatedTime, timeUsed, budget};


                        Project projectToAddTo = SoftwareHuset.projects.get(Integer.parseInt(projectID));

                        SoftwareHuset.addProjectActivities(projectToAddTo, activityValues);

                        setVisible(false);
                        clear();
                        DeveloperPage.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Date interval does not match");
                    }

                } else {
                    JOptionPane.showMessageDialog(frame, "Set a proper day");
                }
            }

        });

    }
    private void createPage() {
        createProjectPanel = new JPanel();
        parentWindow.addPanel(createProjectPanel);
        createProjectPanel.setLayout(null);
        createProjectPanel.setBorder(BorderFactory.createTitledBorder("Create activity page"));
    }
    
    public void setVisible(boolean visi){
        createProjectPanel.setVisible(visi);
    }

    public void setOriginWindow(String window){
        originWindow = window;
    }

    private void clear() {
        nameTxtField.setText("");
        startDateTxtField.setText("");
        endDateTxtField.setText("");
        estTimeTxtField.setText("");
        budgetTxtField.setText("");
        monthSelStart.setSelectedIndex(0);
        monthSelFin.setSelectedIndex(0);
        yearSelStart.setSelectedItem(year);
        yearSelFin.setSelectedItem(year);
    }

    private void addLabelsToScreen(){
        activityNameLabel = new JLabel();
        activityNameLabel.setText("Activity name");
        activityNameLabel.setBounds(25, 50, 193, 29);

        startDateLabel = new JLabel();
        startDateLabel.setText("Activity start date");
        startDateLabel.setBounds(25, 100, 193, 29);

        endDateLabel = new JLabel();
        endDateLabel.setText("Activity end date");
        endDateLabel.setBounds(25, 150, 193, 29);

        estTimeLabel = new JLabel();
        estTimeLabel.setText("Estimated time");
        estTimeLabel.setBounds(25, 200, 193, 29);

        budgetLabel = new JLabel();
        budgetLabel.setText("Budget");
        budgetLabel.setBounds(25, 250, 193, 29);

        projectLabel = new JLabel();
        projectLabel.setText("Which project to assign activity to");
        projectLabel.setBounds(25, 300, 193, 29);


        projectDateLabel = new JLabel();


        //String projectID = projectCombo.getSelectedItem().toString();

        //projectDateLabel.setText(SoftwareHuset.projects.get(Integer.parseInt(projectID)).startDate.toString() + " " + SoftwareHuset.projects.get(Integer.parseInt(projectID)).endDate.toString());

        //projectDateLabel.setBounds(25,350,193,29);


        createProjectPanel.add(startDateLabel);
        createProjectPanel.add(endDateLabel);
        createProjectPanel.add(budgetLabel);
        createProjectPanel.add(projectLabel);
        createProjectPanel.add(activityNameLabel);
        createProjectPanel.add(estTimeLabel);
    }

    private void addTextFieldsToScreen(){
        Vector v = getYears();

        nameTxtField = new JTextField(15);
        nameTxtField.setBounds(250, 50, 193, 29);

        startDateTxtField = new JTextField(15);
        startDateTxtField.setBounds(225, 100, 45, 29);

        monthSelStart = new JComboBox<>(Month.values());
        monthSelStart.setBounds(280,100,95,29);

        yearSelStart = new JComboBox<Integer>(v);
        yearSelStart.setSelectedItem(year);
        yearSelStart.setBounds(385,100,60,29);

        endDateTxtField = new JTextField(15);
        endDateTxtField.setBounds(225, 150, 45, 29);

        monthSelFin = new JComboBox<>(Month.values());
        monthSelFin.setBounds(280,150,95,29);

        yearSelFin = new JComboBox<Integer>(v);
        yearSelFin.setSelectedItem(year);
        yearSelFin.setBounds(385,150,60,29);

        estTimeTxtField = new JTextField(15);
        estTimeTxtField.setBounds(250, 200, 193, 29);

        budgetTxtField = new JTextField(15);
        budgetTxtField.setBounds(250, 250, 193, 29);



        createProjectPanel.add(startDateTxtField);
        createProjectPanel.add(endDateTxtField);
        createProjectPanel.add(estTimeTxtField);
        createProjectPanel.add(nameTxtField);
        createProjectPanel.add(budgetTxtField);
        createProjectPanel.add(monthSelStart);
        createProjectPanel.add(yearSelStart);
        createProjectPanel.add(yearSelFin);
        createProjectPanel.add(monthSelFin);
    }

    private Vector getYears() {
        Calendar now = Calendar.getInstance();
        year = now.get(Calendar.YEAR);
        Vector v = new Vector();
        for (int i = year; i <= 2030; i++) {
            v.add(i);
        }
        return v;
    }

    public void setProjs(){
        projectCombo = new JComboBox<>();
        for (int projID : SoftwareHuset.projects.keySet()) {
            projectCombo.addItem(projID);
        }

        projectCombo.setBounds(250, 300, 193, 29);
        createProjectPanel.add(projectCombo);
    }


}