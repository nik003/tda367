package gruppnan.timeline.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import edu.emory.mathcs.backport.java.util.Collections;
import gruppnan.timeline.ItemListAdapter;
import gruppnan.timeline.R;
import gruppnan.timeline.model.Course;
import gruppnan.timeline.model.DeadlineEvent;
import gruppnan.timeline.model.DeadlineEventSet;

public class ContentTimelineFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<DeadlineEvent> dEvents, a1, a2;
    private List<DeadlineEventSet> dest;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_timeline,
                container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        dEvents = new ArrayList<>();
        a1 = new ArrayList<>();
        a2 = new ArrayList<>();
        dest = new ArrayList<>();

        //temporary code
       /* dEvents.add(0,new DeadlineEvent(new Course("course1","eiei"), "Laboration 1", "hej", 1, true));
        dEvents.add(1,new DeadlineEvent(new Course("course1","ei"), "Laboration 2", "hej", 2, true));
        dEvents.add(2,new DeadlineEvent(new Course("course1","eiei"), "Laboration 3", "hej", 7, false));
        dEvents.add(3,new DeadlineEvent(new Course("course2","ei"), "Inlämning 1", "hej", 2, false));
        dEvents.add(4,new DeadlineEvent(new Course("course2","eiei"), "Seminarie", "hej", 3, false));
        dEvents.add(5,new DeadlineEvent(new Course("course2","ei"), "Inlämning 2", "hej", 5, false));
        dEvents.add(6,new DeadlineEvent(new Course("course1","eiei"), "Laboration 4", "hej", 8, false));
        dEvents.add(7,new DeadlineEvent(new Course("course2","ei"), "Tenta", "hej",10, false));


        sortEvents();
        addEvents();
        */
        mAdapter = new ItemListAdapter(dest);
        mLayoutManager = new LinearLayoutManager(getActivity());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        return view;
    }


   /* public void sortEvents(){
        Collections.sort(dEvents, new Comparator<DeadlineEvent>() {
            @Override
            public int compare(DeadlineEvent deadlineEvent, DeadlineEvent t1) {
                return deadlineEvent.getDate() - t1.getDate();
            }
        });
    }*/

    public void addEvents(){
        //separate the events by courses
        for(DeadlineEvent item : dEvents){
            if(item.getCourseID() == "course1"){
                a1.add(item);
            } else {
                a2.add(item);
            }
        }

        //set the events in pairs in order to be displayed chronologically in the view
        int i = 0;
        int j = 0;
        while(i+j < dEvents.size()){
            DeadlineEvent d1 = i < a1.size() ? a1.get(i) : null;
            DeadlineEvent d2 = j < a2.size() ? a2.get(j) : null;
            if( d1 == null && d2 == null) {
                break;
            } else if ( d1 == null ) {
                dest.add(new DeadlineEventSet(null,d2));
                j++;
            } else if ( d2 == null ) {
                dest.add(new DeadlineEventSet(d1, null));
                i++;
            } else if(d1.getDate().getTime() < d2.getDate().getTime()) {
                dest.add(new DeadlineEventSet(d1, null));
                i++;
            } else if(d1.getDate().getTime() > d2.getDate().getTime()) {
                dest.add(new DeadlineEventSet(null,d2));
                j++;
            } else {
                dest.add(new DeadlineEventSet(d1,d2));
                i++;
                j++;
            }

        }
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}