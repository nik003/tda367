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
import gruppnan.timeline.controller.ContentTimelineFragment;
import gruppnan.timeline.model.CourseContainer;
import gruppnan.timeline.model.DeadlineEventSet;

/**
 * Created by Melina Andersson
 * The Timeline View
 */
public class ContentTimelineView implements ViewMVC  {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button button;

    private List<DeadlineEventSet> sortedEventSet = new ArrayList<>();

    View mRootView;
    ContentTimelineFragment fragment;

    public ContentTimelineView(LayoutInflater inflater, ViewGroup container, ContentTimelineFragment fragment){
        this.fragment = fragment;

        //If no courses exists, show how to add a course
        if(CourseContainer.getCourseContainer().getAllCourses().isEmpty()){
            mRootView = inflater.inflate(R.layout.empty_timeline_layout,container,false);
            button = (Button) mRootView.findViewById(R.id.to_settings_button);
            showGuideToAddCourses();
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

    /**
     * Guides the user to settings if no course yet is added
     */
    public void showGuideToAddCourses(){
        button.setOnClickListener(fragment);
    }

    public RecyclerView.Adapter getAdapter(){
        return mAdapter;
    }

    @Override
    public View getRootView() {
        return mRootView;
    }

}
