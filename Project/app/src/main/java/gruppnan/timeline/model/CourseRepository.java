package gruppnan.timeline.model;

import java.util.HashSet;

/**
 * @author Nikolai
 * A container for the courses
 * Used By: ContentTimelineView, SettingsView, AddEventFragment,TimeEditHandler,EventSorter,ContentTimelineFragment,SettingsFragment
 * Uses: Course
 */

public class CourseRepository {
    HashSet<Course> courses = new HashSet<>();
    private static CourseRepository courseRepository = new CourseRepository();
    public CourseRepository(){}
    public static CourseRepository getCourseRepository(){
        if(courseRepository == null) {
            courseRepository = new CourseRepository();
        }
        return courseRepository;
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
    public void removeCourse(String courseID){
        for(Course c : courses){
            if(c.getCourseID().equals(courseID))
                courses.remove(c);
        }

    }

    public HashSet<Course> getAllCourses(){
        return courses;
    }
}
