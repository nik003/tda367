package gruppnan.timeline;



public class Course {

    private String name, courseID;
    private Timer week, session;
    public Course(){}

    public Course(String courseID, String name){
        this.courseID = courseID;
        this.name = name;
    }

}
