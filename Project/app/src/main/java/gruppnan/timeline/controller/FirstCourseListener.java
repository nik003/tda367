package gruppnan.timeline.controller;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import gruppnan.timeline.R;
import gruppnan.timeline.model.DeadlineEventSet;

/**
 * Created by Melina on 18/05/2017.
 */

public class FirstCourseListener implements ICardListener {


    private Context context;
    DeadlineEventSet dEvent;


    public FirstCourseListener(DeadlineEventSet dEvent, Context context){
        this.dEvent = dEvent;
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        Fragment ft = new CardTimelineFragment();
        Bundle args = new Bundle();
        args.putInt("ID", dEvent.getD1().getID());
        args.putInt("Course", 1);
        ft.setArguments(args);
        ((FragmentActivity)context).getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame,ft)
                .commit();
    }
}
