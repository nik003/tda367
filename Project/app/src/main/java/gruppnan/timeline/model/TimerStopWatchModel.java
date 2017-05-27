package gruppnan.timeline.model;



public class TimerStopWatchModel implements TimerStopWatchInterface {

    private boolean isStopWatch;
    private int progress;
    private long timePassed;
    private long timerTime;
    private long timeLeft;


    public TimerStopWatchModel(){

    }

    public long getTimerTime() {
        return timerTime;
    }

    public void setTimerTime(long timerTime) {
        this.timerTime = timerTime;
    }

    public boolean getStopWatch() {
        return isStopWatch;
    }

    public void setStopWatch(boolean isStopWatch) {
        this.isStopWatch = isStopWatch;
    }

    public long getTimePassed() {
        return timePassed;
    }

    public long getTimeLeft() {
        return timeLeft;
    }

    public int getProgress() {
        return progress;
    }

    public void setTimePassed(long timePassed) {
        this.timePassed = timePassed;
    }

    public void setTimeLeft(long timeLeft) {
        this.timeLeft = timeLeft;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getTime(long time) {

        long millis = time;
        int seconds = (int) ((millis / 1000) % 60);
        int minutes = (int) ((millis / (1000 * 60)) % 60);
        int hours = (int) ((millis / (1000 * 60 * 60)) % 24);

        if(isStopWatch) {
            timePassed = time + 1000;
        } else {
            timeLeft = time - 1000;
        }

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
