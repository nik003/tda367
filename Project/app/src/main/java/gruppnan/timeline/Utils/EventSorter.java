package gruppnan.timeline.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import gruppnan.timeline.model.Course;
import gruppnan.timeline.model.CourseRepository;
import gruppnan.timeline.model.DeadlineEvent;
import gruppnan.timeline.model.DeadlineEventSet;
import gruppnan.timeline.model.EventRepository;

/**
 * @author Melina Andersson
 * Sorts events to appear in chronological order in timeline view
 *
 * Used by: ContentTimelineFragment
 * Uses: DeadlineEvent, DeadlineEventSet, EventRepository, CourseRepository,Event,Course
 */

public class EventSorter {

    private List<DeadlineEvent> unsortedEvents = new ArrayList<>();
    private List<DeadlineEvent> courseEvents1 = new ArrayList<>();
    private List<DeadlineEvent> courseEvents2 = new ArrayList<>();
    private List<DeadlineEventSet> sortedEventSet = new ArrayList<>();

    private EventRepository eventRepository = EventRepository.getEventRepository();
    private Map<Integer,DeadlineEvent> eventMap;



    public EventSorter(){
        eventMap = eventRepository.getDeadlineEventMap();
        mapToList();
        sortEvents();
        separateEvents();
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

    public void separateEvents(){
        //separate the events by courses
        Course c1 = null;
        Course c2 = null;
        for(Course c : CourseRepository.getCourseRepository().getAllCourses()){
            if(c1 == null){
                c1 = c;
            }else if(c2==null) {
                c2 = c;
            }else{break;}
        }
        for (DeadlineEvent item : unsortedEvents) {
            if(item.getCourse() != null){
                if (c1!=null && item.getCourse().getCourseID().equals(c1.getCourseID())) {
                    courseEvents1.add(item);
                } else if (c2!=null && item.getCourse().getCourseID().equals(c2.getCourseID()))  {
                    courseEvents2.add(item);
                }
            }
        }
    }


    public void addEventsInPairs(){

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
