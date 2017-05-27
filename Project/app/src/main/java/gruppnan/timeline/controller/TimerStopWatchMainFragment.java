package gruppnan.timeline.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gruppnan.timeline.view.TimerStopWatchMainView;

public class TimerStopWatchMainFragment extends Fragment {


    private TimerStopWatchMainView timerStopWatchMainView;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;




    public TimerStopWatchMainFragment() {

    }

    public static TimerStopWatchMainFragment newInstance(int sectionNumber) {
        TimerStopWatchMainFragment fragment = new TimerStopWatchMainFragment();
        Bundle args = new Bundle();
        args.putInt("sectionNumber", sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        timerStopWatchMainView = new TimerStopWatchMainView(inflater, container);



        mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
        // Set up the ViewPager with the sections adapter.
        timerStopWatchMainView.getmViewPager().setAdapter(mSectionsPagerAdapter);

        timerStopWatchMainView.getTabLayout().setupWithViewPager(timerStopWatchMainView.getmViewPager());

        return timerStopWatchMainView.getView();
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public static class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return TimerStopWatchHolderFragment.newInstance(0, "course1");
                case 1:
                    return TimerStopWatchHolderFragment.newInstance(3, "course2");
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Course 1";
                case 1:
                    return "Course 2";
            }
            return null;
        }


    }
}
