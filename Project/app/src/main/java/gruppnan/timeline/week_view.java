package gruppnan.timeline;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import gruppnan.timeline.model.Course;
import gruppnan.timeline.model.DeadlineEvent;
import gruppnan.timeline.model.DefaultEvent;
import gruppnan.timeline.model.Event;
import gruppnan.timeline.model.EventContainer;
import gruppnan.timeline.model.dateCalc;
import gruppnan.timeline.view.week_view_fragment;

import static gruppnan.timeline.model.dateCalc.*;


public class week_view extends Fragment implements View.OnClickListener{
    private week_view_fragment wwf;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view  = inflater.inflate(R.layout.fragment_week_view, container, false);
        TableLayout tl = (TableLayout) view.findViewById(R.id.weekView);
        View.OnClickListener on = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };
        wwf =new week_view_fragment(view.getContext(),tl,this);
        wwf.setWeekDatesText(getCurrentWeekDates());
        Calendar[] dates = dateCalc.getCurrentWeekDates();

        wwf.createTable(getRenderingEvents(dates));

        return view;
    }


    @Override
    public void onClick(View v) {
        TextView clickedCell = (TextView) v;
       Log.d("EventDbg","apan sover");
        if(clickedCell.getId() == R.id.nxtWeek|| clickedCell.getId() == R.id.prevWeek) {
            Log.d("EventDbg",((Calendar)clickedCell.getTag()).toString());
            Calendar[] weekDates = getWeekDates((Calendar)clickedCell.getTag());

            wwf.setWeekDatesText(weekDates);
            wwf.renderEvents(getRenderingEvents(weekDates));
        }
        if(clickedCell.getId() == R.id.cell){
             clickedCell.setText((String)clickedCell.getTag());
        }

    }
    private HashMap<String,Calendar[]> getRenderingEvents(Calendar[] dates){
        EventContainer eC = EventContainer.getEventContainer();
        Date stuff = new Date();
        Date stuff2 = new Date();
        stuff2.setTime(stuff.getTime()+86400000);
        Date stuff3 = new Date();
        stuff3.setTime(stuff2.getTime()+604800000 );
        Date stuff4 = new Date();
        stuff4.setTime(stuff3.getTime()+86400000);
        DefaultEvent de = new DefaultEvent(new Course("stuff", "sak"),"apa","apan sover",stuff,stuff2);
        eC.addEvent(de);
        de = new DefaultEvent(new Course("stuf", "sa"),"apsa","aspan sover",stuff3,stuff4);
        eC.addEvent(de);
        ArrayList<Event> toBeRendered = eC.getEventsByDates(dates[0], dates[1]);
        HashMap<String,Calendar[]> renderData = new HashMap<>();
        for(int i = 0 ; i<toBeRendered.size();i++){
            Calendar[] inputDates = new Calendar[2];
            Calendar endDate =  Calendar.getInstance();
            endDate.setTime(toBeRendered.get(i).getEndDate());
            inputDates[0] = null;
            inputDates[1] = endDate;

            if(toBeRendered.get(i) instanceof DefaultEvent){
                Calendar startDate = Calendar.getInstance();
                startDate.setTime(((DefaultEvent)toBeRendered.get(i)).getStartDate());
                inputDates[0] = startDate;

            }
            renderData.put(toBeRendered.get(i).getName(),inputDates);
        }
        return renderData;
    }
}
