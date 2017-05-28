package gruppnan.timeline.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import gruppnan.timeline.R;

/**
 * @author Carlos Yechouh
 * Sets up view with NoTimerStopWatchFragment
 * Used by: NoTimerStopWatchFragment
 */

public class NoTimerStopWatchView {

    private View view;
    private Button settingsButton;

    public NoTimerStopWatchView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_no_timer_stop_watch, container,false);

        settingsButton = (Button) view.findViewById(R.id.settingsButton);
    }

    public Button getSettingsButton() {
        return settingsButton;
    }

    public View getView() {
        return view;
    }

}
