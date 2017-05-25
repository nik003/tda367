package gruppnan.timeline.controller;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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
    private FloatingActionButton editEventBtn, deleteEventBtn;



    public EventAdapter(@NonNull Context context, int layoutResourceId, ArrayList<Event> events) {
        super(context, layoutResourceId, events);
        this.layoutId = layoutResourceId;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
        }

        final EventInterface eTmp = getItem(position);
        TextView eventName = (TextView) convertView.findViewById(R.id.eventNameTxt1);
        TextView eventType = (TextView) convertView.findViewById(R.id.typeTxt);
        editEventBtn = (FloatingActionButton) convertView.findViewById(R.id.editEventBtn);
        deleteEventBtn = (FloatingActionButton) convertView.findViewById(R.id.deleteEventBtn);

        editEventBtn.setOnClickListener(new EventListener(getContext(),eTmp));
        deleteEventBtn.setOnClickListener(new EventListener(getContext(),eTmp));

        eventName.setText("Name: " + eTmp.getName() );

        if (eTmp instanceof DeadlineEvent){
            eventType.setText("Type: Deadline");
        }else if (eTmp instanceof Event){
            eventType.setText("Type: Event");
        }


        return convertView;
    }


}
