package gruppnan.timeline.model;

import java.util.Calendar;


/**
 * Created by Nikolai on 2017-05-04.
 * Calculates dates from given times
 */

public abstract class DateCalc {

    public static WeekDates getCurrentWeekDates() {
        WeekDates wd;

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        Calendar[] dates = new Calendar[4];
        cal.setFirstDayOfWeek(cal.MONDAY);

        cal.add(Calendar.DAY_OF_MONTH,(2-(cal.get(cal.DAY_OF_WEEK))));
        wd = getWeekDates(cal);
        dates[0] = (Calendar) cal.clone();
        dates[3] = getPrevMonday(cal);

        cal.add(cal.DAY_OF_MONTH,6);
        dates[1] = (Calendar)cal.clone();
        dates[2] = getNextMonday(cal);

        return wd;
    }
    private static Calendar getNextMonday(Calendar day){
        Calendar nextDay = (Calendar)day.clone();
        nextDay.add(nextDay.DAY_OF_MONTH,1);
     return nextDay;
    }
    private static Calendar getPrevMonday(Calendar day){
        Calendar prevDay = (Calendar)day.clone();
        prevDay.add(prevDay.DAY_OF_MONTH,-7);

        return prevDay;
    }
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
