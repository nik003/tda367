package gruppnan.timeline;

import org.junit.Before;
import org.junit.Test;

import gruppnan.timeline.model.Course;
import gruppnan.timeline.model.CourseContainer;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
/**
 * Created by Nikolai on 2017-05-26.
 */

public class CourseTest {
    Course course1;
    Course course2;
    CourseContainer cc;
    @Before
    public void beforeTests(){
        course1 = new Course("dat255","Software engineering project");
        course2 = new Course("dat067","Projekt");
        cc = CourseContainer.getCourseContainer();
    }
    @Test
    public void testAddCourse(){
        assertEquals(cc.getAllCourses().size(),0);
        cc.createCourse("dat255","Software engineering project");
        assertEquals(cc.getAllCourses().size(),1);
        cc.createCourse("dat255","Software engineering project");
        assertEquals(cc.getAllCourses().size(),1);
        cc.createCourse("dat067","Projekt");
        assertEquals(cc.getAllCourses().size(),2);
    }
}
