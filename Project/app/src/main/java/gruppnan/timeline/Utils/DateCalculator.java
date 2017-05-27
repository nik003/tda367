package gruppnan.timeline.Utils;

import java.util.Calendar;

import gruppnan.timeline.model.WeekDates;


/**
 * Created by Nikolai on 2017-05-04.
 * Calculates dates from given times
 */

public abstract class DateCalculator {

    public static WeekDates getCurrentWeekDates() {
        WeekDates wd;

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.setFirstDayOfWeek(cal.MONDAY);
        cal.add(Calendar.DAY_OF_MONTH,(2-(cal.get(cal.DAY_OF_WEEK))));
         wd = getWeekDates(cal);

        return wd;
    }

    /**
     *
     * @param day the date to be counted from
     * @return the next monday as a calendar object
     */
    private static Calendar getNextMonday(Calendar day){
        Calendar nextDay = (Calendar)day.clone();
        nextDay.add(nextDay.DAY_OF_MONTH,1);
     return nextDay;
    }

    /**
     *  Gets the previous monday from the date
     * @param day the date to be counted from
     * @return the previous monday as a calendar object
     */
    private static Calendar getPrevMonday(Calendar day){
        Calendar prevDay = (Calendar)day.clone();
        prevDay.add(prevDay.DAY_OF_MONTH,-7);

        return prevDay;
    }

    /**
     * Get the week start, end, next monday and previous monday dates
     * @param monday  this monday
     * @return The dates as an object
     */
    public static WeekDates getWeekDates(Calendar monday){
        WeekDates wd = new WeekDates();

        wd.setThisMonday((Calendar) monday.clone());
        wd.setPrevMonday(getPrevMonday(monday));

        monday.add(monday.DAY_OF_MONTH,6);

        wd.setThisSunday((Calendar)monday.clone());
        wd.setNextMonday(getNextMonday(monday));

        return wd;
    }
}
