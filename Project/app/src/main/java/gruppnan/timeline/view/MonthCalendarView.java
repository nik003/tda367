package gruppnan.timeline.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;

import com.github.clans.fab.FloatingActionButton;

import gruppnan.timeline.R;

/**
 * Created by Hannes
 * View for CalendarFragment. Sets up calendarView, buttons and ListView with created events.
 */

public class MonthCalendarView {

    private FloatingActionButton eventFab,deadlineFab;
    private Button weekViewButton;
    private CalendarView calendarView;
    private View view;
    private ListView eventListView;



    public MonthCalendarView(LayoutInflater layoutInflater, ViewGroup container){
        view = layoutInflater.inflate(R.layout.fragment_calendar,container,false);
        setUpView(view);
    }

    private void setUpView(View view){

        eventFab = (FloatingActionButton) view.findViewById(R.id.fab1);
        deadlineFab = (FloatingActionButton) view.findViewById(R.id.fab2);
        eventListView = (ListView) view.findViewById(R.id.eventListView);
        weekViewButton = (Button) view.findViewById(R.id.weekViewBtn);
        calendarView = (CalendarView) view.findViewById(R.id.calendarView);

    }

    public FloatingActionButton getEventFab(){
        return this.eventFab;
    }
    public FloatingActionButton getDeadlineFab(){
        return this.deadlineFab;
    }
    public Button getWeekViewButton(){
        return this.weekViewButton;
    }
    public ListView getEventListView(){
        return this.eventListView;
    }

    public CalendarView getCalendarView(){
        return this.calendarView;
    }
    public View getView(){
        return this.view;
    }




}
