package gruppnan.timeline.model;

import java.util.Date;


public class DeadlineEvent extends Event {

    protected Date endDate;
    protected String description, name;
    protected Course course;

    public DeadlineEvent(Course course, String name, String description, Date endDate){
        super(course, name, endDate, description);
        this.course = course;
        this.name = name;
        this.description = description;
        this.endDate = endDate;
    }

    public String getName(){
        return name;
    }

    public String getDate(){
        return "28 June";
    }

}
