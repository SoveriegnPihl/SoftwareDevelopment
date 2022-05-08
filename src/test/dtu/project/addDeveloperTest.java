package dtu.project;


import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;

public class addDeveloperTest {

    @Test
    public void addDeveloperTestA(){
        Developer ekki = SoftwareHuset.developers.get("ekki");
        SoftwareHuset.addDeveloper(new String[]{ekki.getInitials(),"noOcc","noSick"});
    }

    @Test
    public void addDeveloperTestB(){
        //Developer developer = new Developer("marc");
        //SoftwareHuset.addDeveloper(new String[]{developer.getInitials(),"noOcc","noSick"});

    }

    @Test
    public void addDeveloperTestC(){
        //Developer developer = new Developer("marc");
        //SoftwareHuset.addDeveloper(new String[]{developer.getInitials(),"occ", "noSick", "2022", "10", "10", "2022", "11", "11"});
    }


    @Test
    public void addDeveloperTestD(){

    }

    @Test
    public void addDeveloperTestE(){

    }





}
