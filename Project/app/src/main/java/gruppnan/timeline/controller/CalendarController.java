package gruppnan.timeline.controller;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
    private Button chooseEventButton, eventButton, deadlineButton;

    /** Will take in a view and model
    public CalendarController(EventContainer eventContainer){
        container = eventContainer;
    }
    */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_view);
        setUpView();
    }

    private void setUpView(){
        chooseEventButton = (Button) findViewById(R.id.mainCalendar_button);

        eventButton = (Button) findViewById(R.id.eventButton);
        deadlineButton = (Button) findViewById(R.id.deadlineButton);

    }

    public void createDefaultEvent(View view){
        //DefaultEvent defaultEvent = new DefaultEvent(null, )

    }
    public void chooseEvent(View view){

    }


}
