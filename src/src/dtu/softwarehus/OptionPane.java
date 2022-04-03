package dtu.softwarehus;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class OptionPane extends JFrame {
    SoftwareHuset softwareHuset = new SoftwareHuset();
    JFrame f;

    OptionPane(String userValue, String method) {
        if (method.equals("Register hours worked")) {
            f = new JFrame();
            f.setLocationRelativeTo(null);
            String hours = JOptionPane.showInputDialog(f, method);
            softwareHuset.addHours(userValue, Integer.valueOf(hours));
        }
        if (method.equals("View hours worked")) {
            f = new JFrame();
            f.setLocationRelativeTo(null);
            int hours = softwareHuset.workedHoursReport(userValue);
            JOptionPane.showMessageDialog(f, "You have worked " + hours + " today");

        }
        if (method.equals("View available developers")) {
            f = new JFrame();
            f.setLocationRelativeTo(null);
            JOptionPane.showMessageDialog(f, softwareHuset.listDevelopers());
        }
        if (method.equals("Create project")) {
            f = new JFrame();
            f.setLocationRelativeTo(null);
            String name = JOptionPane.showInputDialog(f, "Please enter your initials");
            String startWeek = JOptionPane.showInputDialog(f, "Please enter start week");
            String endWeek = JOptionPane.showInputDialog(f, "Please enter end week");
            String budget = JOptionPane.showInputDialog(f, "Please enter budget");
            softwareHuset.createProject(name,Integer.valueOf(startWeek),Integer.valueOf(endWeek),Integer.valueOf(budget));


        }
    }
}
