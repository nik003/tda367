package gruppnan.timeline;

import java.util.Date;


public abstract class Event{

    protected Date endDate;
    protected String description, name;
    protected Course course;

    public Event(Course course, String name, Date endDate, String description){
        this.course = course;
        this.name = name;
        this.endDate = endDate;
        this.description = description;
    }



}
