package gruppnan.timeline.model;


import java.util.Calendar;
import java.util.Date;
import java.util.List;



public class DeadlineEvent extends Event{


    protected boolean isDone;


    public DeadlineEvent(Course course, String name, String description, Date endDate, boolean isDone){
        super(course, name, endDate, description);

        this.isDone = isDone;
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
