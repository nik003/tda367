package gruppnan.timeline;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.vipulasri.timelineview.TimelineView;

import java.util.List;

import gruppnan.timeline.controller.CardTimelineFragment;
import gruppnan.timeline.model.DeadlineEvent;
import gruppnan.timeline.model.DeadlineEventSet;

/**
 * Created by Melina on 02/05/2017.
 */

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {

    private List<DeadlineEventSet> data;
    private Context mContext;

    public ItemListAdapter(List<DeadlineEventSet> deadlineEvents) {
        this.data = deadlineEvents;
    }


    DeadlineEventSet dEvent;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView dateText1, titleText1, dateText2, titleText2;
        TimelineView courseView1, courseView2;
        CardView courseCard1, courseCard2;

        public ViewHolder(View v, int viewType) {
            super(v);
            courseView1 = (TimelineView) itemView.findViewById(R.id.course1);
            courseView1.initLine(viewType);

            courseView2 = (TimelineView) itemView.findViewById(R.id.course2);
            courseView2.initLine(viewType);

            dateText1 = (TextView) v.findViewById(R.id.date1_timeline);
            titleText1 = (TextView) v.findViewById(R.id.text1_timeline);

            dateText2 = (TextView) v.findViewById(R.id.date2_timeline);
            titleText2 = (TextView) v.findViewById(R.id.text2_timeline);

            courseCard1 = (CardView) v.findViewById(R.id.card1);
            courseCard2 = (CardView) v.findViewById(R.id.card2);

        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ItemListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        mContext = parent.getContext();
        // create a new view
                View v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_timeline, parent, false);
                ViewHolder vh = new ViewHolder(v,viewType);

                return vh;

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        dEvent = data.get(position);

        updateContent(dEvent,holder,position);

        setCardListener(holder,position);

    }

    private void setCardListener(ViewHolder holder, final int position) {

        holder.courseCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment ft = new CardTimelineFragment();
                Bundle args = new Bundle();
                args.putInt("Position", position);
                args.putInt("Course", 1);
                ft.setArguments(args);
                ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction()
                        .add(R.id.frame,ft)
                        .commit();
            }
        });

        holder.courseCard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment ft = new CardTimelineFragment();
                Bundle args = new Bundle();
                args.putInt("Position", position);
                args.putInt("Course", 2);
                ft.setArguments(args);
                ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction()
                        .add(R.id.frame,ft)
                        .commit();
            }
        });


    }

    private void updateContent(DeadlineEventSet dEvent, ViewHolder holder, int position) {


        if((dEvent.getD1() != null && dEvent.getD2() != null)){

            updateCourse1Markers(holder,dEvent);
            updateCourse2Markers(holder,dEvent);

            setTextDate(holder.dateText1, dEvent.getD1());
            setTextTitle(holder.titleText1, dEvent.getD1());

            setTextDate(holder.dateText2, dEvent.getD2());
            setTextTitle(holder.titleText2, dEvent.getD2());
        }


        if((dEvent.getD1() != null) && (dEvent.getD2() == null)) {

            updateCourse1Markers(holder,dEvent);

            //Initializing timeline with a marker
            if(position == 0) {
                holder.courseView2.setMarker(getDrawable(mContext,R.drawable.red_marker));
            } else {
                holder.courseView2.setMarker(getDrawable(mContext, R.drawable.red_line));
            }
            //Set the other course's card invisible
            holder.courseCard2.setVisibility(View.INVISIBLE);

            setTextDate(holder.dateText1, dEvent.getD1());
            setTextTitle(holder.titleText1, dEvent.getD1());

        }

        if((dEvent.getD2() != null)  && (dEvent.getD1() == null)){

            updateCourse2Markers(holder,dEvent);

            //Initializing timeline with a marker
            if(position == 0) {
                holder.courseView1.setMarker(getDrawable(mContext, R.drawable.blue_marker));
            } else {
                holder.courseView1.setMarker(getDrawable(mContext, R.drawable.blue_line));
            }

            //Set the other course's card invisible
            holder.courseCard1.setVisibility(View.INVISIBLE);

            setTextDate(holder.dateText2, dEvent.getD2());
            setTextTitle(holder.titleText2, dEvent.getD2());
        }
    }


    private void setTextDate(TextView v, DeadlineEvent event) {
        v.setText(event.getDayofMonth() + " " + event.getMonthAsString());
    }

    private void setTextTitle(TextView v, DeadlineEvent event){
        v.setText(event.getName());
    }

    private void updateCourse1Markers(ViewHolder holder, DeadlineEventSet dEvent){
        if (!dEvent.getD1().isDone()) {
            holder.courseView1.setMarker(getDrawable(mContext, R.drawable.blue_marker_inactive));
        } else {
            holder.courseView1.setMarker(getDrawable(mContext, R.drawable.blue_marker));
        }
    }

    private void updateCourse2Markers(ViewHolder holder, DeadlineEventSet dEvent){
        if(!dEvent.getD2().isDone()) {
            holder.courseView2.setMarker(getDrawable(mContext, R.drawable.red_marker_inactive));
        } else  {
            holder.courseView2.setMarker(getDrawable(mContext, R.drawable.red_marker));
        }
    }



    public static Drawable getDrawable(Context context, int drawableResId) {
        Drawable drawable;

        if (true) {
            drawable = context.getResources().getDrawable(drawableResId, context.getTheme());
        } else {
            drawable = VectorDrawableCompat.create(context.getResources(), drawableResId, context.getTheme());
        }

        return drawable;
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position,getItemCount());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}
