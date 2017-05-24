package gruppnan.timeline.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import gruppnan.timeline.model.Course;
import gruppnan.timeline.model.CourseContainer;
import gruppnan.timeline.model.DeadlineEvent;
import gruppnan.timeline.model.DeadlineEventSet;
import gruppnan.timeline.model.EventContainer;

/**
 * Created by Melina Andersson
 * Sorts events to appear in chronological order in timeline view
 */

public class EventSorter {

    private List<DeadlineEvent> unsortedEvents = new ArrayList<>();
    private List<DeadlineEvent> courseEvents1 = new ArrayList<>();
    private List<DeadlineEvent> courseEvents2 = new ArrayList<>();
    private List<DeadlineEventSet> sortedEventSet = new ArrayList<>();

    private EventContainer eventContainer = EventContainer.getEventContainer();
    private Map<Integer,DeadlineEvent> eventMap;



    public EventSorter(){
        eventMap = eventContainer.getDeadlineEventMap();
        mapToList();
        sortEvents();
        addEventsInPairs();
    }

    public List<DeadlineEventSet> getSortedEventList(){
        return sortedEventSet;
    }

    public void mapToList(){
        for(Map.Entry<Integer, DeadlineEvent> entry : eventMap.entrySet()){
            if(!unsortedEvents.contains(entry.getValue())){
                unsortedEvents.add(entry.getValue());
            }
        }
    }


    public void sortEvents(){
        Collections.sort(unsortedEvents, new Comparator<DeadlineEvent>() {
            @Override
            public int compare(DeadlineEvent deadlineEvent, DeadlineEvent t1) {
                return deadlineEvent.getDayofYear() - t1.getDayofYear();
            }
        });
    }


    public void addEventsInPairs(){
        //separate the events by courses
        for(Course c : CourseContainer.getCourseContainer().getAllCourses()) {
            String courseID = c.getCourseID();
            for (DeadlineEvent item : unsortedEvents) {
                if (item.getCourse().getCourseID().equals(courseID)) {
                    courseEvents1.add(item);
                } else {
                    courseEvents2.add(item);
                }
            }
        }

        //set the events in pairs in order to be displayed chronologically in the view
        int i = 0;
        int j = 0;
        while(i+j < unsortedEvents.size()){
            DeadlineEvent d1 = i < courseEvents1.size() ? courseEvents1.get(i) : null;
            DeadlineEvent d2 = j <   courseEvents2.size() ?   courseEvents2.get(j) : null;
            if( d1 == null && d2 == null) {
                break;
            } else if ( d1 == null ) {
                sortedEventSet.add(new DeadlineEventSet(null,d2));
                j++;
            } else if ( d2 == null ) {
                sortedEventSet.add(new DeadlineEventSet(d1, null));
                i++;
            } else if(d1.getDayofYear() < d2.getDayofYear()) {
                sortedEventSet.add(new DeadlineEventSet(d1, null));
                i++;
            } else if(d1.getDayofYear() > d2.getDayofYear()) {
                sortedEventSet.add(new DeadlineEventSet(null,d2));
                j++;
            } else {
                sortedEventSet.add(new DeadlineEventSet(d1,d2));
                i++;
                j++;
            }

        }
    }


}
