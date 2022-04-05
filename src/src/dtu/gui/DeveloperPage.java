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
    int yCountL =50, yCountR = 50;
    //constructor  
    DeveloperPage( SoftwareHuset softwareHuset, Main parentWindow) {
        this.softwareHuset = softwareHuset;
        this.parentWindow = parentWindow;
        initialize();
    }

    public void initialize() {

        developerPage = new JPanel();
        parentWindow.addPanel(developerPage);
        developerPage.setLayout(null);
        developerPage.setBorder(BorderFactory.createTitledBorder(
                "Developer page"));

        JButton regHbut = makeLeftButton("Register hours worked");
        JButton viewHbut = makeLeftButton("View hours worked");
        JButton regSickBut = makeLeftButton("Register sick day");
        JButton regHoliBut = makeLeftButton("Register holiday");
        JButton addActBut = makeLeftButton("Add project activity");
       // JButton createProBut = makeLeftButton("Create project");
        JButton backBut = makeLeftButton("Back");

        JButton addPmBut = makeRightButton("Assign project manager");


        regHbut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OptionPane OP = new OptionPane(user1, "Register hours worked");
            }
        });
       viewHbut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OptionPane OP = new OptionPane(user1, "View hours worked");
            }
        });

        addPmBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OptionPane OP = new OptionPane(user1, "Assign project manager");
            }
        });

        backBut.addActionListener(new ActionListener() {
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

    public JButton makeLeftButton(String name){
        JButton b1 = new JButton(name);
        b1.setBounds(25, yCountL, 193, 29);
        developerPage.add(b1);
        yCountL+=50;
        return b1;
    }
    public JButton makeRightButton(String name){
        JButton b1 = new JButton(name);
        b1.setBounds(285, yCountR, 193, 29);
        developerPage.add(b1);
        yCountR+=50;
        return b1;
    }

}  