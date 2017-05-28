package gruppnan.timeline.controller;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gruppnan.timeline.R;
import gruppnan.timeline.view.NoTimerStopWatchView;


/**
 * @author Carlos Yechouh
 * Fragment for when timers have yet to be initiated
 * Used by: MainActivity
 * Uses: NoTimerStopWatchView, SettingsFragment
 */
public class NoTimerStopWatchFragment extends Fragment implements View.OnClickListener {


    private NoTimerStopWatchView noTimerStopWatchView;


    public NoTimerStopWatchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        noTimerStopWatchView = new NoTimerStopWatchView(inflater, container);

        setListeners();

        return noTimerStopWatchView.getView();
    }

    private void setListeners() {
        noTimerStopWatchView.getSettingsButton().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        getFragmentManager().beginTransaction().replace(R.id.frame, new SettingsFragment()).commit();
    }
}
