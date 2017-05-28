package gruppnan.timeline.MainSystem;

import org.junit.Before;
import org.junit.Test;

import gruppnan.timeline.model.Course;
import gruppnan.timeline.model.CourseRepository;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
/**
 * Created by Nikolai on 2017-05-26.
 */

public class CourseTest {
    Course course1;
    Course course2;
    CourseRepository cc;
    @Before
    public void beforeTests(){
        course1 = new Course("dat255","Software engineering project");
        course2 = new Course("dat067","Projekt");
        cc = CourseRepository.getCourseRepository();
        for(Course e : cc.getAllCourses()){
            cc.removeCourse(e.getCourseID());
        }



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
        System.out.println(cc.getAllCourses());

        assertEquals(cc.getCourse("dat067").getName(),"Projekt");
        assertEquals(cc.getCourse("dat021"),null);

    }
    @Test
    public void testRemoveCourse(){
        cc.removeCourse("dat222");
        assertEquals(cc.getAllCourses().size(),0);
        cc.createCourse("dat255","Software engineering project");
        assertEquals(cc.getAllCourses().size(),1);
        cc.removeCourse("dat222");
        assertEquals(cc.getAllCourses().size(),1);
        cc.removeCourse("dat255");
        assertEquals(cc.getAllCourses().size(),0);

    }
    @Test
    public void testCourseExist(){
        assertFalse(cc.courseExist("dat255"));
        cc.createCourse("dat255","Software engineering project");
        assertTrue(cc.courseExist("dat255"));

    }
}
