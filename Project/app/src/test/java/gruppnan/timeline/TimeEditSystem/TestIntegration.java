package gruppnan.timeline.TimeEditSystem;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import gruppnan.timeline.controller.CourseSystemInterface;
import gruppnan.timeline.controller.TimeEditHandler;

/**
 * Created by Nikolai on 2017-05-28.
 */

public class TestIntegration {

    CourseSystemInterface csi;
    Calendar calFrom;
    Calendar calTo;

    @Before
    public void prepareFetcher(){
        csi = new TimeEditHandler();
        calFrom = Calendar.getInstance();
        calFrom.add(Calendar.WEEK_OF_YEAR,-2);
        calTo = Calendar.getInstance();
        calTo.setTime(calFrom.getTime());
        calTo.add(Calendar.MONTH,1);
    }
    @Test
    public void testSearchAndAddEvents(){
        csi.searchCourses("dat255");
        csi.getAddEvents("DAT255, Software engineering project",calFrom.getTime(),calTo.getTime());
    }
}
