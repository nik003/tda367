package gruppnan.timeline.model;

import java.util.Calendar;
import java.util.Date;

public class DeadlineEvent {

    protected Date endDate;
    protected String description, name;
    protected Course course;
    protected boolean status;

    public DeadlineEvent(Course course, String name, String description, Date endDate, boolean status){
        //super(course, name, endDate, description);
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

    public int getDayofMonth(){
        return toCalendar(endDate).get(Calendar.DATE);
    }

    public int getMonth(){
        return toCalendar(endDate).get(Calendar.MONTH);
    }

    public int getDayofYear(){
        return toCalendar(endDate).get(Calendar.DAY_OF_YEAR);
    }

    public boolean isDone(){
        return status;
    }

    public static Calendar toCalendar(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

}
