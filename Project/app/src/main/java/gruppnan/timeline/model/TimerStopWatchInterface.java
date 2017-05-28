package gruppnan.timeline.model;

/**
 * Created by Melina on 06/04/2017.
 * Used by: TimerStopWatchModel
 */

public interface TimerStopWatchInterface {

    long getTimerTime();

    void setTimerTime(long timerTime);

    boolean getStopWatch();

    void setStopWatch(boolean isStopWatch);

    long getTimePassed();

    long getTimeLeft();

    int getProgress();

    void setTimePassed(long timePassed);

    void setTimeLeft(long timeLeft);

    void setProgress(int progress);

    String getTime(long time);
}
