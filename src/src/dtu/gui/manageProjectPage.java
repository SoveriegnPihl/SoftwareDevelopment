package dtu.gui;

import dtu.project.Project;
import dtu.project.SoftwareHuset;
import dtu.softwarehus.Utility;

import javax.swing.*;
import java.time.Month;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

// lavet af Victor Larsen-Saldeen testet af Marcus

public class manageProjectPage {
    JFrame frame;
    Project projectToManage;
    JButton saveBtn;
    JPanel createProjectPanel;
    JLabel startDateLabel, endDateLabel, budgetLabel, projectManagerLabel;
    JTextField startDateTxtField, endDateTxtField, budgetTxtField, projectManagerTxtField;
    SoftwareHuset softwareHuset;
    Main parentWindow;
    JComboBox<Month> monthSelStart, monthSelFin;
    JComboBox<Integer> yearSelStart, yearSelFin;
    private int year;


    //calling constructor
    public manageProjectPage(SoftwareHuset softwareHuset, Main parentWindow) {
        this.softwareHuset = softwareHuset;
        this.parentWindow = parentWindow;
        initialize();
    }

    public void initialize() {
        createPage();

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e -> {
            setVisible(false);
            clear();
            ProjectManagerPage.setVisible(true);
        });
        btnBack.setBounds(21, 300, 70, 29);
        createProjectPanel.add(btnBack);

        //adding labels to screen
        addLabelsToScreen();

        //adding textfields to screen
        addTextFieldsToScreen();

        //adding save button
        saveBtn = new JButton("Save"); //set label to button
        saveBtn.setBounds(150, 300, 193, 29);
        createProjectPanel.add(saveBtn);

