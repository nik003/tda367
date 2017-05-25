package gruppnan.timeline.controller;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import java.util.ArrayList;
import java.util.Calendar;
import gruppnan.timeline.R;
import gruppnan.timeline.model.Event;
import gruppnan.timeline.model.EventContainer;
import gruppnan.timeline.view.MonthCalendarView;


/**
 * Controller fragment class that
 */

public class CalendarFragment extends Fragment {



    private FragmentManager fragmentManager;
    private FragmentTransaction ft;
    private MonthCalendarView monthCalendarView;


    final Calendar start = Calendar.getInstance();
    final Calendar end = Calendar.getInstance();
    final EventContainer eventContainer = EventContainer.getEventContainer();
    private Long dateLong;
    public CalendarFragment() {
        // Required empty public constructor
    }




    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            if(container != null){
                container.removeAllViews();
            }
            monthCalendarView = new MonthCalendarView(inflater,container);
        dateLong= monthCalendarView.getCalendarView().getDate();
        addListeners();

        return monthCalendarView.getView();
    }


    private void addListeners(){
        monthCalendarView.getDeadlineFab().setOnClickListener(btnListener);
        monthCalendarView.getEventFab().setOnClickListener(btnListener);
        monthCalendarView.getWeekViewButton().setOnClickListener(btnListener);
        monthCalendarView.getCalendarView().setOnDateChangeListener(dateChangeListener);
    }


    /** changes the list view, that shows events, according to selected date*/
    private CalendarView.OnDateChangeListener dateChangeListener = new CalendarView.OnDateChangeListener() {
        @Override
        public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
            final ArrayList<Event> list = new ArrayList<>();
            Calendar cal = Calendar.getInstance();
            cal.set(year,month,dayOfMonth);
            dateLong = cal.getTimeInMillis();
            start.set(year,month,dayOfMonth,0,0);
            end.set(year, month, dayOfMonth, 23, 59);

            list.clear();
            EventAdapter adapter = new EventAdapter(getContext(),R.layout.event_list_item, list, CalendarFragment.this);
            list.addAll(eventContainer.getEventsByDates(start,end));
            monthCalendarView.getEventListView().setAdapter(adapter);
        }
    };


    private View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Bundle bundle = new Bundle();
            if (view.equals(monthCalendarView.getEventFab())){
                Fragment newFragment = new AddEventFragment();
                bundle.putString("type", "event");
                bundle.putLong("date", dateLong);

                newFragment.setArguments(bundle);

                fragmentManager = getActivity().getSupportFragmentManager();
                ft = fragmentManager.beginTransaction();
                ft.replace(R.id.calendar_fragment, newFragment).addToBackStack(null).commit();
            }
            else if (view.equals(monthCalendarView.getDeadlineFab())){
                bundle.putString("type", "deadline");
                bundle.putLong("date", dateLong);
                Fragment newFragment = new AddEventFragment();
                newFragment.setArguments(bundle);

                fragmentManager = getActivity().getSupportFragmentManager();
                ft = fragmentManager.beginTransaction();
                ft.replace(R.id.calendar_fragment, newFragment).addToBackStack(null).commit();

            }
            else if (view.equals(monthCalendarView.getWeekViewButton())){
                Fragment weekViewFragment = new WeekViewController();
                fragmentManager = getActivity().getSupportFragmentManager();
                ft = fragmentManager.beginTransaction();
                ft.replace(R.id.calendar_fragment, weekViewFragment).addToBackStack(null).commit();
            }
        }
    };




}
