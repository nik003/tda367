package gruppnan.timeline.controller;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Calendar;
import java.util.GregorianCalendar;

import gruppnan.timeline.R;
import gruppnan.timeline.model.Course;
import gruppnan.timeline.model.DeadlineEvent;
import gruppnan.timeline.model.EventContainer;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    private EventContainer eventContainer = EventContainer.getEventContainer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame, new ContentTimelineFragment()).commit();

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        configureToolbar();
        setupDrawer(navView);

        //Ligger temporärt här för att de måste ligga nånstans där de bara skapas en gång..
        Calendar calendar = new GregorianCalendar();
        calendar.set(2017, 4, 3);
        eventContainer.createDeadlineEvent(new Course("course1", "eiei"), "Laboration 1", "hej", DeadlineEvent.toDate(calendar), true);
        calendar.set(2017, 4, 4);
        eventContainer.createDeadlineEvent(new Course("course1", "ei"), "Laboration 2", "hej", DeadlineEvent.toDate(calendar), true);
        calendar.set(2017, 5, 7);
        eventContainer.createDeadlineEvent(new Course("course1", "eiei"), "Laboration 3", "hej", DeadlineEvent.toDate(calendar), false);
        calendar.set(2017, 5, 7);
        eventContainer.createDeadlineEvent(new Course("course2", "ei"), "Inlämning 1", "hej", DeadlineEvent.toDate(calendar), false);
        calendar.set(2017, 5, 20);
        eventContainer.createDeadlineEvent(new Course("course2", "eiei"), "Seminarie", "hej", DeadlineEvent.toDate(calendar), false);
        calendar.set(2017, 5, 26);
        eventContainer.createDeadlineEvent(new Course("course2", "ei"), "Inlämning 2", "hej", DeadlineEvent.toDate(calendar), false);
        calendar.set(2017, 5, 28);
        eventContainer.createDeadlineEvent(new Course("course1", "eiei"), "Laboration 4", "hej", DeadlineEvent.toDate(calendar), false);
        calendar.set(2017, 5, 28);
        eventContainer.createDeadlineEvent(new Course("course2", "ei"), "Tenta", "hej", DeadlineEvent.toDate(calendar), false);




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_items, menu);
        return true;
    }

    protected void configureToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
    }

    private void setupDrawer(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }



    protected void selectDrawerItem(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        Fragment fragment = null;

                if (itemId == R.id.home) {
                    fragment = new ContentTimelineFragment();
                } else if (itemId == R.id.calendar) {
                    fragment = new CalendarFragment();
                }  else if (itemId == R.id.timer) {
                    fragment = new TimerMainFragment();
                } else {

        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame, fragment).addToBackStack(null).commit();

        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());

        mDrawerLayout.closeDrawers();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        switch(itemId) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }


        return true;

    }

    @Override
    public void onBackPressed(){
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

}
