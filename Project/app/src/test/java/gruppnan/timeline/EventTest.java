package gruppnan.timeline;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import gruppnan.timeline.model.EventContainer;

import static org.junit.Assert.*;


public class EventTest {

    private EventContainer container = EventContainer.getEventContainer();
    private SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy HH:mm");


    /**
     * Test to see that right value is stored correctly
     * @throws ParseException
     */
    @Test
    public void testAdd() throws ParseException {

        Date firstJan = dateParser.parse("01/01/2017 00:00");
        Date ninthFeb = dateParser.parse("09/02/2017 00:00");
        Date tenthFeb = dateParser.parse("10/02/2017 10:00");

        container.createDeadline(null, "tenta",firstJan, "Examinations for biology");
        container.createDeadline(null, "presentation", ninthFeb, "Presentation for math");
        container.createDefaultEvent(null,"lecture",tenthFeb,tenthFeb,"some random lecture");

        assertTrue(container.getDeadlineEventMap().get("D0").getName().equals("tenta"));
        assertTrue(container.getDeadlineEventMap().get("D1").getEndDate()==ninthFeb);
        assertNotEquals(container.getDeadlineEventMap().get("D0").getEndDate(), container.getDefaultEventMap().get("Def0"));
        assertTrue(container.getDefaultEventMap().get("Def0").getDescription().equals("some random lecture"));
    }
}
