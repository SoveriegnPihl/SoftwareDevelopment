package dtu.gui;
import dtu.project.Project;
import dtu.softwarehus.SoftwareHuset;

import javax.swing.*;
import java.time.Month;
import java.util.Calendar;
import java.util.Vector;

//create CreateLoginForm class to create login form
//class extends JFrame to create a window where our component add
//class implements ActionListener to perform an action on button click
public class manageProjectPage {
    //initialize button, panel, label, and text field
    Project projectToManage;
    JButton saveBtn;
    JPanel createProjectPanel;
    JLabel startDateLabel, endDateLabel, budgetLabel, projectManagerLabel;
    JTextField startDateTxtField, endDateTxtField, budgetTxtField, projectManagerTxtField;
    SoftwareHuset softwareHuset;
    Main parentWindow;
    private int year;
    JComboBox<Month> monthSelStart, monthSelFin;
    JComboBox<Integer> yearSelStart, yearSelFin;


    //calling constructor
    public manageProjectPage(SoftwareHuset softwareHuset, Main parentWindow) {
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

        //adding save button
        saveBtn = new JButton("Save"); //set label to button
        saveBtn.setBounds(150, 300, 193, 29);
        createProjectPanel.add(saveBtn);


        saveBtn.addActionListener(e -> {
           // projectToManage.startWeek = Integer.parseInt(startDateTxtField.getText());
            //projectToManage.endWeek = Integer.parseInt(endDateTxtField.getText());
           // projectToManage.budget = Integer.parseInt(budgetTxtField.getText());

            setVisible(false);
            clear();
            ProjectManagerPage.setVisible(true);

        });

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
    public void setLabels(String project ){

        projectToManage = SoftwareHuset.projects.get(Integer.parseInt(project));

        System.out.println(projectToManage.getDateDay("start"));

        startDateTxtField.setText(String.valueOf(projectToManage.getDateDay("start")));
        monthSelStart.setSelectedItem(projectToManage.getDateMonth("start"));
        yearSelStart.setSelectedItem(projectToManage.getDateYear("start"));

        endDateTxtField.setText(String.valueOf(projectToManage.getDateDay("end")));
        monthSelFin.setSelectedItem(projectToManage.getDateMonth("end"));
        yearSelFin.setSelectedItem(projectToManage.getDateYear("end"));
        budgetTxtField.setText(String.valueOf(projectToManage.budget));
    }

    private void addLabelsToScreen(){
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

    private void addTextFieldsToScreen(){
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