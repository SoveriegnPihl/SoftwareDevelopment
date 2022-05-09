package dtu.gui;

import dtu.project.Developer;
import dtu.project.Project;
import dtu.project.SoftwareHuset;

import javax.swing.*;

class OptionPane extends JFrame {

    //Lavet af Victor Winther

    OptionPane(String method) {
        if (method.equals("View available developers")) {
            JFrame alertFrame = new JFrame();
            alertFrame.setLocationRelativeTo(null);
            JOptionPane.showMessageDialog(alertFrame, SoftwareHuset.listAvailableDevelopers());
        }
        if (method.equals("Add developer to project")) {
            JFrame alertFrame = new JFrame();
            alertFrame.setLocationRelativeTo(null);
            String developerToAdd = JOptionPane.showInputDialog(alertFrame, method);
            int projectToAddDevTo = Integer.parseInt(JOptionPane.showInputDialog(alertFrame, "What project to add developer to"));

            Developer developer = SoftwareHuset.developers.get(developerToAdd);
            Project project = SoftwareHuset.projects.get(projectToAddDevTo);
            project.addDeveloper(developer);
        }
        if (method.equals("Register sick day")) {
            JFrame alertFrame = new JFrame();
            alertFrame.setLocationRelativeTo(null);
            int isSick = JOptionPane.showConfirmDialog(alertFrame, "Are you gonna call in sick today?");
            if (isSick == JOptionPane.YES_OPTION) {
                DeveloperPage.loggedInUser.setSick();
            }
        }
        if (method.equals("Add developer")) {
            JFrame alertFrame = new JFrame();
            alertFrame.setLocationRelativeTo(null);
            String developerToCreate = JOptionPane.showInputDialog(alertFrame, method);
            if (!(developerToCreate == null)) {
                if (developerToCreate.length() != 4) {
                    JFrame errorFrame = new JFrame();
                    errorFrame.setLocationRelativeTo(null);
                    JOptionPane.showMessageDialog(null, "Please use 4 initials!");
                } else {
                    SoftwareHuset.addDeveloper(new String[]{developerToCreate, "noOcc", "noSick"});
                }
            }
        }
    }

}
