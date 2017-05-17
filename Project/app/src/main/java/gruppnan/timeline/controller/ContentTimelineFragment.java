package gruppnan.timeline.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gruppnan.timeline.view.ContentTimelineView;

public class ContentTimelineFragment extends Fragment {

    ContentTimelineView mConentTimelineView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mConentTimelineView = new ContentTimelineView(inflater,container);

        //Update view
        mConentTimelineView.getAdapter().notifyDataSetChanged();

        return mConentTimelineView.getRootView();
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


}