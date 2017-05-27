package gruppnan.timeline.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import gruppnan.timeline.R;
import gruppnan.timeline.Utils.EventSorter;
import gruppnan.timeline.controller.ItemListAdapter;
import gruppnan.timeline.model.CourseRepository;
import gruppnan.timeline.model.DeadlineEventSet;

/**
 * Created by Melina Andersson
 * The Timeline View
 *
 * Used by: ContentTimeLineFragment
 * Uses: DeadlineEventSet, EventSorter, CourseRepository
 */
public class ContentTimelineView  {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button button;

    private List<DeadlineEventSet> sortedEventSet = new ArrayList<>();

    View mRootView;

    public ContentTimelineView(LayoutInflater inflater, ViewGroup container){

        //If no courses exists, show how to add a course
        if(CourseRepository.getCourseRepository().getAllCourses().isEmpty()){
            mRootView = inflater.inflate(R.layout.empty_timeline_layout,container,false);
            button = (Button) mRootView.findViewById(R.id.to_settings_button);
        } else {
            //Initialize timeline
            mRootView = inflater.inflate(R.layout.content_timeline, container, false);
            mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recyclerView);
            mLayoutManager = new LinearLayoutManager(inflater.getContext());
            initList();
        }
    }

    /**
     * Inits the list in the recyclerview
     */
    public void initList(){

        mRecyclerView.setHasFixedSize(true);

        EventSorter eventSorter = new EventSorter();
        sortedEventSet = eventSorter.getSortedEventList();
        mAdapter = new ItemListAdapter(sortedEventSet);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }


    public Button getButton(){
        return button;
    }

    public RecyclerView.Adapter getAdapter(){
        return mAdapter;
    }

    public View getRootView() {
        return mRootView;
    }

}
