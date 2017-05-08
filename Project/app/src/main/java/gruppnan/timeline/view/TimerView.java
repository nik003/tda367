package gruppnan.timeline.view;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import gruppnan.timeline.R;

/**
 * Created by carlo on 2017-05-03.
 */

public class TimerView extends View{

    private boolean isStopwatch = true;
    long timePassed;
    long timeLeft = 20000;
    TextView timerText, stopWatchText;
    Button stopWatchRestartButton, timerRestartButton;
    ToggleButton stopWatchButton, timerButton;
    Handler timerHandler, stopWatchHandler;
    Runnable timerRunnable, stopWatchRunnable;


    public TimerView(Context context) {
        super(context);

        stopWatchText = (TextView) findViewById(R.id.stopWatchText);
        stopWatchRestartButton = (Button) findViewById(R.id.stopWatchRestartButton);
        stopWatchRestartButton.setEnabled(false);
        stopWatchButton = (ToggleButton) findViewById(R.id.stopWatchButton);
        stopWatchButton.setText("Stopwatch");
        timerText = (TextView) findViewById(R.id.timerText);
        timerRestartButton = (Button) findViewById(R.id.timerRestartButton);
        timerRestartButton.setEnabled(false);
        timerButton = (ToggleButton) findViewById(R.id.timerButton);
        timerButton.setText("Timer");
    }
}
