package gruppnan.timeline;

import java.util.Date;

/**
 * Created by Melina on 04/04/2017.
 */

public abstract class Event extends Course{

    private Date endDate;
    private String description, name;
    private Course course;

    public Event(Course course, String name, Date endDate, String description){
        this.course = course;
        this.name = name;
        this.endDate = endDate;
        this.description = description;
    }

    public Event(String name, Date endDate, String description){
        this.name = name;
        this.endDate = endDate;
        this.description = description;
    }
}
