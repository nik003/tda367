package gruppnan.timeline;

import java.util.Date;

/**
 * Created by Melina on 04/04/2017.
 */

public class Deadline extends Event {

    protected Date endDate;
    protected String description, name;
    protected Course course;

    public Deadline(Course course, String name, Date endDate, String description){
        super(course, name, endDate, description);
    }
}
