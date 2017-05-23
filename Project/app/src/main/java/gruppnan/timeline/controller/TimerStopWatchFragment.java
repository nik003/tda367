package gruppnan.timeline.controller;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.security.PrivateKey;

import gruppnan.timeline.R;
import gruppnan.timeline.view.TimerView;

/**
 * Created by carlo on 2017-05-02.
 */

public class TimerStopWatchFragment extends Fragment {

    TimerView timerView;
    private String type;
    private boolean isStopWatch;
    private int progress;
    private long timePassed;
    private final long timerTime = 20000;
    private long timeLeft = timerTime;
    private TextView timeText, typeText;
    private Button restartButton;
    private ToggleButton toggleButton;
    private ProgressBar progressBar;
    private Handler handler;
    private Runnable runnable;


    private int sectionNumber;

    public TimerStopWatchFragment() {
    }

    public static TimerStopWatchFragment newInstance(int sectionNumber, boolean isStopWatch, String typeText) {
        TimerStopWatchFragment fragment = new TimerStopWatchFragment();
        Bundle args = new Bundle();
        args.putInt("sectionNumber", sectionNumber);
        args.putBoolean("isStopWatch", isStopWatch);
        args.putString("type", typeText);
        fragment.setArguments(args);
        return fragment;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstances) {
        sectionNumber = getArguments().getInt("sectionNumber");
        isStopWatch = getArguments().getBoolean("isStopWatch");
        type = getArguments().getString("type");


        View view;
        if(isStopWatch) {
            view = inflater.inflate(R.layout.fragment_stopwatch, container, false);
        } else {
            view = inflater.inflate(R.layout.fragment_timer, container, false);
        }

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setMax((int)timeLeft);

        timeText = (TextView) view.findViewById(R.id.timeText);
        typeText = (TextView) view.findViewById(R.id.typeText);
        typeText.setText(type);
        restartButton = (Button) view.findViewById(R.id.restartButton);
        restartButton.setEnabled(false);
        toggleButton = (ToggleButton) view.findViewById(R.id.toggleButton);
        if(isStopWatch) {
            toggleButton.setText("Stopwatch");
            timeText.setText(getTime(timePassed));
        } else {
            toggleButton.setText("Timer");
            timeText.setText(getTime(timeLeft));
        }

        handler = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {
                if(isStopWatch) {
                    timeText.setText(getTime(timePassed));
                    handler.postDelayed(this, 1000);
                } else {
                    timeText.setText((getTime(timeLeft)));
                    progress += 1000;
                    progressBar.setProgress(progress);
                    if(timeLeft >= 0) {
                        handler.postDelayed(this, 1000);
                    }
                }
            }
        };

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!toggleButton.isChecked()) {
                    handler.removeCallbacks(runnable);
                    toggleButton.setText("Start");
                    restartButton.setEnabled(true);
                    if(!isStopWatch) {
                       progress = progressBar.getProgress();
                    }

                } else {
                    handler.postDelayed(runnable, 0);
                    toggleButton.setText("Pause");
                    restartButton.setEnabled(false);
                    if(!isStopWatch) {
                    }
                }
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(runnable);
                progressBar.clearAnimation();
                timeText.setText("00:00:00");
                if(isStopWatch) {
                    timePassed = 0;
                } else {
                    timeLeft = timerTime;
                }
                restartButton.setEnabled(false);
                if(!isStopWatch) {
                    progress = 0;
                    progressBar.setProgress(progress);
                }
            }
        });

        return view;
    }


    private String getTime(long time) {

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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("SPARAR", "onSaveInstanceState: Timern sparas");
    }
}
