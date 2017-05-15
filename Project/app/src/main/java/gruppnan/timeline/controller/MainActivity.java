package gruppnan.timeline.controller;

import android.content.Intent;
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

import gruppnan.timeline.R;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    Fragment contentTimeLineFragment, calendarFragment;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        configureToolbar();
        configureNavigationDrawer();

        contentTimeLineFragment = new ContentTimelineFragment();
        calendarFragment = new CalendarFragment();

        fragmentManager = getSupportFragmentManager();
        //fragmentManager.beginTransaction().replace(R.id.frame, contentTimeLineFragment).commit();



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
    }

    protected void configureNavigationDrawer() {
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        configureToolbar();
        setupDrawer(navView);
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
                Intent intent = null;
                if (itemId == R.id.home) {
                    intent = new Intent(this, TestActivity.class);
                } else if (itemId == R.id.calendar) {
                    intent = new Intent(this, TimerActivity.class);
                }  else if (itemId == R.id.timer) {
                    intent = new Intent(this, TestActivity2.class);
                } else {

                }
                /**
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame, fragment).addToBackStack(null).commit();
                **/
                startActivity(intent);
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
