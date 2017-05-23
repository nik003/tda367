package gruppnan.timeline.model;



public class Course implements CourseInterface{

    private String name, courseID;
    private Timer week, session;  //TODO TO BE USED?
    private int weeklyGoal;
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

    public void setWeeklyGoal(int weeklyGoal){
        this.weeklyGoal = weeklyGoal;
    }

    public int getWeeklyGoal(){
        return this.weeklyGoal;
    }


}
