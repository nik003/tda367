package gruppnan.timeline.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;

import gruppnan.timeline.R;
import gruppnan.timeline.controller.EventListener;
import gruppnan.timeline.model.DeadlineEvent;
import gruppnan.timeline.model.Event;
import gruppnan.timeline.model.EventInterface;

/**
 * Created by Nikolai on 2017-05-27.
 * Used by: EventAdapter
 * Uses:DeadlineEvent,EventInterface,Event
 */

public class EventAdapterView {
    private FloatingActionButton editEventBtn, deleteEventBtn;


    private int layoutId;
    private Context context;
    private ViewGroup parent;


    public EventAdapterView(int layoutId,Context context, ViewGroup parent){
        this.layoutId = layoutId;
        this.parent = parent;
        this.context = context;


    }

    public View createView(EventInterface eTmp, View convertView, View.OnClickListener onCl){

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        }


        TextView eventName = (TextView) convertView.findViewById(R.id.eventNameTxt1);
        TextView eventType = (TextView) convertView.findViewById(R.id.typeTxt);

        editEventBtn = (FloatingActionButton) convertView.findViewById(R.id.editEventBtn);
        deleteEventBtn = (FloatingActionButton) convertView.findViewById(R.id.deleteEventBtn);

        editEventBtn.setOnClickListener(onCl);
        deleteEventBtn.setOnClickListener(onCl);

        eventName.setText("Name: " + eTmp.getName() );

        if (eTmp instanceof DeadlineEvent){
            eventType.setText("Type: Deadline");
        }else if (eTmp instanceof Event){
            eventType.setText("Type: Event");
        }
        return convertView;
    }
}
