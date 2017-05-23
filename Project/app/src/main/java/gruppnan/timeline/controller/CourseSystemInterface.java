package gruppnan.timeline.controller;

import java.util.Date;
import java.util.List;

/**
 * Created by Nikolai on 2017-05-18.
 * Interface for subsystems that handles events
 */

public interface CourseSystemInterface {
    void getAddEvents(String courseName, Date from, Date to);
    List<String> searchCourses(String searchText);


}
