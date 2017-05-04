package gruppnan.timeline.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import gruppnan.timeline.ItemListAdapter;
import gruppnan.timeline.R;
import gruppnan.timeline.model.Course;
import gruppnan.timeline.model.DeadlineEvent;

public class ContentTimelineFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<DeadlineEvent> deadlineEvents;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_timeline,
                container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        deadlineEvents = new ArrayList<>();


        // specify an adapter (see also next example)
        mAdapter = new ItemListAdapter(deadlineEvents);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        //mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        deadlineEvents.add(0,new DeadlineEvent(new Course("hdhdh","eiei"), "Projekt", "hej", new Date(), true));
        deadlineEvents.add(1,new DeadlineEvent(new Course("hdh","ei"), "Matte", "hej", new Date(), false));
        deadlineEvents.add(2,new DeadlineEvent(new Course("hdhdh","eiei"), "Klä på mig", "hej", new Date(), false));
        deadlineEvents.add(3,new DeadlineEvent(new Course("hdh","ei"), "Osv", "hej", new Date(), false));
        deadlineEvents.add(4,new DeadlineEvent(new Course("hdhdh","eiei"), "Projekt", "hej", new Date(), false));
        deadlineEvents.add(5,new DeadlineEvent(new Course("hdh","ei"), "Hej", "hej", new Date(), false));
        deadlineEvents.add(6,new DeadlineEvent(new Course("hdhdh","eiei"), "Då", "hej", new Date(), false));
        deadlineEvents.add(7,new DeadlineEvent(new Course("hdh","ei"), "TDA355", "hej", new Date(), false));

        mAdapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}