package gruppnan.timeline.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import gruppnan.timeline.R;
import gruppnan.timeline.model.DeadlineEvent;
import gruppnan.timeline.model.DefaultEvent;
import gruppnan.timeline.model.Event;

/**
 * Created by Nikolai on 2017-05-25.
 */

public class EventViewer {
    private LayoutInflater inflater;
    private View.OnClickListener onCl;
    private Context context;
    private Event event;

    public EventViewer(LayoutInflater inflater, View.OnClickListener onCl, Context context ,Event event) {
        this.inflater = inflater;
        this.onCl = onCl;
        this.context = context;
        this.event = event;
    }
    public void renderView(){
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.eventviewer,null,false);
        TextView t = (TextView) v.findViewById(R.id.eventName);
        t.setText(event.getName());

        t =(TextView) v.findViewById(R.id.eventDescription);
        t.setText(event.getDescription());

        t =(TextView) v.findViewById(R.id.eventDates);
        if(event instanceof DefaultEvent){
            t.setText(((DefaultEvent) event).getStartDate().toString() + " - " + event.getEndDate().toString());
        }else{

            t.setText(event.getEndDate().toString());
        }
        t =(TextView) v.findViewById(R.id.eventCourseName);
        t.setText(event.getCourse().getCourseID()+ " "+event.getCourse().getName());

    }
}
