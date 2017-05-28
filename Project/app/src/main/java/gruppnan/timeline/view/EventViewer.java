package gruppnan.timeline.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import gruppnan.timeline.R;
import gruppnan.timeline.model.DeadlineEvent;
import gruppnan.timeline.model.DefaultEvent;
import gruppnan.timeline.model.Event;

/**
 * Created by Nikolai on 2017-05-25.
 * The creation of a view that shows an event.
 * Uses:Event,Course,DefaultEvent
 * Used by: WeekCalendarView
 */

public class EventViewer {
    private LinearLayout root;
    private View.OnClickListener onCl;
    private Event event;

    public EventViewer(LinearLayout root, View.OnClickListener onCl , Event event) {
        this.root = root;
        this.onCl = onCl;

        this.event = event;
    }

    /**
     *
     * Renders the informational view for an event
     */
    public void renderView(){

        Button btn = (Button)root.findViewById(R.id.eventViewBack);

        btn.setOnClickListener(onCl);

        TextView t = (TextView) root.findViewById(R.id.eventName);
        t.setText(event.getName());

        t =(TextView) root.findViewById(R.id.eventDescription);
        t.setText(event.getDescription());

        t =(TextView) root.findViewById(R.id.eventDates);
        if(event instanceof DefaultEvent){
            t.setText(((DefaultEvent) event).getStartDate().toString() + " - " + event.getEndDate().toString());
        }else{

            t.setText(event.getEndDate().toString());
        }
        t =(TextView) root.findViewById(R.id.eventCourseName);
        if(event.getCourse()!=null)
            t.setText(event.getCourse().getCourseID()+ " "+event.getCourse().getName());

    }
}
