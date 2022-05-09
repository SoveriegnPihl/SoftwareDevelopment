package dtu.gui;

import dtu.project.SoftwareHuset;
import org.junit.Assert;

import javax.swing.*;
import java.awt.*;
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
    JFrame frame1;

    public RegisterHoliday(SoftwareHuset softwareHuset, Main parentWindow) {
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
            Main.setFrameSize(500,500);
            DeveloperPage.setVisible(true);
        });
        btnBack.setBounds(31, 225, 70, 29);
        createProjectPanel.add(btnBack);

        //adding labels to screen
        addLabelsToScreen();

        //adding textfields to screen
        addTextFieldsToScreen();

        //create save button
        saveBtn = new JButton("Save"); //set label to button
        saveBtn.setBounds(150, 225, 193, 29);
        createProjectPanel.add(saveBtn);

        saveBtn.addActionListener(e -> {
            if((Integer.parseInt(startDateTxtField.getText()) > 0 && Integer.parseInt(startDateTxtField.getText()) > 0) &&
            Integer.parseInt(startDateTxtField.getText()) < 31 && Integer.parseInt(startDateTxtField.getText()) < 31){

                GregorianCalendar startDate = new GregorianCalendar(yearSelStart.getItemAt(yearSelStart.getSelectedIndex()),
                        monthSelStart.getSelectedIndex(),Integer.parseInt(startDateTxtField.getText()));

                GregorianCalendar endDate = new GregorianCalendar(yearSelFin.getItemAt(yearSelFin.getSelectedIndex()),
                        monthSelFin.getSelectedIndex(),Integer.parseInt(endDateTxtField.getText()));

                if(startDate.compareTo(endDate)== -1){
                    DeveloperPage.loggedInUser.setHoliday(startDate, endDate);

                    setVisible(false);
                    clear();
                    DeveloperPage.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(frame1, "End date is before start date");
                }


            }
            else{
                JOptionPane.showMessageDialog(frame1, "Set a proper day");
            }

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
        startDateLabel.setBounds(25, 75, 193, 29);

        endDateLabel = new JLabel();
        endDateLabel.setText("Activity end date");
        endDateLabel.setBounds(25, 125, 193, 29);


        createProjectPanel.add(startDateLabel);
        createProjectPanel.add(endDateLabel);
    }

    private void addTextFieldsToScreen(){
        Vector v = getYears();

        startDateTxtField = new JTextField(15);
        startDateTxtField.setBounds(205, 75, 45, 29);
        //startDateTxtField.setFont(Font.getFont("Helvetica",Font.BOLD));

        monthSelStart = new JComboBox<>(Month.values());
        monthSelStart.setBounds(260,75,95,29);

        yearSelStart = new JComboBox<Integer>(v);
        yearSelStart.setSelectedItem(year);
        yearSelStart.setBounds(365,75,60,29);

        endDateTxtField = new JTextField(15);
        endDateTxtField.setBounds(205, 125, 45, 29);

        monthSelFin = new JComboBox<>(Month.values());
        monthSelFin.setBounds(260,125,95,29);

        yearSelFin = new JComboBox<Integer>(v);
        yearSelFin.setSelectedItem(year);
        yearSelFin.setBounds(365,125,60,29);

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

    public void createPage() {
        createProjectPanel = new JPanel();
        parentWindow.addPanel(createProjectPanel);
        createProjectPanel.setLayout(null);
        createProjectPanel.setBorder(BorderFactory.createTitledBorder("Register Holiday"));
    }

}
