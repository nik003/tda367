package gruppnan.timeline.model;

import android.util.Log;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Singleton Class that handles and stores the created events.
 */

public class EventContainer {


    private static EventContainer eventContainer = new EventContainer();


    private HashMap<Integer, Event> eventMap = new HashMap<>();
    private HashMap<Integer, DeadlineEvent> deadlineEventMap = new HashMap<>();
    private HashMap<Integer, DefaultEvent> defaultEventMap = new HashMap<>();
    private static int nrOfEvents = 1;

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
        return new DefaultEvent(course,name,desc,startDate,endDate);
    }

    public DeadlineEvent createDeadlineEvent(Course course, String name, String desc, Date endDate, boolean isDone){
        return new DeadlineEvent(course,name,desc,endDate,false);
    }


    /** adds event instance to map, gives key and increments key for next entry */
    public void addEvent(Event event){
        if (eventMap.containsValue(event)){
            //TODO make this visible on view where user adds event
            Log.d("duplicate event", "an identical event has already been added to map");
        }else{
            eventMap.put(nrOfEvents, event);
            event.setKey(nrOfEvents);
            nrOfEvents++;
        }
    }

    public HashMap<Integer, Event> getEventMap(){
        return this.eventMap;
    }

    /**
     * iterates through hashmap of all events and returns all instances of DefaultEvent
     * @return Hashmap of defaultEvents
     */
    public HashMap<Integer, DefaultEvent> getDefaultEventMap(){
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
    /*
    public HashMap <Integer, DeadlineEvent> getDeadlineEventMap(){
        for (Map.Entry<Integer, Event> entry : eventMap.entrySet()){
            if (entry.getValue() instanceof DeadlineEvent){
                deadlineEventMap.put(entry.getKey(),(DeadlineEvent) entry.getValue());
            }
        }
        return this.deadlineEventMap;
    }
    */



    public void removeEvent(Event event){
       eventMap.remove(event.getKey());
    }

}
