package gruppnan.timeline.view;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gruppnan.timeline.R;

/**
 * @author Carlos Yechouh
 * Sets up view with TimerStopWatchMainFragment
 * Used by: TimerStopWatchMainFragment
 */

public class TimerStopWatchMainView {

    private View view;

    // The ViewPager that will host the section contents.
    private ViewPager mViewPager;
    private TabLayout tabLayout;


    public TimerStopWatchMainView(LayoutInflater inflater, ViewGroup container) {

        view = inflater.inflate(R.layout.fragment_timer_main, container, false);

        mViewPager = (ViewPager) view.findViewById(R.id.container);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
    }

    public ViewPager getmViewPager() {
        return mViewPager;
    }

    public TabLayout getTabLayout() {
        return tabLayout;
    }

    public View getView() {
        return view;
    }
}
