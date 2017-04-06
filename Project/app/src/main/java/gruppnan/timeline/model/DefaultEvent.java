package gruppnan.timeline.model;

import java.util.Date;


public class DefaultEvent extends Event {

    private Date startDate, endDate;
    private String description, name;
    private Course course;

    public DefaultEvent(Course course, String name, Date startDate, Date endDate, String description){
        super(course, name, endDate, description);
        this.startDate = startDate;
    }

    public Date getStartDate(){
        return this.startDate;
    }


}
