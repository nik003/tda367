package gruppnan.timeline;



public class Course implements CourseInterface{

    private String name, courseID;
    private Timer week, session;
    public Course(){}

    public Course(String courseID, String name){
        this.courseID = courseID;
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public String getCourseID(){
        return this.courseID;
    }


}
