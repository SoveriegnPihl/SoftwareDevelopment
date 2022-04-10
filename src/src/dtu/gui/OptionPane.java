package dtu.gui;
import dtu.employees.Developer;
import dtu.project.Project;
import dtu.softwarehus.SoftwareHuset;

import javax.swing.*;

class OptionPane extends JFrame {

    OptionPane(Developer user, String method) {
        if (method.equals("Register hours worked")) {
            JFrame alertFrame = new JFrame();
            alertFrame.setLocationRelativeTo(null);
            String hours = JOptionPane.showInputDialog(alertFrame, method);
            user.addHours(Integer.parseInt(hours));

        }
        if (method.equals("View hours worked")) {
            JFrame alertFrame = new JFrame();
            alertFrame.setLocationRelativeTo(null);
            int hours = user.getHours();
            JOptionPane.showMessageDialog(alertFrame, "You have worked " + hours + " today");

        }
        if (method.equals("View available developers")) {
            JFrame alertFrame = new JFrame();
            alertFrame.setLocationRelativeTo(null);
            JOptionPane.showMessageDialog(alertFrame, SoftwareHuset.listDevelopers());
        }
        if (method.equals("Add developer")) {
            JFrame alertFrame = new JFrame();
            alertFrame.setLocationRelativeTo(null);
            String developerToCreate = JOptionPane.showInputDialog(alertFrame, method);
            SoftwareHuset.addDeveloper(new String[]{developerToCreate});
        }
        if (method.equals("Add developer to project")) {
            JFrame alertFrame = new JFrame();
            alertFrame.setLocationRelativeTo(null);
            String developerToAdd = JOptionPane.showInputDialog(alertFrame, method);
            int projectToAddDevTo = Integer.parseInt(JOptionPane.showInputDialog(alertFrame,"What project to add developer to"));

            Developer developer = SoftwareHuset.developers.get(developerToAdd);
            Project project = SoftwareHuset.projects.get(projectToAddDevTo);
            project.addDeveloper(developer);
            System.out.println("NOOOOO");
        }
        if (method.equals("Assign project manager")) {
            JFrame alertFrame = new JFrame();
            alertFrame.setLocationRelativeTo(null);
            String name = JOptionPane.showInputDialog(alertFrame, "Who do you want to assign");
            while (!SoftwareHuset.isDeveloper(name)){
                name = JOptionPane.showInputDialog(alertFrame, "Developer not found. Try Again");
            }
            String project = JOptionPane.showInputDialog(alertFrame, "Which project?");

                while (!SoftwareHuset.projects.containsKey(Integer.parseInt(project))) {
                    project = JOptionPane.showInputDialog(alertFrame, "Project not found. Try Again");
                }
                System.out.println("success mf");
                SoftwareHuset.assignPM(name,Integer.parseInt(project));


        }
        if(method.equals("Register sick day")){
            JFrame alertFrame = new JFrame();
            alertFrame.setLocationRelativeTo(null);
            int isSick = JOptionPane.showConfirmDialog(alertFrame, "Are you gonna call in sick today?");
            if (isSick == JOptionPane.YES_OPTION){
                DeveloperPage.loggedInUser.setSick();
            }


        }

    }
    OptionPane(String method) {
        if (method.equals("Add developer")) {
            JFrame alertFrame = new JFrame();
            alertFrame.setLocationRelativeTo(null);
            String developerToCreate = JOptionPane.showInputDialog(alertFrame, method);
            SoftwareHuset.addDeveloper(developerToCreate);
        }
    }

}
