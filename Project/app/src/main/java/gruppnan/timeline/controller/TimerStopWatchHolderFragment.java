package gruppnan.timeline.controller;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gruppnan.timeline.R;
import gruppnan.timeline.model.TimerStopWatchHolderView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimerStopWatchHolderFragment extends Fragment {

    private TimerStopWatchHolderView timerStopWatchHolderView;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private int sectionNumber;
    private String course;

    public TimerStopWatchHolderFragment() {
        // Required empty public constructor
    }

    public static TimerStopWatchHolderFragment newInstance(int sectionNumber, String course) {
        TimerStopWatchHolderFragment fragment = new TimerStopWatchHolderFragment();
        Bundle args = new Bundle();
        args.putInt("sectionNumber", sectionNumber);
        args.putString("course", course);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        timerStopWatchHolderView = new TimerStopWatchHolderView(inflater, container);

        sectionNumber = getArguments().getInt("sectionNumber");
        course = getArguments().getString("course");

        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());
        // Set up the ViewPager with the sections adapter.
        timerStopWatchHolderView.getmVerticalViewPager().setAdapter(mSectionsPagerAdapter);
        timerStopWatchHolderView.getTabLayout().setupWithViewPager(timerStopWatchHolderView.getmVerticalViewPager(), true);

        return timerStopWatchHolderView.getView();
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return TimerStopWatchFragment.newInstance(sectionNumber, true, "Session", 0);
                case 1:
                    return TimerStopWatchFragment.newInstance(sectionNumber+1, false, "Week", 0);
                case 2:
                    return TimerStopWatchFragment.newInstance(sectionNumber+2, false, "Break", 0);
                 }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

    }
}
