package gruppnan.timeline;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private boolean isStopwatch = true;
    long timePassed;
    long timeLeft = 20000;
    TextView timerText, stopWatchText;
    Button stopWatchRestartButton, timerRestartButton;
    ToggleButton stopWatchButton, timerButton;
    Handler timerHandler, stopWatchHandler;
    Runnable timerRunnable, stopWatchRunnable;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

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

        stopWatchHandler = new Handler();
        stopWatchRunnable = new Runnable() {

            @Override
            public void run() {

                stopWatchText.setText(getTime(timePassed, isStopwatch));

                stopWatchHandler.postDelayed(this, 1000);
            }
        };

        stopWatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!stopWatchButton.isChecked()) {
                    stopWatchHandler.removeCallbacks(stopWatchRunnable);
                    stopWatchButton.setText("Start");
                    stopWatchRestartButton.setEnabled(true);

                } else {
                    stopWatchHandler.postDelayed(stopWatchRunnable, 0);
                    stopWatchButton.setText("Pause");
                    stopWatchRestartButton.setEnabled(false);
                }
            }
        });

        stopWatchRestartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopWatchHandler.removeCallbacks(stopWatchRunnable);
                stopWatchText.setText("00:00:00");
                timePassed = 0;
                stopWatchRestartButton.setEnabled(false);
            }
        });


        timerHandler = new Handler();
        timerRunnable = new Runnable() {
            @Override
            public void run() {

                timerText.setText(getTime(timeLeft, !isStopwatch));

                timerHandler.postDelayed(this, 1000);

            }
        };

        timerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!timerButton.isChecked()) {
                    timerHandler.removeCallbacks(timerRunnable);
                    timerButton.setText("Start");
                    timerRestartButton.setEnabled(true);
                } else {
                    timerHandler.postDelayed(timerRunnable, 0);
                    timerButton.setText("Pause");
                    timerRestartButton.setEnabled(false);
                }
            }
        });


        timerRestartButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                timerHandler.removeCallbacks(timerRunnable);
                timerText.setText("00:00:00");
                timeLeft = 20000;
                timerRestartButton.setEnabled(false);
            }
        });
    }

    private String getTime(long time, boolean isStopwatch) {

        long millis = time;
        int seconds = (int) ((millis / 1000) % 60);
        int minutes = (int) ((millis / (1000 * 60)) % 60);
        int hours = (int) ((millis / (1000 * 60 * 60)) % 24);

        if(isStopwatch) {
            timePassed = time + 1000;
        } else {
            timeLeft = time - 1000;
        }

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}