package gruppnan.timeline.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import gruppnan.timeline.R;
import gruppnan.timeline.model.Event;
import gruppnan.timeline.view.EventViewer;
import gruppnan.timeline.view.WeekCalendarView;

import static gruppnan.timeline.Utils.DateCalculator.getCurrentWeekDates;

/**
 * Created by Nikolai on 2017-05-25.
 */

public class ViewEventDataController extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        if(container != null){
            container.removeAllViews();
        }

        Event e;
        View view  = inflater.inflate(R.layout.eventviewer, container, false);
      //  EventViewer ev = new EventViewer(inflater,this,view.getContext(),e);
       // ev.renderView();


        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
