package gruppnan.timeline.view;

import android.os.Bundle;
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

/**
 * Created by Melina on 30/04/2017.
 */

public class ContentTimelineView implements ViewMVC  {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<DeadlineEventSet> sortedEventSet = new ArrayList<>();

    View mRootView;

    public ContentTimelineView(LayoutInflater inflater, ViewGroup container){

        mRootView = inflater.inflate(R.layout.content_timeline, container, false);
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(inflater.getContext());
        initList();
    }

    public void initList(){

        mRecyclerView.setHasFixedSize(true);

        EventSorter eventSorter = new EventSorter();
        sortedEventSet = eventSorter.getSortedEventList();
        mAdapter = new ItemListAdapter(sortedEventSet);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

    public RecyclerView.Adapter getAdapter(){
        return mAdapter;
    }




    @Override
    public View getRootView() {
        return mRootView;
    }

    @Override
    public Bundle getViewState() {
        return null;
    }
}
