package gruppnan.timeline.controller;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gruppnan.timeline.R;
import gruppnan.timeline.model.TimerStopWatchModel;
import gruppnan.timeline.view.TimerStopWatchView;

/**
 * Created by carlo on 2017-05-02.
 */

public class TimerStopWatchFragment extends Fragment {

    TimerStopWatchView timerStopWatchView;
    TimerStopWatchModel timerStopWatchModel;
    private String type;
    private Handler handler;
    private Runnable runnable;




    public TimerStopWatchFragment() {
    }

    public static TimerStopWatchFragment newInstance(int sectionNumber, boolean isStopWatch, String typeText, long time) {
        TimerStopWatchFragment fragment = new TimerStopWatchFragment();
        Bundle args = new Bundle();
        args.putInt("sectionNumber", sectionNumber);
        args.putBoolean("isStopWatch", isStopWatch);
        args.putString("type", typeText);
        args.putLong("time", time);
        fragment.setArguments(args);
        return fragment;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstances) {
        timerStopWatchView = new TimerStopWatchView(inflater, container, getArguments().getBoolean("isStopWatch"));
        timerStopWatchModel = new TimerStopWatchModel();


        timerStopWatchModel.setStopWatch(getArguments().getBoolean("isStopWatch"));
        type = getArguments().getString("type");
        timerStopWatchModel.setTimerTime(getArguments().getLong("time"));


        timerStopWatchView.getProgressBar().setMax((int)timerStopWatchModel.getTimeLeft());

        timerStopWatchView.getTypeText().setText(type);
        timerStopWatchView.getRestartButton().setVisibility(View.INVISIBLE);
        if(timerStopWatchModel.getStopWatch()) {
            timerStopWatchView.getTimeText().setText(timerStopWatchModel.getTime(timerStopWatchModel.getTimePassed()));
        } else {
            timerStopWatchView.getTimeText().setText(timerStopWatchModel.getTime(timerStopWatchModel.getTimeLeft()));
            timerStopWatchView.getEditButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    KeypadFragment keypadFragment = KeypadFragment.newInstance(type);
                    getFragmentManager().beginTransaction().replace(R.id.frame, keypadFragment, type).addToBackStack(null).commit();
                }
            });
        }

        handler = new Handler();
        runnable = new Runnable() {

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

        timerStopWatchView.getPlayPauseButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timerStopWatchView.getPlayPauseButton().setSelected(!timerStopWatchView.getPlayPauseButton().isSelected());

                if (!timerStopWatchView.getPlayPauseButton().isSelected()) {
                    handler.removeCallbacks(runnable);
                    timerStopWatchView.getRestartButton().setVisibility(View.VISIBLE);
                    if(!timerStopWatchModel.getStopWatch()) {
                        timerStopWatchModel.setProgress(timerStopWatchView.getProgressBar().getProgress());
                    }

                } else {
                    handler.postDelayed(runnable, 0);
                    timerStopWatchView.getRestartButton().setVisibility(View.INVISIBLE);
                }
            }
        });

        timerStopWatchView.getRestartButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });

        return timerStopWatchView.getView();
    }

}
