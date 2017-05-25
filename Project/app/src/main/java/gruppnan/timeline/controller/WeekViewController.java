package gruppnan.timeline.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import java.util.Calendar;
import gruppnan.timeline.R;
import gruppnan.timeline.model.Event;
import gruppnan.timeline.model.WeekDates;
import gruppnan.timeline.model.WeekEventClickData;
import gruppnan.timeline.view.WeekCalendarView;


import static gruppnan.timeline.model.DateCalc.*;

/**
 * Created by Nikolai
 * This controls the Weekview and creates the view from the model
 */
public class WeekViewController extends Fragment implements View.OnClickListener{
    private WeekCalendarView wwf;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    if(container != null){
        container.removeAllViews();
    }
        View view  = inflater.inflate(R.layout.fragment_week_view, container, false);
        TableLayout tl = (TableLayout) view.findViewById(R.id.weekView);
        wwf =new WeekCalendarView(view.getContext(),tl,this);


        wwf.createTable();
        wwf.updateView(getCurrentWeekDates());
        return view;
    }


    @Override
    public void onClick(View v) {
        TextView clickedCell;
        Button monthViewBtn;
        if(v.getId() == R.id.nxtWeek||v.getId() == R.id.prevWeek||v.getId() == R.id.cell) {
            clickedCell = (TextView) v;


            Log.d("EventDbg", "apan sover");
            if (clickedCell.getId() == R.id.nxtWeek || clickedCell.getId() == R.id.prevWeek) {

                WeekDates weekDates = getWeekDates((Calendar) clickedCell.getTag());
                wwf.updateView(weekDates);

            }
            if (clickedCell.getId() == R.id.cell) {
                clickedCell.setText(((WeekEventClickData) clickedCell.getTag()).getCellNum() + "");
                WeekEventClickData eventData = (WeekEventClickData) clickedCell.getTag();
                Event event = eventData.getEvent();
                if (event != null) {

                } else {


                }
                //TODO PUT EVENT VIEWER HERE
            }
        } else if(v.getId() == R.id.monthViewBtn) {
            Log.d("Monthview", "onClick: Occurs");

            monthViewBtn = (Button) v;
            FragmentManager fragmentManager;
            Fragment monthViewFragment = new CalendarFragment();
            FragmentTransaction ft;
            fragmentManager = getActivity().getSupportFragmentManager();
            ft = fragmentManager.beginTransaction();
           // ft.remove(fragmentManager.getFragments().get(5)).commit();
            //Log.d("Fragment",fragmentManager.getFragments().get(4).toString() );
           ft.replace(R.id.topLayout, monthViewFragment).addToBackStack(null).commit();
        }
    }

}
