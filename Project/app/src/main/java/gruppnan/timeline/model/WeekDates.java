package gruppnan.timeline.model;

import java.util.Calendar;

/**
 * Created by Nikolai on 2017-05-11.
 */

public class WeekDates {
    private Calendar prevMonday;
    private Calendar nextMonday;
    private Calendar thisMonday;
    private Calendar thisSunday;

    public WeekDates() {

    }

    public void setPrevMonday(Calendar prevMonday) {
        this.prevMonday = prevMonday;
    }

    public void setNextMonday(Calendar nextMonday) {
        this.nextMonday = nextMonday;
    }

    public void setThisMonday(Calendar thisMonday) {
        this.thisMonday = thisMonday;
    }

    public void setThisSunday(Calendar thisSunday) {
        this.thisSunday = thisSunday;
    }

    public Calendar getPrevMonday() {
        return prevMonday;
    }

    public Calendar getNextMonday() {
        return nextMonday;
    }

    public Calendar getThisMonday() {
        return thisMonday;
    }

    public Calendar getThisSunday() {
        return thisSunday;
    }




}
