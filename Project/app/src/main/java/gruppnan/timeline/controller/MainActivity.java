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

import gruppnan.timeline.R;
import gruppnan.timeline.week_view;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

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
                Class fragmentClass = null;

                if (itemId == R.id.home) {
                    fragmentClass = ContentTimelineFragment.class;
                } else if (itemId == R.id.calendar) {
                    fragmentClass = week_view.class;
                }  else if (itemId == R.id.timer) {
                    fragmentClass = ContentTimelineFragment.class;
                }

                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e){
                    e.printStackTrace();
                }

                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();

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
        } else {
            super.onBackPressed();
        }
    }

}
