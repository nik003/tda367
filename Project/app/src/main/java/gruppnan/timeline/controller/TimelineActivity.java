package gruppnan.timeline.controller;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import gruppnan.timeline.R;

/**
 * Created by Melina on 02/05/2017.
 */

public class TimelineActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeline);
        init_content();
    }

    public void init_content(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.timelineFragment, new ContentTimelineFragment());
        ft.commit();
    }
}
