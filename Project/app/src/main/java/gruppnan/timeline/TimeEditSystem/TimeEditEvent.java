package gruppnan.timeline.TimeEditSystem;

import java.util.Date;

/**
 * Created by Nikolai on 2017-05-17.
 * The representation of an event in TimeEdit
 */

public class TimeEditEvent {
    private Date startDate;
    private Date endDate;
    private String description;
    private String location;
    private String summary;
    private String name;

    public TimeEditEvent(Date startDate, Date endDate, String summary, String location,String description , String name) {
        this.startDate = new Date(startDate.getTime());
        this.endDate = new Date(endDate.getTime());
        this.description = description;
        this.location = location;
        this.summary = summary;
        this.name = name;
    }

    public Date getStartDate() {
        return new Date(startDate.getTime());
    }

    public Date getEndDate() {
        return new Date(endDate.getTime());
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getSummary() {
        return summary;
    }

    public String getName() {
        return name;
    }
}
