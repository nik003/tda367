package gruppnan.timeline.model;

import java.util.Date;


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
