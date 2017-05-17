package gruppnan.timeline.model;

import java.util.Date;


public class DefaultEvent extends Event {

    private Date startDate, endDate;
    private String description, name;
    private Course course;

    public DefaultEvent(Course course, String name, String description, Date startDate, Date endDate){
        super(course, name, endDate, description);
        this.startDate = new Date(startDate.getTime());
    }

    public Date getStartDate(){
        return new Date(this.startDate.getTime());
    }


}
