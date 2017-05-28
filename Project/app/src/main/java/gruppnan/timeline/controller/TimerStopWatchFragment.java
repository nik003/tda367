package gruppnan.timeline.controller;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gruppnan.timeline.R;
import gruppnan.timeline.model.CourseRepository;
import gruppnan.timeline.model.TimerStopWatchModel;
import gruppnan.timeline.view.TimerStopWatchView;

/**
 * @author Carlos Yechouh
 * Controller class responsible for timer and stopwatch functionality.
 * Used by: TimerStopWatchHolderFragment
 * Uses: TimerStopWatchView, TimerStopWatchModel, KeypadModel
 */

public class TimerStopWatchFragment extends Fragment implements View.OnClickListener {

    TimerStopWatchView timerStopWatchView;
    TimerStopWatchModel timerStopWatchModel;
    private String type, course;
    private long time;
    private Handler handler;
    private Runnable runnable;
    private boolean isStopWatch, isWeek;


    public TimerStopWatchFragment() {
    }

    public static TimerStopWatchFragment newInstance(boolean isStopWatch, String typeText, String course, boolean isWeek) {
        TimerStopWatchFragment fragment = new TimerStopWatchFragment();
        Bundle args = new Bundle();
        args.putBoolean("isStopWatch", isStopWatch);
        args.putString("type", typeText);
        args.putString("course", course);
        args.putBoolean("isWeek", isWeek);
        fragment.setArguments(args);
        return fragment;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstances) {
        timerStopWatchView = new TimerStopWatchView(inflater, container, getArguments().getBoolean("isStopWatch"));
        timerStopWatchModel = new TimerStopWatchModel();

        isStopWatch = getArguments().getBoolean("isStopWatch");
        type = getArguments().getString("type");
        course = getArguments().getString("course");
        isWeek = getArguments().getBoolean("isWeek");

        if(isWeek) {
            time = CourseRepository.getCourseRepository().getCourse(course).getWeeklyGoal();
        } else {
            time = CourseRepository.getCourseRepository().getCourse(course).getBreakGoal();
        }

        timerStopWatchModel.setStopWatch(isStopWatch);

        timerStopWatchView.getTypeText().setText(type);
        timerStopWatchView.getRestartButton().setVisibility(View.INVISIBLE);

        timerStopWatchView.getPlayPauseButton().setOnClickListener(this);
        timerStopWatchView.getRestartButton().setOnClickListener(this);


        if(timerStopWatchModel.getStopWatch()) {
            timerStopWatchView.getTimeText().setText(timerStopWatchModel.getTime(timerStopWatchModel.getTimePassed()));
        } else {
            timerStopWatchModel.setTimerTime(time);
            timerStopWatchView.getTimeText().setText(timerStopWatchModel.getTime(timerStopWatchModel.getTimeLeft()));
            timerStopWatchView.getProgressBar().setMax((int)timerStopWatchModel.getTimeLeft());
            timerStopWatchView.getEditButton().setOnClickListener(this);
        }

        handler = new Handler();
        runnable = new Runnable() {

            // Handles the stopwatch and timer counting.
            @Override
            public void run() {
                if(timerStopWatchModel.getStopWatch()) {
                    timerStopWatchView.getTimeText().setText(timerStopWatchModel.getTime(timerStopWatchModel.getTimePassed()));
                    handler.postDelayed(this, 1000);
                } else {
                    timerStopWatchView.getTimeText().setText((timerStopWatchModel.getTime(timerStopWatchModel.getTimeLeft())));
                    timerStopWatchModel.setProgress(timerStopWatchModel.getProgress() + 1000);
                    timerStopWatchView.getProgressBar().setProgress(timerStopWatchModel.getProgress());
                    if(timerStopWatchModel.getTimeLeft() >= 0) {
                        handler.postDelayed(this, 1000);
                    } else {
                        timerStopWatchView.getPlayPauseButton().setSelected(!timerStopWatchView.getPlayPauseButton().isSelected());
                        timerStopWatchModel.setTimeLeft(timerStopWatchModel.getTimerTime());
                        timerStopWatchModel.setProgress(0);
                    }
                }
            }
        };

        return timerStopWatchView.getView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // Starts/pauses the stopwatch or timer.
            case R.id.playPauseButton:
                timerStopWatchView.getPlayPauseButton().setSelected(!timerStopWatchView.getPlayPauseButton().isSelected());
                if (!timerStopWatchView.getPlayPauseButton().isSelected()) {
                    handler.removeCallbacks(runnable);
                    timerStopWatchView.getRestartButton().setVisibility(View.VISIBLE);
                    if(!timerStopWatchModel.getStopWatch()) {
                        timerStopWatchModel.setProgress(timerStopWatchView.getProgressBar().getProgress());
                    }
                } else {
                    handler.postDelayed(runnable, 1000);
                    timerStopWatchView.getRestartButton().setVisibility(View.INVISIBLE);
                }
                break;
            // Resets the stopwatch/timer to its original state.
            case R.id.restartButton:
                handler.removeCallbacks(runnable);
                if(timerStopWatchModel.getStopWatch()) {
                    timerStopWatchModel.setTimePassed(0);
                    timerStopWatchView.getTimeText().setText(timerStopWatchModel.getTime(timerStopWatchModel.getTimePassed()));
                } else {
                    timerStopWatchModel.setTimeLeft(timerStopWatchModel.getTimerTime());
                    timerStopWatchModel.setProgress(0);
                    timerStopWatchView.getProgressBar().setProgress(timerStopWatchModel.getProgress());
                    timerStopWatchView.getTimeText().setText(timerStopWatchModel.getTime(timerStopWatchModel.getTimeLeft()));
                }
                timerStopWatchView.getRestartButton().setVisibility(View.INVISIBLE);
                break;
            // Displays the Keypad fragment for user input
            case R.id.editButton:
                KeypadFragment keypadFragment = KeypadFragment.newInstance(course, isWeek);
                getFragmentManager().beginTransaction().replace(R.id.container, keypadFragment).commit();
                break;
            default:
                break;
        }

    }
}
