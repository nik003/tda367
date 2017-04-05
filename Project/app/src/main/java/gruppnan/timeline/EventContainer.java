package gruppnan.timeline;

import java.util.Date;
import java.util.TreeMap;

/**
 * Singleton Class that handles and stores the created events.
 */

public class EventContainer {


    private static EventContainer eventContainer = new EventContainer();

    private int nrOfDeadlineEvents;
    private int nrOfDefaultEvents;
    private TreeMap<String, DefaultEvent> defaultEventMap = new TreeMap<>();
    private TreeMap<String, DeadlineEvent> deadlineEventMap = new TreeMap<>();

    /**Preventing from new instantations of eventcontainer*/
    private EventContainer(){}

    /**
     * method to get the only instance of EventContainer
     * @return EventContainer object
     */
    public static EventContainer getEventContainer(){
        return eventContainer;
    }


    public void createDefaultEvent(Course course,String name, Date startDate, Date endDate, String description){

        DefaultEvent event = new DefaultEvent(course, name, startDate, endDate, description);
        defaultEventMap.put("Def" +nrOfDefaultEvents , event);
        nrOfDefaultEvents++;
    }

    public void createDeadline(Course course, String name, Date endDate, String description){

        DeadlineEvent event = new DeadlineEvent(course, name, endDate, description);
        deadlineEventMap.put("D" +nrOfDeadlineEvents , event);
        nrOfDeadlineEvents++;
    }

    public TreeMap<String, DefaultEvent> getDefaultEventMap(){
        return this.defaultEventMap;
    }

    public TreeMap<String, DeadlineEvent> getDeadlineEventMap(){
        return this.deadlineEventMap;
    }

    //method to remove event object from correct list
    public void removeEvent(){

    }

}
