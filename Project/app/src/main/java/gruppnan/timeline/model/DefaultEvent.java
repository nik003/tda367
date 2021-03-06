package gruppnan.timeline.model;

import java.util.Date;
/**
 * @author Everyone
 * Model for Default events
 * Used by: EventListener,EventRepository,WeekCalendarView,EventViewer
 * Uses:Course,Event
 *
 */

public class DefaultEvent extends Event {

    private Date startDate;

    public DefaultEvent(Course course, String name, String description, Date startDate, Date endDate){
        super(course, name, endDate, description);
        this.startDate = (Date) startDate.clone();
    }

    @Override
    public Date getStartDate(){
        return (Date) startDate.clone();
    }


}
