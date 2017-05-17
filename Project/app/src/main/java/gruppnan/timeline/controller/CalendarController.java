package gruppnan.timeline.controller;


import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


import android.os.Bundle;
import android.support.annotation.Nullable;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;


import gruppnan.timeline.R;
import gruppnan.timeline.model.EventContainer;

/**
 * Created by Hannes on 01/05/2017.
 */

public class CalendarController extends FragmentActivity {

    private EventContainer container;


    /** Will take in a view and model
    public CalendarController(EventContainer eventContainer){
        container = eventContainer;
    }
    */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragment();


    }

    private void initFragment(){
        setContentView(R.layout.calendar_view);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        CalendarFragment calendarFragment = new CalendarFragment();


        ft.add(android.R.id.content, calendarFragment).addToBackStack(null).commit();

    }


}