        saveBtn.addActionListener(e -> {

            if (!(Utility.isInt(startDateTxtField.getText()) && Utility.isInt(endDateTxtField.getText()) && Utility.isInt(budgetTxtField.getText()))) {
                if (!Utility.isInt(startDateTxtField.getText())) {
                    JOptionPane.showMessageDialog(frame, "Start date isn't an int!");
                }
                if (!Utility.isInt(endDateTxtField.getText())) {
                    JOptionPane.showMessageDialog(frame, "End date isn't an int!");
                }
                if (!Utility.isInt(budgetTxtField.getText())) {
                    JOptionPane.showMessageDialog(frame, "Budget isn't an int!");
                }
            } else {
                if ((Integer.parseInt(startDateTxtField.getText()) > 0 && Integer.parseInt(endDateTxtField.getText()) > 0) && Integer.parseInt(startDateTxtField.getText()) < 31 && Integer.parseInt(endDateTxtField.getText()) < 31) {

                    GregorianCalendar startDate = new GregorianCalendar(yearSelStart.getItemAt(yearSelStart.getSelectedIndex()), monthSelStart.getSelectedIndex(), Integer.parseInt(startDateTxtField.getText()));

                    GregorianCalendar endDate = new GregorianCalendar(yearSelFin.getItemAt(yearSelFin.getSelectedIndex()), monthSelFin.getSelectedIndex(), Integer.parseInt(endDateTxtField.getText()));

                    if (startDate.compareTo(endDate) == -1 && endDate.compareTo(startDate) == 1) {
                        DeveloperPage.loggedInUser.setHoliday(startDate, endDate);

                        int[] newStartDate = {Integer.parseInt(startDateTxtField.getText()), monthSelStart.getSelectedIndex(), yearSelStart.getItemAt(yearSelStart.getSelectedIndex())};
                        int[] newEndDate = {Integer.parseInt(endDateTxtField.getText()), monthSelFin.getSelectedIndex(), yearSelFin.getItemAt(yearSelStart.getSelectedIndex())};
                        int newBudget = Integer.parseInt(budgetTxtField.getText());

                        projectToManage.setNewDateAndBudget(newStartDate, newEndDate, newBudget);
                        SoftwareHuset.updateCSVFile("projects");

                        setVisible(false);
                        ProjectManagerPage.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Date intervals does not match");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Set a proper day");
                }
            }
        });

    }

    public void setVisible(boolean visi) {
        createProjectPanel.setVisible(visi);
    }

    public void setLabels(String project) {
        projectToManage = SoftwareHuset.projects.get(Integer.parseInt(project));

        startDateTxtField.setText(String.valueOf(projectToManage.getDateDay("start")));
        monthSelStart.setSelectedIndex(Integer.parseInt(projectToManage.getDateMonth("start")));
        yearSelStart.setSelectedItem(projectToManage.getDateYear("start"));

        endDateTxtField.setText(String.valueOf(projectToManage.getDateDay("end")));
        monthSelFin.setSelectedIndex(Integer.parseInt(projectToManage.getDateMonth("end")));
        yearSelFin.setSelectedItem(projectToManage.getDateYear("end"));
        budgetTxtField.setText(String.valueOf(projectToManage.budget));
    }

    private void addLabelsToScreen() {
        startDateLabel = new JLabel();
        startDateLabel.setText("Change Start date");      //set label value for textField1
        startDateLabel.setBounds(25, 50, 193, 29);

        endDateLabel = new JLabel();
        endDateLabel.setText("Change End date");
        endDateLabel.setBounds(25, 100, 193, 29);

        budgetLabel = new JLabel();
        budgetLabel.setText("Change Budget");
        budgetLabel.setBounds(25, 150, 193, 29);

        projectManagerLabel = new JLabel();
        projectManagerLabel.setText("Assign project manager");
        projectManagerLabel.setBounds(25, 200, 193, 29);

        createProjectPanel.add(startDateLabel);
        createProjectPanel.add(endDateLabel);
        createProjectPanel.add(budgetLabel);
        createProjectPanel.add(projectManagerLabel);
    }

    private void addTextFieldsToScreen() {
        Vector v = getYears();

        startDateTxtField = new JTextField(15);
        startDateTxtField.setBounds(225, 50, 45, 29);

        monthSelStart = new JComboBox<>(Month.values());
        monthSelStart.setBounds(280, 50, 95, 29);

        yearSelStart = new JComboBox<Integer>(v);
        yearSelStart.setSelectedItem(year);
        yearSelStart.setBounds(385, 50, 60, 29);

        endDateTxtField = new JTextField(15);
        endDateTxtField.setBounds(225, 100, 45, 29);

        monthSelFin = new JComboBox<>(Month.values());
        monthSelFin.setBounds(280, 100, 95, 29);

        yearSelFin = new JComboBox<Integer>(v);
        yearSelFin.setSelectedItem(year);
        yearSelFin.setBounds(385, 100, 60, 29);

        budgetTxtField = new JTextField(15);
        budgetTxtField.setBounds(250, 150, 193, 29);

        projectManagerTxtField = new JTextField(15);
        projectManagerTxtField.setBounds(250, 200, 193, 29);

        createProjectPanel.add(startDateTxtField);
        createProjectPanel.add(endDateTxtField);
        createProjectPanel.add(budgetTxtField);
        createProjectPanel.add(monthSelStart);
        createProjectPanel.add(yearSelStart);
        createProjectPanel.add(yearSelFin);
        createProjectPanel.add(monthSelFin);
    }

    private void createPage() {
        createProjectPanel = new JPanel();
        parentWindow.addPanel(createProjectPanel);
        createProjectPanel.setLayout(null);
        createProjectPanel.setBorder(BorderFactory.createTitledBorder("Manage project page"));
    }

    public void setDevs() {
        JComboBox<Object> developerCombo = new JComboBox<>();
        for (String developer : SoftwareHuset.developers.keySet()) {
            developerCombo.addItem(developer);
        }

        developerCombo.setBounds(250, 200, 193, 29);
        createProjectPanel.add(developerCombo);
    }

    public void clear() {
        startDateTxtField.setText("");
        endDateTxtField.setText("");
        projectManagerTxtField.setText("");
        budgetTxtField.setText("");
        monthSelStart.setSelectedItem("January");
        monthSelFin.setSelectedItem("January");
        yearSelStart.setSelectedItem(year);
        yearSelFin.setSelectedItem(year);
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