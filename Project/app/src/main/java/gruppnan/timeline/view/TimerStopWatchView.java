package gruppnan.timeline.view;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import gruppnan.timeline.R;

/**
 * Created by carlo on 2017-05-03.
 */

public class TimerStopWatchView {

    View view;
    private TextView timeText, typeText;
    private ImageButton playPauseButton, restartButton, editButton;
    private ProgressBar progressBar;


    public TimerStopWatchView(LayoutInflater inflater, ViewGroup container, Boolean isStopWatch) {
        if(isStopWatch) {
            view = inflater.inflate(R.layout.fragment_stopwatch, container, false);
        } else {
            view = inflater.inflate(R.layout.fragment_timer, container, false);
            editButton = (ImageButton) view.findViewById(R.id.editButton);

        }
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        timeText = (TextView) view.findViewById(R.id.timeText);
        typeText = (TextView) view.findViewById(R.id.typeText);
        restartButton = (ImageButton) view.findViewById(R.id.restartButton);
        playPauseButton = (ImageButton) view.findViewById(R.id.playPauseButton);

    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public ImageButton getPlayPauseButton() {
        return playPauseButton;
    }

    public ImageButton getRestartButton() {
        return restartButton;
    }

    public ImageButton getEditButton() {
        return editButton;
    }

    public TextView getTimeText() {
        return timeText;
    }

    public TextView getTypeText() {
        return typeText;
    }

    public View getView() {
        return view;
    }

}
