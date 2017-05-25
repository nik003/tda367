package gruppnan.timeline.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import gruppnan.timeline.R;
import gruppnan.timeline.view.WeekCalendarView;

import static gruppnan.timeline.model.DateCalculator.getCurrentWeekDates;

/**
 * Created by Nikolai on 2017-05-25.
 */

public class ViewEventDataController extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(container != null){
            container.removeAllViews();
        }
        View view  = inflater.inflate(R.layout.eventviewer, container, false);



        return view;
    }
}
