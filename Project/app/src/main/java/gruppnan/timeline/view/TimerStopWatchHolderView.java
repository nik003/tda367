package gruppnan.timeline.view;

import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gruppnan.timeline.R;
import gruppnan.timeline.Utils.VerticalViewPager;

/**
 * Created by carlo on 2017-05-26.
 */

public class TimerStopWatchHolderView {

    View view;
    private VerticalViewPager mVerticalViewPager;
    private TabLayout tabLayout;


    public TimerStopWatchHolderView(LayoutInflater inflater, ViewGroup container){
        view = inflater.inflate(R.layout.fragment_timer_holder, container, false);

        mVerticalViewPager = (VerticalViewPager) view.findViewById(R.id.vertical_container);
        tabLayout = (TabLayout) view.findViewById(R.id.tabDots);

    }

    public VerticalViewPager getmVerticalViewPager() {
        return mVerticalViewPager;
    }

    public TabLayout getTabLayout() {
        return tabLayout;
    }

    public View getView() {
        return view;
    }
}
