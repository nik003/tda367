package gruppnan.timeline.model;

/**
 * @author Melina Andersson
 * Class for creating events in sets in order to be displayed beside each other in a recyclerview
 *
 * Used by: ItemListAdapter, EventSorter
 */
public class DeadlineEventSet {

    private DeadlineEvent d1,d2;

    public DeadlineEventSet(DeadlineEvent d1, DeadlineEvent d2){
        this.d1 = d1;
        this.d2 = d2;
    }

    public DeadlineEvent getD1(){
        return this.d1;
    }
    public DeadlineEvent getD2(){
        return this.d2;
    }

}
