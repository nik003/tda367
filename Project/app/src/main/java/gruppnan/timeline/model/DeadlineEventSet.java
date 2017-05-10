package gruppnan.timeline.model;

/**
 * Created by Melina on 04/05/2017.
 */

public class DeadlineEventSet {

    private DeadlineEvent d1,d2;

    public DeadlineEventSet(DeadlineEvent d1, DeadlineEvent d2){
        this.d1 = d1;
        this.d2 = d2;
    }

    public DeadlineEvent getD1(){
        return d1;
    }
    public DeadlineEvent getD2(){
        return d2;
    }

}
