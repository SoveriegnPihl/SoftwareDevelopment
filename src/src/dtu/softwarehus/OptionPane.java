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
    }
}
