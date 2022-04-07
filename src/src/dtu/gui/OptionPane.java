package dtu.gui;
import dtu.employees.Developer;
import dtu.softwarehus.SoftwareHuset;

import javax.swing.*;

class OptionPane extends JFrame {
    SoftwareHuset softwareHuset = new SoftwareHuset();
    JFrame alertFrame;

    OptionPane(Developer user, String method) {
        if (method.equals("Register hours worked")) {
            alertFrame = new JFrame();
            alertFrame.setLocationRelativeTo(null);
            String hours = JOptionPane.showInputDialog(alertFrame, method);
            user.addHours(Integer.parseInt(hours));

        }
        if (method.equals("View hours worked")) {
            alertFrame = new JFrame();
            alertFrame.setLocationRelativeTo(null);
            int hours = user.getHours();
            JOptionPane.showMessageDialog(alertFrame, "You have worked " + hours + " today");

        }
        if (method.equals("View available developers")) {
            alertFrame = new JFrame();
            alertFrame.setLocationRelativeTo(null);
            JOptionPane.showMessageDialog(alertFrame, SoftwareHuset.listDevelopers());
        }
        /*if (method.equals("Create project")) {
            f = new JFrame();
            f.setLocationRelativeTo(null);
            String name = JOptionPane.showInputDialog(f, "Please enter your initials");
            String startWeek = JOptionPane.showInputDialog(f, "Please enter start week");
            String endWeek = JOptionPane.showInputDialog(f, "Please enter end week");
            String budget = JOptionPane.showInputDialog(f, "Please enter budget");
            softwareHuset.createProject(Integer.parseInt(startWeek),Integer.parseInt(endWeek),Integer.parseInt(budget));
        }*/

        if (method.equals("Assign project manager")) {
            alertFrame = new JFrame();
            alertFrame.setLocationRelativeTo(null);
            String name = JOptionPane.showInputDialog(alertFrame, "Who do you want to assign");
            String project = JOptionPane.showInputDialog(alertFrame, "Which project?");
            SoftwareHuset.assignPM(name,Integer.parseInt(project));
        }
        if(method.equals("Add project activity")){
            alertFrame = new JFrame();
            alertFrame.setLocationRelativeTo(null);
            String activityName = JOptionPane.showInputDialog(alertFrame, "Which activity do you want to add");
            String project = JOptionPane.showInputDialog(alertFrame, "To which project?");
            //SoftwareHuset.projects.get(project).addActivity();
        }

    }
}
