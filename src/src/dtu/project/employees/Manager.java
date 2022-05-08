package dtu.project.employees;

public class Manager extends Developer {

    public Manager(String ini) {
        super(ini);
        super.isProjectManager = true;
    }


    public Manager(Developer dev) {
        super(dev.initials);
        super.isProjectManager = true;
    }



}
