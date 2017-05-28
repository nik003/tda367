package gruppnan.timeline.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import gruppnan.timeline.R;
import gruppnan.timeline.model.CourseRepository;

/**
 * @author Melina Andersson
 * The Timeline View
 *
 * Used by: ContentTimeLineFragment
 * Uses: CourseRepository
 */
public class ContentTimelineView  {


    private RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager mLayoutManager;
    private Button button;



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

        }
    }

    /**
     * Inits the list in the recyclerview
     */
    public void initList(){
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }


    public Button getButton(){
        return button;
    }



    public View getRootView() {
        return mRootView;
    }

    public RecyclerView getmRecyclerView() {
        return mRecyclerView;
    }
}
