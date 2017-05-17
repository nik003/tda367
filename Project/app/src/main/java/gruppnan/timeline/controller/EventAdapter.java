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
 */

public class EventAdapter extends ArrayAdapter<Event> {



    public EventAdapter(@NonNull Context context, @LayoutRes int resource, ArrayList<Event> events) {
        super(context, resource, events);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        EventInterface event = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        TextView eventName = (TextView) convertView.findViewById(R.id.eventNameTxt1);
        TextView eventType = (TextView) convertView.findViewById(R.id.typeTxt);





            // My layout has only one TextView
            // do whatever you want with your string and long
            eventName.setText("Name: " + event.getName());
            if (event instanceof DeadlineEvent){
                eventType.setText("Type: Deadline");
            }else if (event instanceof Event){
                eventType.setText("Type: Event");
            }


        return convertView;
    }
}
