package gruppnan.timeline.model;

import java.util.Date;


public class DeadlineEvent extends Event {

    protected Date endDate;
    protected String description, name;
    protected Course course;

    public DeadlineEvent(Course course, String name, Date endDate, String description){
        super(course, name, endDate, description);
    }
}
