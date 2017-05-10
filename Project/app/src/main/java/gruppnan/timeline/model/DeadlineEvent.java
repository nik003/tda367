package gruppnan.timeline.model;

import java.util.Date;

public class DeadlineEvent extends Event {

    protected Date endDate;
    protected String description, name;
    protected Course course;
    protected boolean status;

    public DeadlineEvent(Course course, String name, String description, Date endDate, boolean status){
        super(course, name, endDate, description);
        this.course = course;
        this.name = name;
        this.description = description;
        this.endDate = endDate;
        this.status = status;
    }

    public String getCourseID(){
        return course.getCourseID();
    }

    public String getName(){
        return name;
    }

    public String getDateAsString(){
        return endDate.toString() ;
    }

    public Date getDate(){
        return endDate;
    }

    public boolean isDone(){
        return status;
    }

}
