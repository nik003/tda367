package gruppnan.timeline.controller;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;

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
                bundle.putLong("end", eTmp.getEndDate().getTime());
                if (eTmp instanceof DeadlineEvent){
                    bundle.putString("type", "deadline");
                }
                if (eTmp instanceof DefaultEvent){
                    bundle.putString("type", "default");
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

}
