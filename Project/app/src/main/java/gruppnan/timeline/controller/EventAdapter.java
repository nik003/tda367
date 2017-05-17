package gruppnan.timeline.controller;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import gruppnan.timeline.R;
import gruppnan.timeline.model.DeadlineEvent;
import gruppnan.timeline.model.Event;
import gruppnan.timeline.model.EventInterface;



/**
 * Created by Hannes on 15/05/2017.
 * Adapter to show specific data from events in list view widget in CalendarFragment class.
 */

public class EventAdapter extends ArrayAdapter<Event> {

    private int layoutId;


    public EventAdapter(@NonNull Context context, int layoutResourceId, ArrayList<Event> events) {
        super(context, layoutResourceId, events);
        this.layoutId = layoutResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        EventInterface event = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext()).inflate(layoutId, parent, false);
        }

        TextView eventName = (TextView) convertView.findViewById(R.id.eventNameTxt1);
        TextView eventType = (TextView) convertView.findViewById(R.id.typeTxt);


        eventName.setText("Name: " + event.getName() );

        if (event instanceof DeadlineEvent){
            eventType.setText("Type: Deadline");
        }else if (event instanceof Event){
            eventType.setText("Type: Event");
        }


        return convertView;
    }
}
