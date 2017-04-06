package gruppnan.timeline;



public class Timer implements TimerInterface{

    private int limit;
    private boolean countsUp;


    public Timer(int limit, boolean upOrDown){
        this.limit = limit;
        this.countsUp = upOrDown;
    }

    public int getLimit(){
        return this.limit;
    }

    public boolean isUp(){
        return countsUp;
    }
}
