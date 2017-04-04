package gruppnan.timeline;

import java.util.Date;

/**
 * Created by Melina on 04/04/2017.
 */

public class DefaultEvent extends Event {

    private Date startDate, endDate;
    private String description, name;
    private Course course;

    public DefaultEvent(Course course, String name, Date startDate, Date endDate, String description){
        super(course, name, endDate, description);
        this.startDate = startDate;
    }

    public DefaultEvent(String name, Date startDate, Date endDate, String description){
        super(name, endDate, description);
        this.startDate = startDate;
    }
}
