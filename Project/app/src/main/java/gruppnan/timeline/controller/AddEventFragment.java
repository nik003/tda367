package gruppnan.timeline.controller;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.Date;
import gruppnan.timeline.model.Course;
import gruppnan.timeline.model.Event;
import gruppnan.timeline.model.EventContainer;
import gruppnan.timeline.view.AddEventView;


public class AddEventFragment extends Fragment{



    private EventContainer eventContainer;
    private FragmentManager fragmentManager;
    private FragmentTransaction ft;


    private AddEventView addEventView;

    public AddEventFragment() {
        // Required empty public constructor
    }


    /** Set up view according to the type of event user wants to add */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        addEventView = new AddEventView(inflater, container,this);
        fragmentManager = getActivity().getSupportFragmentManager();
        eventContainer = EventContainer.getEventContainer();
        return addEventView.getView();
    }

    public void createDeadlineEvent(Course course, String name, String eventDescription, Date endDate, boolean isDone){
        eventContainer.createDeadlineEvent(course, name, eventDescription, endDate, isDone);
        removeFragment();
    }
    public void createDefaultEvent(Course course, String name, String eventDescription, Date startDate, Date endDate){
        eventContainer.createDefaultEvent(course,name,eventDescription,startDate, endDate);
        removeFragment();
    }
    public void removeFragment(){

        ft = fragmentManager.beginTransaction();

        ft.remove(this);
        ft.commit();
    }
}
