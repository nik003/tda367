package gruppnan.timeline.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Hannes
 * Revised by Nikolai
 * Singleton Class that handles and stores the different types of created events. Uses facade
 * pattern to simplify creation of said events.
 * Used By:EventSorter,CalendarFragment,TimeEditHandler,AddEventFragment,EventListener,CardTimelineFragment,WeekCalendarView,MainActivity
 * Uses:Event,DeadlineEvent,Course,DefaultEvent
 */

public class EventRepository {


    private static EventRepository eventRepository = new EventRepository();


    private Map<Integer, Event> eventMap = new HashMap<>();
    private Map<Integer, DeadlineEvent> deadlineEventMap = new HashMap<>();
    private Map<Integer, DefaultEvent> defaultEventMap = new HashMap<>();
    private int nrOfEvents = 1;


    /**Preventing from new instantiations of eventContainer*/
    private EventRepository(){}

    /**
     * method to get the only instance of EventContainer
     * @return EventContainer object
     */
    public static EventRepository getEventRepository(){
        return eventRepository;
    }

    public DefaultEvent createDefaultEvent(Course course, String name, String desc, Date startDate, Date endDate){

        DefaultEvent defaultEvent = new DefaultEvent(course,name,desc,startDate,endDate);
        addEvent(defaultEvent);
        return defaultEvent;
    }
    public DeadlineEvent createDeadlineEvent(Course course, String name, String desc, Date endDate, boolean isDone){
        DeadlineEvent deadlineEvent = new DeadlineEvent(course,name,desc,endDate,isDone);
        addEvent(deadlineEvent);
        return deadlineEvent;
    }


    /** adds event instance to map, gives key and increments key for next entry */
    private void addEvent(Event event){
        if (eventMap.containsValue(event)){


        }else{
            eventMap.put(nrOfEvents, event);
            event.setID(nrOfEvents);
            nrOfEvents++;
        }
    }

    public Map<Integer, Event> getEventMap(){
        return this.eventMap;
    }

    /**
     * iterates through hashmap of all events and returns all instances of DefaultEvent
     * @return Hashmap of defaultEvents
     */
    public Map<Integer, DefaultEvent> getDefaultEventMap(){

        defaultEventMap.clear();
        for (Map.Entry<Integer, Event> entry : eventMap.entrySet()){
            if (entry.getValue() instanceof DefaultEvent){
                defaultEventMap.put(entry.getKey(),(DefaultEvent) entry.getValue());
            }
        }
        return this.defaultEventMap;
    }

    /**
     * iterates through main map of all events and returns all instances of DeadlineEvent
     * @return Hashmap of deadlineEvents
     */

    public Map <Integer, DeadlineEvent> getDeadlineEventMap(){

        deadlineEventMap.clear();
        for (Map.Entry<Integer, Event> entry : eventMap.entrySet()){
            if (entry.getValue() instanceof DeadlineEvent){
                deadlineEventMap.put(entry.getKey(),(DeadlineEvent) entry.getValue());
            }
        }
        return this.deadlineEventMap;
    }


    /** returns array of events within specified time selection */
    public ArrayList<Event> getEventsByDates(Calendar start, Calendar end){
        Calendar date = Calendar.getInstance();
        ArrayList<Event> datesEvents = new ArrayList<>();
        for (Map.Entry<Integer, Event> entry : eventMap.entrySet()){
            date.setTime(entry.getValue().getEndDate());
            if(start.before(date) && end.after(date)){
                datesEvents.add(entry.getValue());

            }else if((entry.getValue() instanceof DefaultEvent)  ){
                date.setTime(((DefaultEvent) entry.getValue()).getStartDate());
                if(start.before(date) && end.after(date)){
                    datesEvents.add(entry.getValue());
                }
            }
        }
    return datesEvents;
    }

    public void removeEvent(int eventID){
       eventMap.remove(eventID);
    }
    public void removeAll(){
        eventMap.clear();
    }
}
