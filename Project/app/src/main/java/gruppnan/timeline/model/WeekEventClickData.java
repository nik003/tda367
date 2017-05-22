package gruppnan.timeline.model;

/**
 * Created by Nikolai on 2017-05-15.
 * The data each cells in the weekview holds as a tag.
 */

public class WeekEventClickData {
    private Event cellEvent;
    private int cellNum;

    public WeekEventClickData(Event cellEvent, int cellNum) {
        this.cellEvent = cellEvent;
        this.cellNum = cellNum;
    }

    public Event getEvent() {
        return cellEvent;
    }

    public void setEvent(Event cellEvent) {
        this.cellEvent = cellEvent;
    }

    public int getCellNum() {
        return cellNum;
    }

    public void setCellNum(int cellNum) {
        this.cellNum = cellNum;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof WeekEventClickData){
            WeekEventClickData cellData = (WeekEventClickData) obj;
            if(cellData.getCellNum() == getCellNum())
                return true;
        }
        return false;
    }
    public int hashCode() {
        assert false : "hashCode not designed";
        return 42; // any arbitrary constant will do
    }
}
