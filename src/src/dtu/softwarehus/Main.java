package dtu.softwarehus;

import dtu.gui.CreateLoginForm;

import javax.swing.*;

public class Main {

    public static void main(String[] args){

        try
        {
            //create instance of the CreateLoginForm
            CreateLoginForm form = new CreateLoginForm();
            SoftwareHuset.startProgram();

            form.setSize(500,200);  //set size of the frame
            form.setLocationRelativeTo(null);
            form.setVisible(true);  //make form visible to the user

        }
        catch(Exception e)
        {
            //handle exception
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
