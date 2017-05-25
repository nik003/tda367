package gruppnan.timeline.controller;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import gruppnan.timeline.R;
import gruppnan.timeline.model.DeadlineEvent;
import gruppnan.timeline.model.DefaultEvent;
import gruppnan.timeline.model.Event;
import gruppnan.timeline.model.EventInterface;



/**
 * Created by Hannes on 15/05/2017.
 * Adapter to show specific data from events in list view widget in CalendarFragment class.
 */

public class EventAdapter extends ArrayAdapter<Event> {

    private int layoutId;
    private FloatingActionButton editEventBtn;
    private FragmentManager fragmentManager;
    private Fragment fragment;
    private Calendar calendar;
    private int year,month,day;


    public EventAdapter(@NonNull Context context, int layoutResourceId, ArrayList<Event> events, Fragment fragment) {
        super(context, layoutResourceId, events);
        this.layoutId = layoutResourceId;
        this.fragment = fragment;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext()).inflate(layoutId, parent, false);
        }

        final EventInterface eTmp = getItem(position);
        TextView eventName = (TextView) convertView.findViewById(R.id.eventNameTxt1);
        TextView eventType = (TextView) convertView.findViewById(R.id.typeTxt);
        editEventBtn = (FloatingActionButton) convertView.findViewById(R.id.editEventBtn);
        editEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();

                Fragment newFragment = new AddEventFragment();
                bundle.putString("name", eTmp.getName());
                bundle.putString("description", eTmp.getDescription());
                Date endDate = eTmp.getEndDate();
                Date startDate = eTmp.getStartDate();
                calendar = Calendar.getInstance();
                setDate(startDate, endDate, bundle, eTmp);
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
                newFragment.setArguments(bundle);


                fragmentManager = fragment.getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.calendar_fragment, newFragment).addToBackStack(null).commit();
            }
        });

        eventName.setText("Name: " + eTmp.getName() );

        if (eTmp instanceof DeadlineEvent){
            eventType.setText("Type: Deadline");
        }else if (eTmp instanceof Event){
            eventType.setText("Type: Event");
        }


        return convertView;
    }
    private void setDate(Date startDate, Date endDate, Bundle bundle, EventInterface eTmp){
        //was here
    }

}
