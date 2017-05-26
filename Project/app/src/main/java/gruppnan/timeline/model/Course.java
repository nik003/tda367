package gruppnan.timeline.model;


import java.util.Objects;

public class Course implements CourseInterface{

    private String name, courseID;
    private TimerStopWatchModel week, session;  //TODO TO BE USED?
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(name, course.name) &&
                Objects.equals(courseID, course.courseID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, courseID);
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", courseID='" + courseID + '\'' +
                '}';
    }
}
