package gruppnan.timeline.controller;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import gruppnan.timeline.R;
import gruppnan.timeline.model.DeadlineEvent;

/**
 * Created by Melina on 18/05/2017.
 */

public class CardListener implements ICardListener {


    private Context context;
    DeadlineEvent dEvent;
    private int courseNumber;


    public CardListener(DeadlineEvent dEvent, Context context, int courseNumber){
        this.dEvent = dEvent;
        this.context = context;
        this.courseNumber = courseNumber;
    }

    @Override
    public void onClick(View view) {
        Fragment ft = new CardTimelineFragment();
        Bundle args = new Bundle();
        args.putInt("ID", dEvent.getID());
        args.putInt("Course", courseNumber);
        ft.setArguments(args);
        ((FragmentActivity)context).getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame,ft)
                .commit();
    }
}
