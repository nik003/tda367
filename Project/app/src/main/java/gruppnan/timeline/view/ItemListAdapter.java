package gruppnan.timeline.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.vipulasri.timelineview.TimelineView;

import java.util.List;

import gruppnan.timeline.R;
import gruppnan.timeline.controller.CardListener;
import gruppnan.timeline.model.DeadlineEvent;
import gruppnan.timeline.model.DeadlineEventSet;

/**
 * Created by Melina Andersson
 * Initializes and updates the content of the recyclerview in the timeline view
 */

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {

    private List<DeadlineEventSet> data;
    private Context mContext;

    public ItemListAdapter(List<DeadlineEventSet> deadlineEvents) {
        this.data = deadlineEvents;
    }

    private DeadlineEventSet dEvent;

    /**
     * Provides a reference to the views for each data item
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView dateText1, titleText1, dateText2, titleText2, courseTextCard,courseTextCard2;
        private TimelineView courseView1, courseView2;
        private CardView courseCard1, courseCard2;

        public ViewHolder(View v, int viewType) {
            super(v);
            courseView1 = (TimelineView) v.findViewById(R.id.course1);
            courseView1.initLine(viewType);

            courseView2 = (TimelineView) v.findViewById(R.id.course2);
            courseView2.initLine(viewType);

            dateText1 = (TextView) v.findViewById(R.id.date1_timeline);
            titleText1 = (TextView) v.findViewById(R.id.text1_timeline);

            dateText2 = (TextView) v.findViewById(R.id.date2_timeline);
            titleText2 = (TextView) v.findViewById(R.id.text2_timeline);

            courseCard1 = (CardView) v.findViewById(R.id.card1);
            courseCard2 = (CardView) v.findViewById(R.id.card2);

            courseTextCard = (TextView) v.findViewById(R.id.course_timeline);
            courseTextCard2 = (TextView) v.findViewById(R.id.course2_timeline);
        }
    }

    /**
     * Create new views (invoked by the layout manager)
     */
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

    /**
     *  Replace the contents of a view (invoked by the layout manager)
      */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //The current eventset to be updated
        dEvent = data.get(position);

        updateContent(dEvent,holder,position);
        setCardListeners(dEvent, holder);
    }

    /**
     * Set listener for each card on timeline
     * @param dEvent The current set to be updated
     * @param holder The viewholder for the view element
     */
    private void setCardListeners(final DeadlineEventSet dEvent, ViewHolder holder) {
        holder.courseCard1.setOnClickListener(new CardListener(dEvent.getD1(),mContext));
        holder.courseCard2.setOnClickListener(new CardListener(dEvent.getD2(),mContext));
    }

    /**
     * Updates texts and markers for the current card
     *
     * @param dEvent The current set to be updated
     * @param holder The viewholder for the view element
     * @param position The position of the DeadlineEventSet in the list
     */
    private void updateContent(DeadlineEventSet dEvent, ViewHolder holder, int position) {
        if((dEvent.getD1() != null && dEvent.getD2() != null)){

            updateCourse1Markers(holder,dEvent);
            updateCourse2Markers(holder,dEvent);

            setTextDate(holder.dateText1, dEvent.getD1());
            setTextTitle(holder.titleText1, dEvent.getD1());

            setTextDate(holder.dateText2, dEvent.getD2());
            setTextTitle(holder.titleText2, dEvent.getD2());

            setCourseText(dEvent.getD1(),holder.courseTextCard);
            setCourseText(dEvent.getD2(),holder.courseTextCard2);
        }

        if((dEvent.getD1() != null) && (dEvent.getD2() == null)) {

            updateCourse1Markers(holder,dEvent);

            //Initializing timeline with a marker
            if(position == 0) {
                holder.courseView2.setMarker(getDrawable(mContext,R.drawable.second_course_marker));
            } else {
                holder.courseView2.setMarker(getDrawable(mContext, R.drawable.second_course_line));
            }
            //Set the other course's card invisible
            holder.courseCard2.setVisibility(View.INVISIBLE);

            setTextDate(holder.dateText1, dEvent.getD1());
            setTextTitle(holder.titleText1, dEvent.getD1());


            setCourseText(dEvent.getD1(),holder.courseTextCard);

        }

        if((dEvent.getD2() != null)  && (dEvent.getD1() == null)){

            updateCourse2Markers(holder,dEvent);

            //Initializing timeline with a marker
            if(position == 0) {
                holder.courseView1.setMarker(getDrawable(mContext, R.drawable.first_course_marker));
            } else {
                holder.courseView1.setMarker(getDrawable(mContext, R.drawable.first_course_line));
            }

            //Set the other course's card invisible
            holder.courseCard1.setVisibility(View.INVISIBLE);

            setTextDate(holder.dateText2, dEvent.getD2());
            setTextTitle(holder.titleText2, dEvent.getD2());

            setCourseText(dEvent.getD2(),holder.courseTextCard2);
        }

    }


    private void setCourseText(DeadlineEvent dEvent, TextView v){
        v.setText(dEvent.getCourseID());
    }


    private void setTextDate(TextView v, DeadlineEvent event) {
        v.setText(event.getDayofMonth() + " " + event.getMonthAsString());
    }


    private void setTextTitle(TextView v, DeadlineEvent event){
        v.setText(event.getName());
    }

    /**
     * Updates markers for course one according to specific colors
     * @param holder The viewholder for the view element
     * @param dEvent The current set to be updated
     */
    public void updateCourse1Markers(ViewHolder holder, DeadlineEventSet dEvent){
        if (!dEvent.getD1().isDone()) {
            holder.courseView1.setMarker(getDrawable(mContext, R.drawable.first_course_marker_inactive));
        } else {
            holder.courseView1.setMarker(getDrawable(mContext, R.drawable.first_course_marker));
        }
    }

    /**
     * Updates markers for course one according to specific colors
     * @param holder The viewholder for the view element
     * @param dEvent The current set to be updated
     */
    private void updateCourse2Markers(ViewHolder holder, DeadlineEventSet dEvent){
        if(!dEvent.getD2().isDone()) {
            holder.courseView2.setMarker(getDrawable(mContext, R.drawable.second_course_marker_inactive));
        } else  {
            holder.courseView2.setMarker(getDrawable(mContext, R.drawable.second_course_marker));
        }
    }

    /**
     * Help method for updateCourseMarkers
     * @param context
     * @param drawableResId
     * @return
     */
    public static Drawable getDrawable(Context context, int drawableResId) {
        Drawable drawable;

        if (true) {
            drawable = context.getResources().getDrawable(drawableResId, context.getTheme());
        } else {
            drawable = VectorDrawableCompat.create(context.getResources(), drawableResId, context.getTheme());
        }

        return drawable;
    }

    /**
     * Returns the view type at a specific position
     */
    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position,getItemCount());
    }

    /**
     * Returns the size of the list in this adapter
     */
    @Override
    public int getItemCount() {
        return data.size();
    }

}
