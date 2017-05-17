package gruppnan.timeline.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import gruppnan.timeline.TimeEditSystem.TimeEditEvent;
import gruppnan.timeline.TimeEditSystem.TimeEditFetcher;
import gruppnan.timeline.model.CourseContainer;
import gruppnan.timeline.model.EventContainer;

/**
 * Created by Nikolai on 2017-05-17.
 */

public abstract class TimeEditHandler {
    static TimeEditFetcher  tf = new TimeEditFetcher();
    static EventContainer  ec= EventContainer.getEventContainer();
    static CourseContainer cc  = CourseContainer.getCourseContainer();
    public static void addTimeEditEvents(String courseName, Date from, Date to){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        List<TimeEditEvent> events = tf.getIcs(courseName,sdf.format(from),sdf.format(to));
        for(TimeEditEvent ev : events){
            String courseID = ev.getName().substring(0,5);
            String name = ev.getName().substring(5,ev.getName().length());
            String courseDesc = ev.getDescription() + "\n" + ev.getSummary() + "\n" + ev.getLocation();
            if(!cc.courseExist(courseID)){
                cc.createCourse(name,courseID);

            }
            ec.createDefaultEvent(cc.getCourse(courseID),name,courseDesc,ev.getStartDate(),ev.getEndDate());
        }
    }
    public static List<String>searchCourses(String searchText){
        return tf.searchCourse(searchText);

    }
}
