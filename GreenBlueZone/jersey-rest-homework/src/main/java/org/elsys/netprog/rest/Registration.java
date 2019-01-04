package org.elsys.netprog.rest;

import java.util.Calendar;
import java.util.Date;

public class Registration {
    private String carReg;
    private Boolean active;
    private Date due;
    private Date lastAction;

    public Registration(String carReg) {
        this.carReg = carReg;
        this.active = true;
        this.lastAction = new Date();
        this.due = getDateOneHourFromDate(this.lastAction);
    }

    public void updateRegistration() {
        this.active = true;
        this.due = getDateOneHourFromDate(this.due);
        this.lastAction = new Date();
    }

    private Date getDateOneHourFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        return calendar.getTime();
    }

    public String getCarReg() {
        return carReg;
    }

    public Boolean getActive() {
        this.active = this.due.after(new Date());
        return active;
    }

    public String getDue() {
        return due.toString();
    }

    public String getLastAction() {
        return lastAction.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Registration that = (Registration) o;

        if (!carReg.equals(that.carReg)) return false;
        if (!active.equals(that.active)) return false;
        if (!due.equals(that.due)) return false;
        return lastAction.equals(that.lastAction);
    }

    @Override
    public int hashCode() {
        int result = carReg.hashCode();
        result = 31 * result + active.hashCode();
        result = 31 * result + due.hashCode();
        result = 31 * result + lastAction.hashCode();
        return result;
    }
}
