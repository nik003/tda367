package gruppnan.timeline.model;

import java.util.Calendar;
import java.util.Observable;

/**
 * Created by Nikolai on 2017-05-04.
 */

public abstract class dateCalc{

    public static Calendar[] getCurrentWeekDates() {
        Calendar cal = Calendar.getInstance();
        Calendar[] dates = new Calendar[4];
        cal.setFirstDayOfWeek(cal.MONDAY);

        cal.add(cal.DAY_OF_MONTH,(2-(cal.get(cal.DAY_OF_WEEK))));
        dates[0] = (Calendar) cal.clone();
        dates[3] = getPrevMonday(cal);

        cal.add(cal.DAY_OF_MONTH,6);
        dates[1] = (Calendar)cal.clone();
        dates[2] = getNextMonday(cal);


        return dates;
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
    public static Calendar[] getWeekDates(Calendar monday){
        Calendar[] dates = new Calendar[4];
        dates[0] = (Calendar) monday.clone();
        dates[3] = getPrevMonday(monday);

        monday.add(monday.DAY_OF_MONTH,6);
        dates[1] = (Calendar)monday.clone();
        dates[2] = getNextMonday(monday);
        return dates;
    }
}
