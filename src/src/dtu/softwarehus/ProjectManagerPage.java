package dtu.softwarehus;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectManagerPage extends JFrame {
    String initials;
    JButton b1,b2,b3,b4,b5;
    JPanel newPanel;
    JLabel userLabel;
    JTextField textField1;
    private JPanel panel1;
    private JCheckBox checkBox1;
    boolean managerCheckBox = false;
    //constructor
    ProjectManagerPage(String userValue)
    {
        initials = userValue;
        //create a welcome label and set it to the new page
        //create submit button

        b1 = new JButton("Add developer"); //set label to button
        b2 = new JButton("View *available* developers"); //set label to button
        b3 = new JButton("Make new project"); //set label to button
        b4 = new JButton("Add developer to project"); //set label to button
        b5 = new JButton("Add project activity"); //set label to button
        b5 = new JButton("Get project report"); //set label to button

        //create panel to put form elements
        newPanel = new JPanel(new GridLayout(3, 1));
        newPanel.add(b1);
        newPanel.add(b2);
        newPanel.add(b3);
        newPanel.add(b4);
        newPanel.add(b5);

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OptionPane OP = new OptionPane(initials,"View available developers");
            }
        });


        add(newPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.
                WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Welcome");
        setSize(400, 200);
    }
}

