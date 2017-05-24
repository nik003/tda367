package gruppnan.timeline.model;


import java.util.Calendar;
import java.util.Date;

/**
 * Created by Melina Andersson
 * Model for Deadline events
 */
public class DeadlineEvent extends Event{


    protected boolean isDone;
    private int hour, minute;
    private String name,description;
    private Course course;
    private Date endDate;


    public DeadlineEvent(Course course, String name, String description, Date endDate, boolean isDone){
        super(course, name, endDate, description);
        this.course = course;
        this.name = name;
        this.description = description;
        this.endDate = endDate;
        this.isDone = isDone;
    }

    public DeadlineEvent(Course course, String name, Date endDate, boolean isDone){
        super(course, name, endDate);
        this.course = course;
        this.name = name;
        this.endDate = endDate;
        this.isDone = isDone;
    }

    public String getCourseID(){
        return course.getCourseID();
    }

    public int getDayofMonth(){
        return toCalendar(endDate).get(Calendar.DATE);
    }

    public int getMonth(){
        return toCalendar(endDate).get(Calendar.MONTH);
    }

    public String getMonthAsString(){
        int month = getMonth();
        switch(month){
            case 0: return "January";
            case 1: return "February";
            case 2: return "March";
            case 3: return "April";
            case 4: return "May";
            case 5: return "June";
            case 6: return "July";
            case 7: return "August";
            case 8: return "September";
            case 9: return "October";
            case 10: return "November";
            case 11: return "December";
        }
        return null;
    }

    public int getDayofYear(){
        return toCalendar(endDate).get(Calendar.DAY_OF_YEAR);

    }

    public boolean isDone(){
        return isDone;
    }

    public void setDone(boolean isDone){
        this.isDone = isDone;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setEndDate(Date endDate){
        this.endDate = new Date(endDate.getTime());
    }

    public void setHour(int hour){

        this.hour = hour;

    }

    public void setMinute(int minute){
        this.minute = minute;
    }

    public int getHour(){
        return this.hour;
    }

    public int getMinute(){
        return this.minute;
    }

    public static Calendar toCalendar(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static Date toDate(Calendar calendar){
        Date date = calendar.getTime();
        return date;
    }



}
