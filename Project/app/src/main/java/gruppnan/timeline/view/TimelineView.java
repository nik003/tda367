package gruppnan.timeline.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gruppnan.timeline.R;

/**
 * Created by Melina on 30/04/2017.
 */

public class TimelineView implements ViewMVC {

    private View mRootView;

    public TimelineView(LayoutInflater inflater, ViewGroup container){
        mRootView = inflater.inflate(R.layout.timeline,
                container, false);

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
