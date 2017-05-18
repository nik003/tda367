package gruppnan.timeline.model;

import java.util.HashSet;

/**
 * Created by Nikolai on 2017-05-17.
 */

public class CourseContainer {
    HashSet<Course> courses = new HashSet<>();
    private static CourseContainer courseContainer = new CourseContainer();
    public CourseContainer(){}
    public static CourseContainer getCourseContainer(){
        if(courseContainer == null) {
            courseContainer = new CourseContainer();
        }
        return courseContainer;
    }
    public Course createCourse(String name, String courseID){
        Course course = new Course(name,courseID);
        courses.add(course);
        return course;
    }
    public Course getCourse(String courseID){
        for(Course c : courses){
            if(c.getCourseID().equals(courseID))
                return c;
        }
        return null;
    }
    public boolean courseExist(String courseID){
        for(Course c : courses){
            if(c.getCourseID().equals(courseID))
                return true;
        }
        return false;
    }

    public HashSet<Course> getAllCourses(){
        return courses;
    }
}
