package gruppnan.timeline.controller;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import gruppnan.timeline.R;
import gruppnan.timeline.view.TimerView;

/**
 * Created by carlo on 2017-05-02.
 */

public class TimerStopWatchFragment extends Fragment {

    TimerView timerView;

    private boolean isStopWatch;
    long timePassed;
    long timeLeft = 20000;
    TextView text;
    Button restartButton;
    ToggleButton toggleButton;
    Handler handler;
    Runnable runnable;


    private int sectionNumber;

    public static TimerStopWatchFragment newInstance(int sectionNumber, boolean isStopWatch) {
        TimerStopWatchFragment fragment = new TimerStopWatchFragment();
        Bundle args = new Bundle();
        args.putInt("sectionNumber", sectionNumber);
        args.putBoolean("isStopWatch", isStopWatch);
        fragment.setArguments(args);
        return fragment;
    }


    public TimerStopWatchFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstances) {
        sectionNumber = getArguments().getInt("sectionNumber");
        isStopWatch = getArguments().getBoolean("isStopWatch");

        View view;
        if(isStopWatch) {
            view = inflater.inflate(R.layout.fragment_stopwatch, container, false);
        } else {
            view = inflater.inflate(R.layout.fragment_timer, container, false);
        }

        text = (TextView) view.findViewById(R.id.text);
        restartButton = (Button) view.findViewById(R.id.restartButton);
        restartButton.setEnabled(false);
        toggleButton = (ToggleButton) view.findViewById(R.id.toggleButton);
        if(isStopWatch) {
            toggleButton.setText("Stopwatch");
        } else {
            toggleButton.setText("Timer");
        }

        handler = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {
                if(isStopWatch) {
                    text.setText(getTime(timePassed));
                } else {
                    text.setText((getTime(timeLeft)));
                }

                handler.postDelayed(this, 1000);
            }
        };

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!toggleButton.isChecked()) {
                    handler.removeCallbacks(runnable);
                    toggleButton.setText("Start");
                    restartButton.setEnabled(true);

                } else {
                    handler.postDelayed(runnable, 0);
                    toggleButton.setText("Pause");
                    restartButton.setEnabled(false);
                }
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(runnable);
                text.setText("00:00:00");
                if(isStopWatch) {
                    timePassed = 0;
                } else {
                    timeLeft = 20000;
                }
                restartButton.setEnabled(false);
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
}
