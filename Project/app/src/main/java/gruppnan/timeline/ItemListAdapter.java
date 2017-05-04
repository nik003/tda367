package gruppnan.timeline;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.vipulasri.timelineview.TimelineView;

import java.util.List;

import gruppnan.timeline.model.DeadlineEvent;

/**
 * Created by Melina on 02/05/2017.
 */

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {


    private List<DeadlineEvent> data;
    private Context mContext;

    public ItemListAdapter(List<DeadlineEvent> deadlineEvents) {
        this.data= deadlineEvents;
    }


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView dateText, titleText;
        TimelineView mTimelineView;

        public ViewHolder(View v, int viewType) {
            super(v);
            mTimelineView = (TimelineView) itemView.findViewById(R.id.time_marker);
            mTimelineView.initLine(viewType);
            dateText = (TextView) v.findViewById(R.id.date1_timeline);
            titleText = (TextView) v.findViewById(R.id.text1_timeline);
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
                // set the view's size, margins, paddings and layout parameters
                ViewHolder vh = new ViewHolder(v,viewType);
                return vh;

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that
        DeadlineEvent dEvent = data.get(position);

        if(!dEvent.isDone()){
            holder.mTimelineView.setMarker(getDrawable(mContext, R.drawable.marker_inactive));
        } else {

            holder.mTimelineView.setMarker(getDrawable(mContext, R.drawable.marker));
        }

        holder.dateText.setText(dEvent.getDate());
        holder.titleText.setText(dEvent.getName());

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

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return data.size();
    }
}
