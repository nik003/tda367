package gruppnan.timeline.model;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Everyone
 * Abstract model class for a Event
 *
 * Used by: CardTimelineFragment, WeekCalendarView, ItemListAdapter, DefaultEvent, EventAdapter, WeekViewController, EventAdapterView, DeadlineEvent, CalendarFragment, EventSorter, CardListener, EventRepository, EventViewer, WeekEventClickData
 * Uses: EventInterface
 *
 */
public abstract class Event implements EventInterface{

    protected Date endDate;
    protected String description, name;
    protected Course course;
    private int id;

    public Event(Course course, String name, Date endDate, String description){
        this.course = course;
        this.name = name;
        this.endDate = (Date) endDate.clone();
        this.description = description;
    }

    public Event(Course course, String name, Date endDate){
        this.course = course;
        this.name = name;
        this.endDate = (Date) endDate.clone();
    }


    public Date getEndDate (){
        return (Date) endDate.clone();
    }


    public Date getStartDate(){
        return null;
    }
    public String getDescription(){
        return this.description;
    }
    public String getName(){
        return this.name;
    }
    public void setEndDate(Date endDate){
        this.endDate = (Date) endDate.clone();
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

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }


}
