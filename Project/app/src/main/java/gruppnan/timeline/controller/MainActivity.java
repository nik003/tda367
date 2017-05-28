package gruppnan.timeline.controller;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import gruppnan.timeline.R;
import gruppnan.timeline.model.CourseRepository;

/**
 * @author Melina Andersson
 *
 * Used by: None
 * Uses: ContentTimelineFragment, CalendarFragment, TimerStopWatchMainFragment, SettingsFragment,EventRepository
 */
public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame, new ContentTimelineFragment()).commit();

        //Init drawer and toolbar
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        initToolbar();
        initDrawer(navView);


    }

    /**
     * Initializes the toolbar
     */
    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
    }


    /**
     * Initializes the drawer
     *
     * @param navigationView the navigation view to be used
     */
    private void initDrawer(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    /**
     * Start view depending on which menu item that is selected
     */
    protected void selectDrawerItem(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        Fragment fragment = null;

        if (itemId == R.id.home) {
            fragment = new ContentTimelineFragment();
            tag = "contentTimeLine";
        } else if (itemId == R.id.calendar) {
            fragment = new CalendarFragment();
            tag = "calendar";
        } else if (itemId == R.id.timer) {
            if(CourseRepository.getCourseRepository().getAllCourses().isEmpty()) {
                fragment = new NoTimerStopWatchFragment();
            } else {
                fragment = new TimerStopWatchMainFragment();
                tag = "timerStopWatchMain";
            }
        } else if (itemId == R.id.settings) {
            fragment = new SettingsFragment();
            tag = "settings";
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame, fragment, tag).addToBackStack(null).commit();

        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDrawerLayout.closeDrawer(GravityCompat.START);

    }


    /**
     * Handles selected toolbar item
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return true;
    }

    /**
     * Handles back pressed
     */
    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}
