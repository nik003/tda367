package gruppnan.timeline.controller;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import gruppnan.timeline.R;
import gruppnan.timeline.model.DefaultEvent;
import gruppnan.timeline.model.EventContainer;

/**
 * Created by Hannes on 01/05/2017.
 */

public class CalendarController extends Activity {

    private EventContainer container;


    /** Will take in a view and model
    public CalendarController(EventContainer eventContainer){
        container = eventContainer;
    }
    */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.calendar_view);
        Log.d("TAG", "onCreate: loggg i activity");
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        CalendarFragment calendarFragment = new CalendarFragment();

        ft.add(android.R.id.content, calendarFragment).commit();

    }



}
