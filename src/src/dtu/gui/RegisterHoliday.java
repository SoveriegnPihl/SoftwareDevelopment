package dtu.gui;

import dtu.project.SoftwareHuset;

import javax.swing.*;
import java.time.Month;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

public class RegisterHoliday {
    JButton saveBtn;
    JPanel createProjectPanel;
    JLabel startDateLabel, endDateLabel;
    JTextField startDateTxtField, endDateTxtField;
    SoftwareHuset softwareHuset;
    Main parentWindow;
    JComboBox<Month> monthSelStart, monthSelFin;
    JComboBox<Integer> yearSelStart, yearSelFin;
    int year;

    public RegisterHoliday(SoftwareHuset softwareHuset, Main parentWindow) {
        this.softwareHuset = softwareHuset;
        this.parentWindow = parentWindow;
        initialize();
    }

    public void initialize() {
        createProjectPanel = new JPanel();
        parentWindow.addPanel(createProjectPanel);
        createProjectPanel.setLayout(null);

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e -> {
            setVisible(false);
            clear();
            DeveloperPage.setVisible(true);
        });
        btnBack.setBounds(21, 300, 59, 29);
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

            DeveloperPage.loggedInUser.setHoliday(startDate, endDate);

            setVisible(false);
            clear();
            DeveloperPage.setVisible(true);
        });
    }
    private void clear() {
        startDateTxtField.setText("");
        endDateTxtField.setText("");
        monthSelStart.setSelectedIndex(0);
        monthSelFin.setSelectedIndex(0);
        yearSelStart.setSelectedItem(year);
        yearSelFin.setSelectedItem(year);
    }

    public void setVisible(boolean visi){
        createProjectPanel.setVisible(visi);
    }

    private void addLabelsToScreen(){
        startDateLabel = new JLabel();
        startDateLabel.setText("Activity start date");
        startDateLabel.setBounds(25, 100, 193, 29);

        endDateLabel = new JLabel();
        endDateLabel.setText("Activity end date");
        endDateLabel.setBounds(25, 150, 193, 29);


        createProjectPanel.add(startDateLabel);
        createProjectPanel.add(endDateLabel);
    }

    private void addTextFieldsToScreen(){
        Vector v = getYears();

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

        createProjectPanel.add(startDateTxtField);
        createProjectPanel.add(endDateTxtField);
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
