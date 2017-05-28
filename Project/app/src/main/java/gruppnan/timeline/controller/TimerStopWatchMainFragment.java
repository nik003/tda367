package gruppnan.timeline.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gruppnan.timeline.model.Course;
import gruppnan.timeline.model.CourseRepository;
import gruppnan.timeline.view.TimerStopWatchMainView;

/**
 * @author Carlos Yechouh
 * Controller class which holds each courses set of stopwatch and timers
 * Used by: MainActivity
 * Uses: TimerStopWatchMainView, TimerStopWatchHolderFragment
 */
public class TimerStopWatchMainFragment extends Fragment {


    private TimerStopWatchMainView timerStopWatchMainView;

    private SectionsPagerAdapter mSectionsPagerAdapter;


    public TimerStopWatchMainFragment() {
        // Required empty public constructor
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

        public int size;
        private Course[] courses;

        private void setUpCourses() {
            size = CourseRepository.getCourseRepository().getAllCourses().size();
            courses = CourseRepository.getCourseRepository().getAllCourses().toArray(new Course[size]);
        }

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
            setUpCourses();
        }
        @Override
        public Fragment getItem(int position) {
            return TimerStopWatchHolderFragment.newInstance(courses[position].getCourseID());
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return size;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return courses[position].getCourseID();
        }


    }
}
