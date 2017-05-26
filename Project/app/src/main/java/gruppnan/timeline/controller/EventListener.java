package gruppnan.timeline.controller;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;

import java.util.Calendar;
import java.util.Date;

import gruppnan.timeline.R;
import gruppnan.timeline.model.DeadlineEvent;
import gruppnan.timeline.model.DefaultEvent;
import gruppnan.timeline.model.EventContainer;
import gruppnan.timeline.model.EventInterface;
import gruppnan.timeline.view.AddEventView;

/**
 * Created by Hannes
 * Helper class that handles onClickListeners for buttons in EventAdapter class
 */

public class EventListener implements OnClickListener {

    private Context context;
    private EventInterface eTmp;
    private Calendar calendar;
    private int year,month,day;
    private Bundle bundle;
    private EventContainer eventContainer = EventContainer.getEventContainer();

    public EventListener(Context context, EventInterface event){
        this.context = context;
        eTmp = event;
    }

    @Override
    public void onClick(View view) {
        bundle = new Bundle();
        if (view.getId() == R.id.editEventBtn){
            getEventInfo();
            changeFragment();
        }else if (view.getId() == R.id.deleteEventBtn){
            eventContainer.removeEvent(eTmp.getID());
        }
    }

    /** open fragments where user can see and edit event info */
    private void changeFragment(){
        Fragment newFragment = new AddEventFragment();
        newFragment.setArguments(bundle);


        ((FragmentActivity)context).getSupportFragmentManager().beginTransaction()
                .replace(R.id.calendar_fragment,newFragment)
                .addToBackStack(null)
                .commit();
    }

    /** gets event info from listView, adds to bundle so next fragment gets event info */
    private void getEventInfo(){
        bundle.putString("name", eTmp.getName());
        bundle.putString("description", eTmp.getDescription());
        Date endDate = eTmp.getEndDate();
        Date startDate = eTmp.getStartDate();
        calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        int endHour = calendar.get(Calendar.HOUR_OF_DAY);
        int endMinute = calendar.get(Calendar.MINUTE);
        bundle.putInt("year", year);
        bundle.putInt("month", month);
        bundle.putInt("day", day);
        bundle.putInt("endHour", endHour);
        bundle.putInt("endMinute", endMinute);
        bundle.putInt("id", eTmp.getID());

        if (eTmp instanceof DefaultEvent){
            calendar.setTime(startDate);

            int startHour = calendar.get(Calendar.HOUR_OF_DAY);
            int startMinute = calendar.get(Calendar.MINUTE);
            bundle.putString("type", "event");
            bundle.putInt("startHour", startHour);
            bundle.putInt("startMinute", startMinute);
        }

        if (eTmp instanceof DeadlineEvent){
            bundle.putString("type", "deadline");
        }
    }

}
