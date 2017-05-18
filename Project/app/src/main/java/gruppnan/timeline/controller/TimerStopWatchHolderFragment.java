package gruppnan.timeline.controller;


import android.os.Bundle;
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

    public TimerStopWatchHolderFragment() {
        // Required empty public constructor
    }

    private int sectionNumber;

    public static TimerStopWatchHolderFragment newInstance(int sectionNumber) {
        TimerStopWatchHolderFragment fragment = new TimerStopWatchHolderFragment();
        Bundle args = new Bundle();
        args.putInt("sectionNumber", sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer_holder, container, false);

        sectionNumber = getArguments().getInt("sectionNumber");

        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mVerticalViewPager = (VerticalViewPager) view.findViewById(R.id.container);
        mVerticalViewPager.setAdapter(mSectionsPagerAdapter);

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
                    return TimerStopWatchFragment.newInstance(0, true);
                case 1:
                    return TimerStopWatchFragment.newInstance(1, false);
                case 2:
                    return TimerStopWatchFragment.newInstance(2, false);
                 }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        /**
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Course 1";
                case 1:
                    return "Course 2";
                case 2:
                    return "Course 3";
            }
            return null;
        }
        **/
    }
}
