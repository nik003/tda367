package gruppnan.timeline.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;


import gruppnan.timeline.R;
import gruppnan.timeline.controller.EventListener;
import gruppnan.timeline.model.DeadlineEvent;
import gruppnan.timeline.model.Event;
import gruppnan.timeline.model.EventInterface;
import gruppnan.timeline.view.EventAdapterView;


/**
 * @author Hannes
 * Adapter to show specific data from events in list view widget in CalendarFragment class.
 * Used in CalendarFragment.
 */

public class EventAdapter extends ArrayAdapter<Event> {

    private int layoutId;




    public EventAdapter(@NonNull Context context, int layoutResourceId, ArrayList<Event> events) {
        super(context, layoutResourceId, events);
        this.layoutId = layoutResourceId;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        EventAdapterView eav = new EventAdapterView(layoutId,getContext(),parent);
        final EventInterface eTmp = getItem(position);
        EventListener ev = new EventListener(getContext(),eTmp);
        convertView = eav.createView(eTmp,convertView, ev);




        return convertView;
    }


}
