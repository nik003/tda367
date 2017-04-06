package gruppnan.timeline.model;

import java.util.Date;


public abstract class Event implements EventInterface{

    protected Date endDate;
    protected String description, name;
    protected Course course;

    public Event(Course course, String name, Date endDate, String description){
        this.course = course;
        this.name = name;
        this.endDate = endDate;
        this.description = description;
    }

    public Date getEndDate (){
        return this.endDate;
    }
    public String getDescription(){
        return this.description;
    }
    public String getName(){
        return this.name;
    }

    public Course getCourse(){
        return this.course;
    }


}
