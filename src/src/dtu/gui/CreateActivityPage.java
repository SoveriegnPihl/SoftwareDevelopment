package dtu.gui;
import dtu.project.Activity;
import dtu.project.Project;
import dtu.softwarehus.SoftwareHuset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Month;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;


public class CreateActivityPage {
    JButton saveBtn;
    JPanel createProjectPanel;
    JLabel startDateLabel, endDateLabel, budgetLabel, projectLabel, activityNameLabel, estTimeLabel;
    JTextField startDateTxtField, endDateTxtField, estTimeTxtField, projectTxtField, nameTxtField, budgetTxtField;
    SoftwareHuset softwareHuset;
    Main parentWindow;
    JComboBox<Month> monthSelStart, monthSelFin;
    JComboBox<Integer> yearSelStart, yearSelFin;
    int year;
    private JFrame frame;

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
                DeveloperPage.setVisible(true);
            }
        });
        btnBack.setBounds(21, 350, 59, 29);
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
            //date intervals
            GregorianCalendar startDate = new GregorianCalendar(yearSelStart.getItemAt(yearSelStart.getSelectedIndex()),
                    monthSelStart.getSelectedIndex(),Integer.parseInt(startDateTxtField.getText()));

            GregorianCalendar endDate = new GregorianCalendar(yearSelFin.getItemAt(yearSelFin.getSelectedIndex()),
                    monthSelFin.getSelectedIndex(),Integer.parseInt(endDateTxtField.getText()));

            //making activity
            Activity newActivity = new Activity(nameTxtField.getText(), Integer.parseInt(estTimeTxtField.getText()));
            newActivity.setDateInterval(startDate, endDate);
            newActivity.setBudget(Integer.parseInt(budgetTxtField.getText()));

            //adding to project
            Project projectToAddTo = SoftwareHuset.projects.get(Integer.parseInt(projectTxtField.getText()));
            projectToAddTo.addActivity(newActivity);

        setVisible(false);
        clear();
        DeveloperPage.setVisible(true);

        });

    }
    private void createPage() {
        frame = new JFrame();
        frame.setBounds(100, 100, 500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new CardLayout(0, 0));
        createProjectPanel = new JPanel();
        frame.getContentPane().add(createProjectPanel);
        parentWindow.addPanel(createProjectPanel);
        createProjectPanel .setLayout(null);
        createProjectPanel .setBorder(BorderFactory.createTitledBorder("Create activity page"));
    }
    
    public void setVisible(boolean visi){
        createProjectPanel.setVisible(visi);
    }

    private void clear() {
        nameTxtField.setText("");
        startDateTxtField.setText("");
        endDateTxtField.setText("");
        projectTxtField.setText("");
        estTimeTxtField.setText("");
        budgetTxtField.setText("");
        monthSelStart.setSelectedItem("January");
        monthSelFin.setSelectedItem("January");
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

        projectTxtField = new JTextField(15);
        projectTxtField.setBounds(250, 300, 193, 29);

        createProjectPanel.add(startDateTxtField);
        createProjectPanel.add(endDateTxtField);
        createProjectPanel.add(projectTxtField);
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

}