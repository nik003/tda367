package gruppnan.timeline.controller;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import gruppnan.timeline.TimeEditSystem.TimeEditEvent;
import gruppnan.timeline.TimeEditSystem.TimeEditFetcher;
import gruppnan.timeline.model.CourseRepository;
import gruppnan.timeline.model.EventRepository;

/**
 * Created by Nikolai on 2017-05-17.
 * A subsystem handler for TimeEdit
 * Used by: DialogOnclickListener, SettingsFragment
 * Uses:CourseRepository,TimeEditEvent,TimeEditFetcher,CourseSystemInterface,EventRepository
 */

public class TimeEditHandler implements CourseSystemInterface {
    static TimeEditFetcher  tf = new TimeEditFetcher();
    static EventRepository ec= EventRepository.getEventRepository();
    static CourseRepository cc  = CourseRepository.getCourseRepository();

    /**
     *
     * @param courseName Name of the course to be added
     * @param from        Date that indicates from when to fetch events
     * @param to          Date that indicates to when to fetch events
     */
    public void  getAddEvents(String courseName, Date from, Date to) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        List<TimeEditEvent> events = tf.getIcs(courseName, sdf.format(from), sdf.format(to));
        if(courseName != null)

        if (events != null) {
            for (TimeEditEvent ev : events) {
                String courseID = ev.getName().substring(0, 6);
                courseName = ev.getName().substring(7,ev.getName().length());
                String name = ev.getDescription();
                String courseDesc = ev.getName() + "\n" + ev.getSummary() + "\n" + ev.getLocation();

                if (!cc.courseExist(courseID)) {
                    cc.createCourse(courseID,courseName);

                }
                ec.createDefaultEvent(cc.getCourse(courseID), name, courseDesc, ev.getStartDate(), ev.getEndDate());
            }
        }
    }

    /**
     *
     * @param searchText The course to be searched from
     * @return The list of courses matching the searchText
     */
    public  List<String>searchCourses(String searchText){
        return tf.searchCourse(searchText);

    }
}
