package gruppnan.timeline.model;

import java.util.Calendar;
import java.util.Date;


public abstract class Event implements EventInterface{

    protected Date endDate;
    protected String description, name;
    protected Course course;
    private int key;

    public Event(Course course, String name, Date endDate, String description){
        this.course = course;
        this.name = name;
        this.endDate = new Date(endDate.getTime());
        this.description = description;
    }

    public Date getEndDate (){
        return new Date(this.endDate.getTime());
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
    public void setKey(int key) {
        this.key = this.key == 0 ? key : this.key;
    }
    public int getKey(){
        return this.key;
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
