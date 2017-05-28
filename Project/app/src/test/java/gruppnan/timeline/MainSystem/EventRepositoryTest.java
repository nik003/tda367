package gruppnan.timeline.MainSystem;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import gruppnan.timeline.model.Course;
import gruppnan.timeline.model.DeadlineEvent;
import gruppnan.timeline.model.DefaultEvent;
import gruppnan.timeline.model.Event;
import gruppnan.timeline.model.EventRepository;

/**
 * @author Hannes
 * This class tests EventRepository as well as the different Event classes as
 * EventRepository uses these classes.
 */

public class EventRepositoryTest {


    private EventRepository eventRepo;
    private Course course;
    private Date date1,date2;
    @Before
    public void setUp(){

        course = new Course("TDA367", "ProgrammeringsProjekt");
        date1 = new Date(1495876773);
        date2 = new Date(1495880373);
        eventRepo = EventRepository.getEventRepository();
        eventRepo.removeAll();
    }

    /** testing creation of default events through EventRepository which handles this.
     *  testing if the containers that hold these events function properly
     */
    @Test
    public void testCreateDefaultEvent(){

        DefaultEvent def1 = eventRepo.createDefaultEvent(course, "Name", "this is a test",date1,date2);
        assertTrue(eventRepo.getDefaultEventMap().size()==1);

        DefaultEvent def2 = eventRepo.createDefaultEvent(course, "test",null,date1,date2);
        assertTrue(eventRepo.getDefaultEventMap().size()==2);
        assertTrue(eventRepo.getEventMap().size()==2);

        eventRepo.removeEvent(def2.getID());
        assertTrue(eventRepo.getDefaultEventMap().size()==1);
        assertTrue(eventRepo.getEventMap().size()==1);
        assertTrue(eventRepo.getDeadlineEventMap().size()==0);
        assertTrue(eventRepo.getEventMap().get(def1.getID()).getName().equalsIgnoreCase("name"));

        eventRepo.removeAll();
        assertTrue(eventRepo.getDefaultEventMap().size()==0);
    }

    /** testing creation of deadline events through EventRepository which handles this.
     *  testing if the containers that hold these events function properly
     */
    @Test
    public void testCreateDeadline(){
        DeadlineEvent dead1 = eventRepo.createDeadlineEvent(course,"testar","this is deadline",date1,false);
        DeadlineEvent dead2 = eventRepo.createDeadlineEvent(course, "namn",null,date2,false);
        assertEquals(2,eventRepo.getDeadlineEventMap().size(),eventRepo.getEventMap().size());

        String deadlineName = eventRepo.getDeadlineEventMap().get(dead1.getID()).getName();
        String expectedName = "testar";
        assertTrue(deadlineName.equalsIgnoreCase(expectedName));

        eventRepo.removeEvent(dead1.getID());
        assertEquals(1,eventRepo.getDeadlineEventMap().size(),eventRepo.getEventMap().size());
        eventRepo.removeAll();
        assertEquals(0,eventRepo.getDeadlineEventMap().size(),eventRepo.getEventMap().size());
    }

    /** tests method that returns list with events within specified time interval
     *  Changing event's endDate to after specified time interval, which removes it from given list.
     */
    @Test
    public void testGetEventsByDate(){
        DeadlineEvent ev = eventRepo.createDeadlineEvent(course,"eventsByDate",null,date2,false);
        Calendar calStart = Calendar.getInstance();
        calStart.setTime(date1);
        Calendar calEnd = Calendar.getInstance();
        Date end = new Date(1495880375);
        calEnd.setTime(end);


        ArrayList<Event> list = eventRepo.getEventsByDates(calStart,calEnd);
        assertTrue(list.size()==1);

        // set event endDate to later time than, this means it shouldn't be included in the next list.
        Date afterEnd = new Date(1495880375+10);
        ev.setEndDate(afterEnd);

        list = eventRepo.getEventsByDates(calStart,calEnd);
        assertTrue(list.size()==0);

    }
}
