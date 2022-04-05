package dtu.gui;//import required classes and packages
import dtu.employees.Developer;

import dtu.softwarehus.SoftwareHuset;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//create NewPage class to create a new page on which user will navigate  
public class DeveloperPage
{
    static Developer user1;
    JButton b1,b2,b3,b4,b5,b6,b7,b8;
    JPanel developerPage;
    JLabel userLabel;
    JTextField textField1;
    private JPanel panel1;
    private JCheckBox checkBox1;
    boolean managerCheckBox = false;
    SoftwareHuset softwareHuset;
    Main parentWindow;
    //constructor  
    DeveloperPage( SoftwareHuset softwareHuset, Main parentWindow) {
        this.softwareHuset = softwareHuset;
        this.parentWindow = parentWindow;
        initialize();
    }

    public void initialize() {
        //create a welcome label and set it to the new page
        //create submit button

        b1 = new JButton("Register hours worked"); //set label to button
        b2 = new JButton("View hours worked"); //set label to button
        b3 = new JButton("Register sick day"); //set label to button
        b4 = new JButton("Register holiday"); //set label to button
        b5 = new JButton("Assign project manager"); //set label to button
        b6 = new JButton("Add project activity"); //set label to button
        b7 = new JButton("Create project");
        b8 = new JButton("Back");
        //create panel to put form elements
        developerPage = new JPanel();
        parentWindow.addPanel(developerPage);
        developerPage.setLayout(null);
        developerPage.setBorder(BorderFactory.createTitledBorder(
                "Developer page"));
        b1.setBounds(25, 50, 193, 29);
        developerPage.add(b1);
        b2.setBounds(25, 100, 193, 29);
        developerPage.add(b2);
        b3.setBounds(25, 150, 193, 29);
        developerPage.add(b3);
        b4.setBounds(25, 200, 193, 29);
        developerPage.add(b4);
        b6.setBounds(25, 250, 193, 29);
        developerPage.add(b6);
        b8.setBounds(20, 300, 193, 29);
        developerPage.add(b8);

        b5.setBounds(285, 50, 193, 29);
        developerPage.add(b5);
        //b7.setBounds(325, 50, 193, 29);
       // developerPage.add(b7);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OptionPane OP = new OptionPane(user1, "Register hours worked");
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OptionPane OP = new OptionPane(user1, "View hours worked");
            }
        });

        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OptionPane OP = new OptionPane(user1, "Assign project manager");
            }
        });

       /* b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // setVisible(false);
                CreateProjectPage OP = new CreateProjectPage();
                OP.setSize(500, 500);  //set size of the frame
                OP.setLocationRelativeTo(null);
                OP.setVisible(true);


            }
        });*/

        b8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                parentWindow.setVisible(true);
            }
        });
    }
        public void setVisible(boolean visi){
            developerPage.setVisible(visi);
        }
        public static void setUser(Developer user){
        user1 = user;
        }

}  