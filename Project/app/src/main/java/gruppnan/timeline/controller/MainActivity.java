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
import gruppnan.timeline.model.EventContainer;

/**
 * Created by Melina Andersson
 */
public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private EventContainer eventContainer = EventContainer.getEventContainer();

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
/*
        //Ligger temporärt här för att de måste ligga nånstans där de bara skapas en gång..
        CourseContainer courseContainer = CourseContainer.getCourseContainer();
        Course course1 = courseContainer.createCourse("TDA367", "Objektorienterad prog");
        Course course2 = courseContainer.createCourse("TMV027", "Matte");
        Calendar calendar = new GregorianCalendar();
        calendar.set(2017, 4, 3);
        eventContainer.createDeadlineEvent(course2, "Laboration 1", "hej", DeadlineEvent.toDate(calendar), true);
        calendar.set(2017, 4, 4);
        eventContainer.createDeadlineEvent(course2, "Laboration 2", "hej", DeadlineEvent.toDate(calendar), true);
        calendar.set(2017, 5, 7);
        eventContainer.createDeadlineEvent(course2, "Laboration 3", "hej", DeadlineEvent.toDate(calendar), false);
        calendar.set(2017, 5, 7);
        eventContainer.createDeadlineEvent(course1, "Inlämning 1", "hej", DeadlineEvent.toDate(calendar), false);
        calendar.set(2017, 5, 20);
        eventContainer.createDeadlineEvent(course1, "Seminarie", "hej", DeadlineEvent.toDate(calendar), false);
        calendar.set(2017, 5, 26);
        eventContainer.createDeadlineEvent(course1, "Inlämning 2", "hej", DeadlineEvent.toDate(calendar), false);
        calendar.set(2017, 5, 28);
        eventContainer.createDeadlineEvent(course2, "Laboration 4", "hej", DeadlineEvent.toDate(calendar), false);
        calendar.set(2017, 5, 28);
        eventContainer.createDeadlineEvent(course1, "Tenta", "hej", DeadlineEvent.toDate(calendar), false);
*/
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
        } else if (itemId == R.id.calendar) {
            fragment = new CalendarFragment();
        } else if (itemId == R.id.timer) {
            fragment = new TimerMainFragment();
        } else if (itemId == R.id.settings) {
            fragment = new SettingsFragment();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame, fragment).addToBackStack(null).commit();

        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());

        mDrawerLayout.closeDrawers();

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
