package dtu.gui;

import dtu.softwarehus.SoftwareHuset;

import javax.swing.*;
import java.time.Month;
import java.time.Year;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;


//create CreateLoginForm class to create login form
//class extends JFrame to create a window where our component add
//class implements ActionListener to perform an action on button click
public class CreateProjectPage {
    //initialize button, panel, label, and text field
    JButton createProjectBtn;
    JPanel createProjectPanel;
    JLabel startDateLabel, endDateLabel, budgetLabel, asignPMLabel;
    JTextField startDateTxtField, endDateTxtField, projectManagerTxtField, budgetTxtField;
    SoftwareHuset softwareHuset;
    Main parentWindow;
    private int year;
    JComboBox<Month> monthSelStart, monthSelFin;
    JComboBox<Integer> yearSelStart, yearSelFin;

    //calling constructor
    public CreateProjectPage( SoftwareHuset softwareHuset, Main parentWindow) {
        this.softwareHuset = softwareHuset;
        this.parentWindow = parentWindow;
        initialize();
    }
    public void initialize(){
        createProjectPanel = new JPanel();
        parentWindow.addPanel(createProjectPanel);
        createProjectPanel.setLayout(null);

        //adding labels to screen
        addLabelsToScreen();

        //adding textfields to screen
        addTextFieldsToScreen();

        createProjectBtn = new JButton("Create project"); //set label to button
        createProjectBtn.setBounds(150, 300, 193, 29);
        createProjectPanel.add(createProjectBtn);



        createProjectBtn.addActionListener(e -> {
            String startWeekTxt = startDateTxtField.getText();
            String endWeekTxt = endDateTxtField.getText();
            String budgetTxt = budgetTxtField.getText();
            String projectManagerTxt = projectManagerTxtField.getText();

            //getting date intervals
            GregorianCalendar startDate = new GregorianCalendar(yearSelStart.getItemAt(yearSelStart.getSelectedIndex()),
                    monthSelStart.getSelectedIndex(),Integer.parseInt(startDateTxtField.getText()));

            GregorianCalendar endDate = new GregorianCalendar(yearSelFin.getItemAt(yearSelFin.getSelectedIndex()),
                    monthSelFin.getSelectedIndex(),Integer.parseInt(endDateTxtField.getText()));

            int project = SoftwareHuset.createProject(startDate, endDate,Integer.parseInt(budgetTxt));

            if(!projectManagerTxt.isEmpty()) {
                SoftwareHuset.assignPM(projectManagerTxt, project);
        }
        setVisible(false);
        clear();
        parentWindow.setVisible(true);

        });

    }

    private void addTextFieldsToScreen() {
        Vector v = getYears();

        startDateTxtField = new JTextField(15);
        startDateTxtField.setBounds(225, 50, 45, 29);

        monthSelStart = new JComboBox<>(Month.values());
        monthSelStart.setBounds(280,50,95,29);

        yearSelStart = new JComboBox<Integer>(v);
        yearSelStart.setSelectedItem(year);
        yearSelStart.setBounds(385,50,60,29);

        endDateTxtField = new JTextField(15);
        endDateTxtField.setBounds(225, 100, 45, 29);

        monthSelFin = new JComboBox<>(Month.values());
        monthSelFin.setBounds(280,100,95,29);

        yearSelFin = new JComboBox<Integer>(v);
        yearSelFin.setSelectedItem(year);
        yearSelFin.setBounds(385,100,60,29);

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

    public void setVisible(boolean visi){
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