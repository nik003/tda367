package gruppnan.timeline.controller;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gruppnan.timeline.R;
import gruppnan.timeline.view.TimerView;

/**
 * Created by carlo on 2017-05-02.
 */

public class TimerFragment extends Fragment {

    TimerView timerView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstances) {
        return inflater.inflate(R.layout.fragment_timer, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        timerView = new TimerView(getActivity());


    }
}
