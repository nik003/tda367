package gruppnan.timeline.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import gruppnan.timeline.R;
import gruppnan.timeline.model.CourseRepository;
import gruppnan.timeline.view.ContentTimelineView;

/**
 * Created by Melina Andersson
 * Controlls the timeline view
 *
 * Used by: MainActivity
 * Uses: ContentTimelineView, CourseRepository
 */
public class ContentTimelineFragment extends Fragment implements Button.OnClickListener{

    ContentTimelineView contentTimelineView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        contentTimelineView = new ContentTimelineView(inflater,container);

        if(!CourseRepository.getCourseRepository().getAllCourses().isEmpty()){
            contentTimelineView.getAdapter().notifyDataSetChanged();
        } else {
            showGuideToAddCourses();
        }

        return contentTimelineView.getRootView();
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    /**
     * Guides the user to settings if no course yet is added
     */
    public void showGuideToAddCourses(){
        contentTimelineView.getButton().setOnClickListener(this);
    }

    /**
     * Handles button click for the empty timeline
     * @param view
     */
    @Override
    public void onClick(View view) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame, new SettingsFragment()).addToBackStack(null).commit();
    }
}