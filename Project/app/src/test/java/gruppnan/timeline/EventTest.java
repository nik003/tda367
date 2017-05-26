package gruppnan.timeline;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import gruppnan.timeline.TimeEditSystem.TimeEditFetcher;
import gruppnan.timeline.controller.TimeEditHandler;
import gruppnan.timeline.model.Event;
import gruppnan.timeline.model.EventContainer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;


public class EventTest {
    /*
    private EventContainer container = EventContainer.getEventContainer();
    private SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private TimeEditHandler th = new TimeEditHandler();

    private TimeEditFetcher tf = new TimeEditFetcher();
    private Date firstJan;
    private Date ninthFeb;
    private Date tenthFeb;


    @Before
    public void setUpDates() throws ParseException{
        firstJan =  dateParser.parse("01/01/2017 00:00");
        ninthFeb = dateParser.parse("09/02/2017 00:00");
        tenthFeb = dateParser.parse("10/02/2017 10:00");
    }

    @Test
    public void testEventsValues(){
        container.addEvent(container.createDeadlineEvent(null, "tenta", "Examinations for biology", firstJan,false));
        assertEquals("tenta", container.getEventMap().get(1).getName());
    }

    test if instances are stored correctly in the different maps
    @Test
    public void testEventMaps(){
        Event e = container.createDeadlineEvent(null, "tenta","Examinations for biology", firstJan,false);
        container.addEvent(e);
        Event k = container.createDeadlineEvent(null, "presentation","Presentation for math", ninthFeb,false);
        container.addEvent(k);
        Event c = container.createDefaultEvent(null,"lecture","some random lecture",tenthFeb, tenthFeb);
        container.addEvent(c);
        assertTrue(container.getDeadlineEventMap().size()==2);
        assertTrue(container.getEventMap().size()==3);
        assertTrue(container.getDefaultEventMap().size()==1);
    }

    @Test
    public void testAddAndRemove(){
        Event e = container.createDeadlineEvent(null, "tenta","Examinations for biology", firstJan,false);
        container.addEvent(e);
        Event k = container.createDeadlineEvent(null, "presentation","Presentation for math", ninthFeb,false);
        container.addEvent(k);
        Event c = container.createDefaultEvent(null,"lecture","some random lecture",tenthFeb, tenthFeb);
        container.addEvent(c);
        System.out.println(container.getEventMap().size());
        assertTrue(container.getEventMap().size()==3);
        //container.removeEvent(c);
        assertTrue(container.getEventMap().size()==2);

    }

    @Test
    public void getKey() {
        Event e = container.createDeadlineEvent(null, "presentation", "Presentation for math", ninthFeb, false);
        container.addEvent(e);
        Event c = container.createDefaultEvent(null, "lecture", "some random lecture", tenthFeb, tenthFeb);
        container.addEvent(c);
        assertTrue(c.getID() == 2);


        assertTrue(container.getDeadlineEventMap().get("D0").getName().equals("tenta"));
        assertTrue(container.getDeadlineEventMap().get("D1").getEndDate() == ninthFeb);
        assertNotEquals(container.getDeadlineEventMap().get("D0").getEndDate(), container.getDefaultEventMap().get("Def0"));

        assertTrue(container.getDefaultEventMap().get("Def0").getDescription().equals("some random lecture"));
        assertNotEquals(tf.sendHttpGet("https://se.timeedit.net/web/chalmers/db1/public/objects.html?max=15&fr=t&partajax=t&;im=f&sid=3&l=sv_SE&search_text=tda367&types=182"), null);
        assertNotEquals(tf.searchCourse("tda3"), "No course found");
        tf.getIcs("TDA335, Individual project in interaction design, major", "20170101", "20170502");
    }
    @Test
    public void getTimeEditEvents(){
        Calendar c =Calendar.getInstance();
        Date dc = c.getTime();
        c.add(Calendar.MONTH,5);
        Date d = c.getTime();

        ArrayList<String> al = (ArrayList)th.searchCourses("tda367");
        assertTrue(al.size()==1);
        th.getAddEvents(al.get(0),dc,d);
        assertTrue(container.getDefaultEventMap().size()>7);


    }
    */
}
