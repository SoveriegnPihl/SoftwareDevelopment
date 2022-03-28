package dtu.dto;

import dtu.employees.Developer;

public class developerInfo {

    private String initials;

    public developerInfo (String initials) {
        this.initials = initials;
    }
  /*  public developerInfo(Developer developer){
        this.initials = developer.getInitials();
    }

   */
    public String getInitials() { return initials;}
}
