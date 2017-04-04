package gruppnan.timeline;

import java.util.Date;
import java.util.TreeMap;

/**
 * Class that handles and contains the created events.
 */

public class EventContainer {


    private int nrOfDeadlineEvents;
    private int nrOfDefaultEvents;
    private TreeMap<String, DefaultEvent> defaultEventMap = new TreeMap<>();
    private TreeMap<String, DeadlineEvent> deadlineEventMap = new TreeMap<>();



    public void createDefaultEvent(Course course,String name, Date startDate, Date endDate, String description){

        DefaultEvent event = new DefaultEvent(course, name, startDate, endDate, description);
        defaultEventMap.put("D" +nrOfDefaultEvents , event);
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
