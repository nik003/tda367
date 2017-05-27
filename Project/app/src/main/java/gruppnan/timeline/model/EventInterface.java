package gruppnan.timeline.model;

import java.util.Date;

/**
 * @author Melina Andersson
 * Used By: EventAdapter, EvetnListener,EventAdapterView, Event
 * Uses: Course
 *
 */

public interface EventInterface {

    Date getEndDate ();

    String getDescription();

    String getName();

    Course getCourse();

    Date getStartDate();

    int getID();
}
