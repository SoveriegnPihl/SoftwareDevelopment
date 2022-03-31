package dtu.softwarehus;//import required classes and packages
import javax.swing.*;
import java.awt.*;

//create NewPage class to create a new page on which user will navigate  
class DeveloperPage extends JFrame
{
    String initials;
    JButton b1,b2,b3,b4,b5;
    JPanel newPanel;
    JLabel userLabel;
    JTextField textField1;
    private JPanel panel1;
    private JCheckBox checkBox1;
    boolean managerCheckBox = false;
    //constructor  
    DeveloperPage(String userValue)
    {
        initials = userValue;
        //create a welcome label and set it to the new page
        //create submit button

        b1 = new JButton("Register hours worked"); //set label to button
        b2 = new JButton("View hours worked"); //set label to button
        b3 = new JButton("Register sick day"); //set label to button
        b4 = new JButton("Register holiday"); //set label to button
        b5 = new JButton("Assign project manager"); //set label to button
        b5 = new JButton("Add project activity"); //set label to button

        //create panel to put form elements
        newPanel = new JPanel(new GridLayout(3, 1));
        newPanel.add(b1);
        newPanel.add(b2);
        newPanel.add(b3);
        newPanel.add(b4);
        newPanel.add(b5);

        add(newPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.
                WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Welcome");
        setSize(400, 200);
    }
}  