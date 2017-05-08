package gruppnan.timeline.controller;

import android.app.Activity;
import android.os.Bundle;

import gruppnan.timeline.view.TimerView;

/**
 * Created by carlo on 2017-05-04.
 */

public class TimerActivity extends Activity {
    TimerView timerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        timerView = new TimerView(this);

        setContentView(timerView);




    }
}
