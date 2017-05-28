package gruppnan.timeline.model;


import java.util.Objects;

/**
 * @author Everyone
 * Used by: WeekCalendarView, CourseRepository, Event, SettingsFragment, DeadlineEvent, EventViewer, DefaultEvent, AddEventFragment, EventSorter, EventListener, EventInterface, SettingsView, EventRepository
 * Uses: CourseInterface
 */
public class Course implements CourseInterface{

    private String name, courseID;
    private long weeklyGoal;
    private long breakGoal;
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

    public void setWeeklyGoal(long weeklyGoal){
        this.weeklyGoal = weeklyGoal;
    }

    public long getWeeklyGoal(){
        return this.weeklyGoal;
    }

    public void setBreakGoal(long breakGoal) {
        this.breakGoal = breakGoal;
    }

    public long getBreakGoal() {
        return this.breakGoal;
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
