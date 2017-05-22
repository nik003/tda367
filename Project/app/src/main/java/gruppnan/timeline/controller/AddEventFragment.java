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

    private View view;




    private EventContainer eventContainer;
    private FragmentManager fragmentManager;



    private AddEventView addEventView;

    public AddEventFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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




    public void removeFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.remove(this);
        fragmentTransaction.commit();
    }


    public void createDeadlineEvent(Course course, String name, String eventDescription, Date endDate, boolean isDone){
        eventContainer.createDeadlineEvent(course, name, eventDescription, endDate, isDone);
        removeFragment();
    }
    public void createDefaultEvent(Course course, String name, String eventDescription, Date startDate, Date endDate){
        eventContainer.createDefaultEvent(course,name,eventDescription,startDate, endDate);
        removeFragment();
    }

}
