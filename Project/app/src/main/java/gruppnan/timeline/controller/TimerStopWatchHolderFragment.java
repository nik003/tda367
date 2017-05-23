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

/**
 * A simple {@link Fragment} subclass.
 */
public class TimerStopWatchHolderFragment extends Fragment {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private VerticalViewPager mVerticalViewPager;

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
        View view = inflater.inflate(R.layout.fragment_timer_holder, container, false);

        sectionNumber = getArguments().getInt("sectionNumber");
        course = getArguments().getString("course");

        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mVerticalViewPager = (VerticalViewPager) view.findViewById(R.id.vertical_container);
        mVerticalViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(mVerticalViewPager, true);



        return view;
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return TimerStopWatchFragment.newInstance(sectionNumber, true, "Today");
                case 1:
                    return TimerStopWatchFragment.newInstance(sectionNumber+1, false, "Week");
                case 2:
                    return TimerStopWatchFragment.newInstance(sectionNumber+2, false, "Break");
                 }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

    }
}
