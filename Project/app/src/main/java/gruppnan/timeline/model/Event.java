package gruppnan.timeline.model;

import java.util.Calendar;
import java.util.Date;


public abstract class Event implements EventInterface{

    protected Date endDate;
    protected String description, name;
    protected Course course;
    private int id;

    public Event(Course course, String name, Date endDate, String description){
        this.course = course;
        this.name = name;
        this.endDate = endDate;
        this.description = description;
    }

    public Date getEndDate (){
        return (Date) endDate.clone();
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
    public void setID(int id) {
        this.id = this.id == 0 ? id : this.id;
    }
    public int getID(){
        return this.id;
    }
    public Calendar dateToCalendar(Date date){
        Calendar cal = null;
        if(date !=null) {
           cal = Calendar.getInstance();
            cal.setTime(date);
        }
        return cal;
    }




}
