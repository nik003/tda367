package gruppnan.timeline;

/**
 * Created by Melina on 04/04/2017.
 */

public class Course {

    private String name, courseID;
    private Timer week, session;
    public Course(){}

    public Course(String courseID, String name){
        this.courseID = courseID;
        this.name = name;
    }

}
