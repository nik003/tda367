package gruppnan.timeline.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import gruppnan.timeline.R;
import gruppnan.timeline.model.Course;
import gruppnan.timeline.model.Event;
import gruppnan.timeline.model.EventContainer;
import gruppnan.timeline.model.WeekDates;
import gruppnan.timeline.model.WeekEventClickData;
import gruppnan.timeline.model.dateCalc;
import gruppnan.timeline.view.WeekCalendarView;

import static gruppnan.timeline.model.dateCalc.getCurrentWeekDates;
import static gruppnan.timeline.model.dateCalc.getWeekDates;


public class WeekViewController extends Fragment implements View.OnClickListener{
    private WeekCalendarView wwf;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view  = inflater.inflate(R.layout.fragment_week_view, container, false);
        TableLayout tl = (TableLayout) view.findViewById(R.id.weekView);
        wwf =new WeekCalendarView(view.getContext(),tl,this);

        WeekDates dates = dateCalc.getCurrentWeekDates();
        EventContainer ec = EventContainer.getEventContainer();
        ec.createDeadlineEvent(new Course("stuff","stuf"),"Sak","denna saken", new Date(),false);
        wwf.createTable();
        wwf.updateView(getCurrentWeekDates());
        return view;
    }


    @Override
    public void onClick(View v) {
        TextView clickedCell = (TextView) v;
       Log.d("EventDbg","apan sover");
        if(clickedCell.getId() == R.id.nxtWeek|| clickedCell.getId() == R.id.prevWeek) {
            Log.d("EventDbg",((WeekEventClickData)clickedCell.getTag()).getCellNum()+"");
            WeekDates weekDates = getWeekDates((Calendar)clickedCell.getTag());
            wwf.updateView(weekDates);

        }
        if(clickedCell.getId() == R.id.cell){
            clickedCell.setText(((WeekEventClickData)clickedCell.getTag()).getCellNum() + "");
            WeekEventClickData eventData = (WeekEventClickData)clickedCell.getTag();
            Event event =eventData.getEvent();
            if(event!=null){

            }else{


            }
            //TODO PUT EVENT VIEWER HERE
        }

    }

}
