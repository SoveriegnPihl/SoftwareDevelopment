package dtu.gui;
import dtu.project.Project;
import dtu.softwarehus.SoftwareHuset;
import javax.swing.*;


//create CreateLoginForm class to create login form
//class extends JFrame to create a window where our component add
//class implements ActionListener to perform an action on button click
public class manageProjectPage {
    //initialize button, panel, label, and text field
    Project thisProject;
    JButton saveBtn;
    JPanel createProjectPanel;
    JLabel startDateLabel, endDateLabel, budgetLabel, projectManagerLabel;
    JTextField startDateTxtField, endDateTxtField, budgetTxtField, projectManagerTxtField;
    SoftwareHuset softwareHuset;
    Main parentWindow;


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
            thisProject.startWeek = Integer.parseInt(startDateTxtField.getText());
            thisProject.endWeek = Integer.parseInt(endDateTxtField.getText());
            thisProject.budget = Integer.parseInt(budgetTxtField.getText());

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

    }
    public void setLabels(String project ){

        thisProject = SoftwareHuset.projects.get(Integer.parseInt(project));

        startDateTxtField.setText(String.valueOf(thisProject.startWeek));
        endDateTxtField.setText(String.valueOf(thisProject.endWeek));
        budgetTxtField.setText(String.valueOf(thisProject.budget));
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
        startDateTxtField = new JTextField(15); //set length of the text
        startDateTxtField.setBounds(250, 50, 193, 29);

        endDateTxtField = new JTextField(15);
        endDateTxtField.setBounds(250, 100, 193, 29);

        budgetTxtField = new JTextField(15);
        budgetTxtField.setBounds(250, 150, 193, 29);

        projectManagerTxtField = new JTextField(15);
        projectManagerTxtField.setBounds(250, 200, 193, 29);

        createProjectPanel.add(startDateTxtField);
        createProjectPanel.add(endDateTxtField);
        createProjectPanel.add(projectManagerTxtField);
        createProjectPanel.add(budgetTxtField);
    }
}