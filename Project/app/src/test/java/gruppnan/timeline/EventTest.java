package gruppnan.timeline;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import gruppnan.timeline.model.Event;
import gruppnan.timeline.model.EventContainer;

import static org.junit.Assert.*;


public class EventTest {

    private EventContainer container = EventContainer.getEventContainer();
    private SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy HH:mm");
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
        container.addEvent(container.createDeadlineEvent(null, "tenta", "Examinations for biology", firstJan));
        assertEquals("tenta", container.getEventMap().get(1).getName());
    }

    /** test if instances are stored correctly in the different maps */
    @Test
    public void testEventMaps(){
        Event e = container.createDeadlineEvent(null, "tenta","Examinations for biology", firstJan);
        container.addEvent(e);
        Event k = container.createDeadlineEvent(null, "presentation","Presentation for math", ninthFeb);
        container.addEvent(k);
        Event c = container.createDefaultEvent(null,"lecture","some random lecture",tenthFeb, tenthFeb);
        container.addEvent(c);
        assertTrue(container.getDeadlineEventMap().size()==2);
        assertTrue(container.getEventMap().size()==3);
        assertTrue(container.getDefaultEventMap().size()==1);
    }

    @Test
    public void testAddAndRemove(){
        Event e = container.createDeadlineEvent(null, "tenta","Examinations for biology", firstJan);
        container.addEvent(e);
        Event k = container.createDeadlineEvent(null, "presentation","Presentation for math", ninthFeb);
        container.addEvent(k);
        Event c = container.createDefaultEvent(null,"lecture","some random lecture",tenthFeb, tenthFeb);
        container.addEvent(c);
        System.out.println(container.getEventMap().size());
        assertTrue(container.getEventMap().size()==3);
        container.removeEvent(c);
        assertTrue(container.getEventMap().size()==2);

    }

    @Test
    public void getKey(){
        Event e = container.createDeadlineEvent(null, "presentation", "Presentation for math", ninthFeb);
        container.addEvent(e);
        Event c = container.createDefaultEvent(null,"lecture","some random lecture",tenthFeb, tenthFeb);
        container.addEvent(c);
        assertTrue(c.getKey()==2);

    }
}
