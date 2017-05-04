package gruppnan.timeline.view;

import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by carlo on 2017-05-03.
 */

public class TimerView {

    private boolean isStopwatch = true;
    long timePassed;
    long timeLeft = 20000;
    TextView timerText, stopWatchText;
    Button stopWatchRestartButton, timerRestartButton;
    ToggleButton stopWatchButton, timerButton;
    Handler timerHandler, stopWatchHandler;
    Runnable timerRunnable, stopWatchRunnable;



}
