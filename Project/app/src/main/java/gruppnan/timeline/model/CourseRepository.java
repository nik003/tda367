package gruppnan.timeline.model;

import android.util.Log;

import java.util.HashSet;

/**
 * Created by Nikolai on 2017-05-17.
 * A container for the courses
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
                Log.d("Searchcourse", courseID);
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
