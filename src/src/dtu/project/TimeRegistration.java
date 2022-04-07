package dtu.project;


import dtu.employees.Developer;

import java.util.GregorianCalendar;

public class TimeRegistration {
    private GregorianCalendar date;
    private float amountOfTime;
    private Developer developer;

    // We have chosen to make the key the day for an employee for an activity
    public TimeRegistration(Developer developer, GregorianCalendar date, float amountOfTime){
//            throws OperationNotAllowedException {
/*
        if (amountOfTime < 0) {
            throw new OperationNotAllowedException("You cannot report negative hours");
        }
*/
        this.developer = developer;
        this.date = date;
        this.amountOfTime = amountOfTime;
    }

    public Developer getEmployee() {
        return developer;
    }

    public float getAmountOfTime() {
        return amountOfTime;
    }

    public void correctTime(float amountOfTime) {
        this.amountOfTime = amountOfTime;
    }

    public String toString() {
        int year = date.get(GregorianCalendar.YEAR);

        // The month is zero indexed in GregorianCalendar. Therefore we must add one month to get the correct month.
        int month = date.get(GregorianCalendar.MONTH) + 1;

        int day = date.get(GregorianCalendar.DATE);
        return amountOfTime + " hours on " + day + "/" + month + "/" + year + " for " + developer.getInitials();
    }

    public boolean match(Developer developer, GregorianCalendar date) {
        boolean year = this.date.get(GregorianCalendar.YEAR) == date.get(GregorianCalendar.YEAR);
        boolean month = this.date.get(GregorianCalendar.MONTH) == date.get(GregorianCalendar.MONTH);
        boolean day = this.date.get(GregorianCalendar.DATE) == date.get(GregorianCalendar.DATE);
        boolean employeeMatch;
        if (this.developer == null && developer == null) {
            employeeMatch = true;
        } else {
            employeeMatch = this.developer.equals(developer);
        }
        return employeeMatch && year && month && day;
    }

}
