package gruppnan.timeline.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Singleton Class that handles and stores the created events.
 */

public class EventContainer {


    private static EventContainer eventContainer = new EventContainer();


    private Map<Integer, Event> eventMap = new HashMap<>();
    private Map<Integer, DeadlineEvent> deadlineEventMap = new HashMap<>();
    private Map<Integer, DefaultEvent> defaultEventMap = new HashMap<>();
    private int nrOfEvents = 1;


    /**Preventing from new instantiations of eventContainer*/
    private EventContainer(){}

    /**
     * method to get the only instance of EventContainer
     * @return EventContainer object
     */
    public static EventContainer getEventContainer(){
        return eventContainer;
    }

    public DefaultEvent createDefaultEvent(Course course, String name, String desc, Date startDate, Date endDate){

        DefaultEvent de = new DefaultEvent(course,name,desc,startDate,endDate);
        addEvent(de);
        return de;
    }
    public DeadlineEvent createDeadlineEvent(Course course, String name, String desc, Date endDate, boolean isDone){
        DeadlineEvent de = new DeadlineEvent(course,name,desc,endDate,isDone);
        addEvent(de);
        return de;
    }


    public DeadlineEvent createDeadlineEvent(Course course, String name, Date endDate, boolean isDone) {
        DeadlineEvent de = new DeadlineEvent(course, name, endDate, isDone);
        addEvent(de);
        return de;
    }

    /** adds event instance to map, gives key and increments key for next entry */
    public void addEvent(Event event){
        if (eventMap.containsValue(event)){
            //TODO make this visible on view where user adds event

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
        for (Map.Entry<Integer, Event> entry : eventMap.entrySet()){
            if (entry.getValue() instanceof DeadlineEvent){
                deadlineEventMap.put(entry.getKey(),(DeadlineEvent) entry.getValue());
            }
        }
        return this.deadlineEventMap;
    }



    public ArrayList<Event> getEventsByDates(Calendar start, Calendar end){
        Calendar date = Calendar.getInstance();
        ArrayList<Event> datesEvents = new ArrayList<>();
        for (Map.Entry<Integer, Event> entry : eventMap.entrySet()){
            date.setTime(entry.getValue().getEndDate());
            Log.d("Before", String.valueOf(start.before(date)));
            Log.d("After", String.valueOf(end.after(date)));
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

}
