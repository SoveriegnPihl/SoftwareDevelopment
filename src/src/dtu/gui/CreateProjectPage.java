package dtu.gui;

import dtu.project.SoftwareHuset;
import dtu.softwarehus.Utility;

import javax.swing.*;
import java.time.Month;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;


// lavet af Victor Larsen-Saldeen testet af Marcus

public class CreateProjectPage {
    JFrame frame;
    JButton createProjectBtn;
    JPanel createProjectPanel;
    JLabel startDateLabel, endDateLabel, budgetLabel, asignPMLabel;
    JTextField startDateTxtField, endDateTxtField, projectManagerTxtField, budgetTxtField;
    SoftwareHuset softwareHuset;
    Main parentWindow;
    JComboBox<Month> monthSelStart, monthSelFin;
    JComboBox<Integer> yearSelStart, yearSelFin;
    private int year;

    //calling constructor
    public CreateProjectPage(SoftwareHuset softwareHuset, Main parentWindow) {
        this.softwareHuset = softwareHuset;
        this.parentWindow = parentWindow;
        initialize();
    }

    public void initialize() {

        createPage();

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e -> {
            clear();
            Main.setFrameSize(600, 400);
            Main.setLocation();
            setVisible(false);
            parentWindow.setVisible(true);
        });
        btnBack.setBounds(21, 300, 59, 29);
        createProjectPanel.add(btnBack);

        //adding labels to screen
        addLabelsToScreen();

        //adding textfields to screen
        addTextFieldsToScreen();

        createProjectBtn = new JButton("Create project"); //set label to button
        createProjectBtn.setBounds(150, 300, 193, 29);
        createProjectPanel.add(createProjectBtn);

        createProjectBtn.addActionListener(e -> {

            String budgetTxt = budgetTxtField.getText();
            String projectManagerTxt = projectManagerTxtField.getText();
            GregorianCalendar startDate = null;
            GregorianCalendar endDate = null;
            //getting date intervals

            if (!(Utility.isInt(startDateTxtField.getText()) && Utility.isInt(endDateTxtField.getText()))) {
                if (!Utility.isInt(startDateTxtField.getText())) {
                    JOptionPane.showMessageDialog(frame, "Start date isn't an int!");
                }
                if (!Utility.isInt(endDateTxtField.getText())) {
                    JOptionPane.showMessageDialog(frame, "End date isn't an int!");
                }
            } else {
                try {


                    startDate = new GregorianCalendar(yearSelStart.getItemAt(yearSelStart.getSelectedIndex()), monthSelStart.getSelectedIndex(), Integer.parseInt(startDateTxtField.getText()));

                    endDate = new GregorianCalendar(yearSelFin.getItemAt(yearSelFin.getSelectedIndex()), monthSelFin.getSelectedIndex(), Integer.parseInt(endDateTxtField.getText()));
                } catch (NumberFormatException b) {
                    Main.createMessage("Error. " + b.getMessage());
                }

                int project = 0;
                if (startDate != null) {
                    if (startDate.compareTo(endDate) < 0 && projectManagerTxt.isEmpty()) {
                        try {
                            project = SoftwareHuset.createProject(startDate, endDate, Integer.parseInt(budgetTxt));
                            Main.setFrameSize(600, 400);
                            Main.setLocation();
                            clear();
                            setVisible(false);
                            parentWindow.setVisible(true);
                            Main.createMessage("Success");
                        } catch (NumberFormatException b) {
                            Main.createMessage("Error. " + b.getMessage());
                        }

                    } else if (!projectManagerTxt.isEmpty()) {
                        if (SoftwareHuset.isDeveloper(projectManagerTxt)) {
                            try {
                                project = SoftwareHuset.createProject(startDate, endDate, Integer.parseInt(budgetTxt));
                            } catch (NumberFormatException b) {
                                Main.createMessage("Error. " + b.getMessage());
                            }
                            SoftwareHuset.assignPM(projectManagerTxt, project);
                            Main.setFrameSize(500, 250);
                            setVisible(false);
                            clear();
                            parentWindow.setVisible(true);
                            Main.createMessage("Success");
                        } else {
                            Main.createMessage("No developer found");
                        }


                    } else {
                        Main.createMessage("Select new date");
                    }
                }
            }
        });
    }

    private void createPage() {
        createProjectPanel = new JPanel();
        parentWindow.addPanel(createProjectPanel);
        createProjectPanel.setLayout(null);
        createProjectPanel.setBorder(BorderFactory.createTitledBorder("Create project page"));

    }

    private void addTextFieldsToScreen() {
        Vector v = getYears();

        startDateTxtField = new JTextField(15);
        startDateTxtField.setBounds(225, 50, 45, 29);

        monthSelStart = new JComboBox<>(Month.values());
        monthSelStart.setBounds(280, 50, 110, 29);

        yearSelStart = new JComboBox<Integer>(v);
        yearSelStart.setSelectedItem(year);
        yearSelStart.setBounds(395, 50, 100, 29);

        endDateTxtField = new JTextField(15);
        endDateTxtField.setBounds(225, 100, 45, 29);

        monthSelFin = new JComboBox<>(Month.values());
        monthSelFin.setBounds(280, 100, 110, 29);

        yearSelFin = new JComboBox<Integer>(v);
        yearSelFin.setSelectedItem(year);
        yearSelFin.setBounds(395, 100, 100, 29);

        budgetTxtField = new JTextField(15);
        budgetTxtField.setBounds(250, 150, 193, 29);

        projectManagerTxtField = new JTextField(15);
        projectManagerTxtField.setBounds(250, 200, 193, 29);

        createProjectPanel.add(startDateTxtField);
        createProjectPanel.add(endDateTxtField);
        createProjectPanel.add(projectManagerTxtField);
        createProjectPanel.add(budgetTxtField);
        createProjectPanel.add(monthSelStart);
        createProjectPanel.add(yearSelStart);
        createProjectPanel.add(yearSelFin);
        createProjectPanel.add(monthSelFin);
    }

    private void addLabelsToScreen() {
        startDateLabel = new JLabel();
        startDateLabel.setText("Start date");
        startDateLabel.setBounds(25, 50, 193, 29);

        endDateLabel = new JLabel();
        endDateLabel.setText("End date");
        endDateLabel.setBounds(25, 100, 193, 29);

        budgetLabel = new JLabel();
        budgetLabel.setText("Budget");
        budgetLabel.setBounds(25, 150, 193, 29);

        asignPMLabel = new JLabel();
        asignPMLabel.setText("Assign project manager");
        asignPMLabel.setBounds(25, 200, 193, 29);

        createProjectPanel.add(startDateLabel);
        createProjectPanel.add(endDateLabel);
        createProjectPanel.add(budgetLabel);
        createProjectPanel.add(asignPMLabel);
    }

    public void setVisible(boolean visi) {
        createProjectPanel.setVisible(visi);
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