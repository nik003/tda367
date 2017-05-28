package gruppnan.timeline.TimeEditSystem;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Nikolai on 2017-05-28.
 */

public class TestFetcherMethods {
    TimeEditFetcher tef;
    Calendar calFrom;
    Calendar calTo;
    @Before
    public void TimeEditPrepare(){
        tef = new TimeEditFetcher();
        calFrom = Calendar.getInstance();
        calFrom.add(Calendar.WEEK_OF_YEAR,-2);
        calTo = Calendar.getInstance();
        calTo.setTime(calFrom.getTime());
        calTo.add(Calendar.MONTH,1);

    }

    @Test
    public void testSendHTTP(){
        String httpData;
        httpData = tef.sendHttpGet("http://www.google.com");
        System.out.println(httpData);
        assertNotEquals(httpData,null);
        assertEquals(httpData.substring(0,9),"<!doctype");

    }

    @Test
    public void testGetCourses(){
        List<TimeEditEvent> list;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        List<String> result;
        result = tef.searchCourse("dat255");
        System.out.println(sdf.format(calFrom.getTime())+"-"+sdf.format(calTo.getTime()));
        list = tef.getIcs("DAT255, Software engineering project",sdf.format(calFrom.getTime()),sdf.format(calTo.getTime()));
        System.out.println(list.size());
        assertTrue(list.size()>1);

    }
    @Test
    public void testSearchCourses(){
        List<String> result;
        result = tef.searchCourse("dat255");
        assertEquals(result.size(),1);
        result = tef.searchCourse(null);
        assertEquals(result.get(0),"No course found");
    }
}
