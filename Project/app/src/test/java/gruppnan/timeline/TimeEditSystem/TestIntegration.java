package gruppnan.timeline.TimeEditSystem;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import gruppnan.timeline.controller.CourseSystemInterface;
import gruppnan.timeline.controller.TimeEditHandler;
import gruppnan.timeline.model.CourseRepository;
import gruppnan.timeline.model.EventRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Nikolai on 2017-05-28.
 */

public class TestIntegration {

    CourseSystemInterface csi;
    Calendar calFrom;
    Calendar calTo;
    EventRepository er;
    CourseRepository cr;

    @Before
    public void prepareFetcher(){
        csi = new TimeEditHandler();
        calFrom = Calendar.getInstance();
        calFrom.add(Calendar.WEEK_OF_YEAR,-2);
        calTo = Calendar.getInstance();
        calTo.setTime(calFrom.getTime());
        calTo.add(Calendar.MONTH,1);
        er = EventRepository.getEventRepository();
        cr = CourseRepository.getCourseRepository();
    }
    @Test
    public void testSearchAndAddEvents(){

        csi.searchCourses("dat255");
        assertEquals(cr.getAllCourses().size(),0);
        assertEquals(er.getEventMap().size(),0);
        csi.getAddEvents("DAT255, Software engineering project",calFrom.getTime(),calTo.getTime());
        assertEquals(cr.getAllCourses().size(),1);
        assertTrue(er.getEventMap().size()>0);
		



    }
}
