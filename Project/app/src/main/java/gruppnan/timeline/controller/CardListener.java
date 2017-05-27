package gruppnan.timeline.controller;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import gruppnan.timeline.R;
import gruppnan.timeline.model.DeadlineEvent;

/**
 * @author Melina Andersson
 * Helper class for settings listener on every card in timeline
 *
 * Used by: ItemListAdapter
 * Uses: CardTimeLineFragment
 */
public class CardListener implements View.OnClickListener {

    private Context context;
    private DeadlineEvent dEvent;

    public CardListener(DeadlineEvent dEvent, Context context){
        this.dEvent = dEvent;
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        Fragment ft = new CardTimelineFragment();
        Bundle args = new Bundle();
        args.putInt("ID", dEvent.getID());
        ft.setArguments(args);
        ((FragmentActivity)context).getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame,ft)
                .commit();
    }
}
