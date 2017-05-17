package gruppnan.timeline.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import gruppnan.timeline.ItemListAdapter;
import gruppnan.timeline.R;
import gruppnan.timeline.model.DeadlineEventSet;
import gruppnan.timeline.view.ContentTimelineView;
import gruppnan.timeline.view.EventSorter;

public class ContentTimelineFragment extends Fragment {

    ContentTimelineView mConentTimelineView;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<DeadlineEventSet> sortedEventSet = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mConentTimelineView = new ContentTimelineView(inflater,container);
        mLayoutManager = new LinearLayoutManager(inflater.getContext());
        initList();
        return mConentTimelineView.getRootView();
    }

    public void initList(){

        mRecyclerView = (RecyclerView) mConentTimelineView.getRootView().findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        EventSorter eventSorter = new EventSorter();
        sortedEventSet = eventSorter.getSortedEventList();
        mAdapter = new ItemListAdapter(sortedEventSet);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();


    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


}