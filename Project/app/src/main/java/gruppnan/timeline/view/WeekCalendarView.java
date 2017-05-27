package gruppnan.timeline.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;


import gruppnan.timeline.R;
import gruppnan.timeline.model.DefaultEvent;
import gruppnan.timeline.model.Event;
import gruppnan.timeline.model.EventRepository;
import gruppnan.timeline.model.WeekDates;
import gruppnan.timeline.model.WeekEventClickData;


/**
 * Created by Nikolai on 2017-05-04.
 * The view for the Week View in the calendar section
 */

public class WeekCalendarView {
    private Context context;
    private TableLayout tl;
    private RelativeLayout rl;
    private LayoutInflater inflater;
    private View.OnClickListener onCl;

    View latestView;

    public WeekCalendarView(Context c, RelativeLayout rl, View.OnClickListener onCl){
        context = c;
        this.rl = rl;
        this.onCl = onCl;

    }

    /**
     * Configures the first row and begins creating the table layout
     */
    private void createWeekView(){
        TextView tv;
        Button btn;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        tl = (TableLayout)rl.findViewById(R.id.weekView);
        btn = (Button) tl.findViewById(R.id.monthViewBtn);
        btn.setOnClickListener(onCl);
        tv  = (TextView)tl.findViewById(R.id.nxtWeek);
        tv.setClickable(true);
        tv.setOnClickListener(onCl);
        tv  = (TextView)tl.findViewById(R.id.prevWeek);
        tv.setClickable(true);
        tv.setOnClickListener(onCl);


        for(int i = 0;i<24;i++)
             tl.addView(createRow(i));



    }
    public void createTable(){
        createWeekView();
    }

    /**
     * Shows the view with information about an event
     * @param e The event which information should be shown
     */
    public void showEvent(Event e){
        latestView= rl.getChildAt(0);
        rl.removeAllViews();
        LinearLayout eventViewRoot = (LinearLayout) inflater.inflate(R.layout.eventviewer,null);
        EventViewer ev = new EventViewer(eventViewRoot,onCl,e);
        ev.renderView();
        rl.addView(eventViewRoot);
    }

    /**
     * Hides the event viewer and brings back the Week View
     */
    public void hideEvent(){
        rl.removeAllViews();
        rl.addView(latestView);

    }

    /**
     * Creates row i in order
     * @param i the row to be created
     * @return the created row
     */

    private TableRow createRow(int i){
        TableRow tr = new TableRow(context);
        View txtCell = inflater.inflate(R.layout.textcell,null);
        TextView tv = (TextView) txtCell.findViewById(R.id.cell);
        tv.setText(i + ":00");
        tr.addView(tv);
        for (int j = 0; j < 7; j++) {
            tr.addView(createCell(i,j));
        }

        return tr;
    }

    /**
     * Creates a cell in the table
     * @param row The row in which the cell is to be added
     * @param col The column in which the cell is to be added
     * @return     The Cell, as a textView
     */
    private TextView createCell(int row,int col){
        View txtCell;
        int cellNo = ((row)*7)+(col+1);
        txtCell = inflater.inflate(R.layout.textcell,null);
        TextView tv = (TextView) txtCell.findViewById(R.id.cell);
        tv.setTag(new WeekEventClickData(null,cellNo));
        tv.setOnClickListener(onCl);
        tv.setClickable(true);
        return tv;

    }

    /**
     * Updates the week view to show from certain dates
     * @param dates The Dates which the view should update from
     */
    public void updateView(WeekDates dates){

        String fromDate = dates.getThisMonday().get(Calendar.DAY_OF_MONTH)+"/" + (dates.getThisMonday().get(Calendar.MONTH)+1);
        String toDate = dates.getThisSunday().get(Calendar.DAY_OF_MONTH)+"/" + (dates.getThisSunday().get(Calendar.MONTH)+1);
        TextView dateView  = (TextView)tl.findViewById(R.id.tableDate);
        List<Event> events = EventRepository.getEventRepository().getEventsByDates(dates.getThisMonday(),dates.getThisSunday());

        dateView.setText(fromDate+ "-" + toDate);

        dateView  = (TextView)tl.findViewById(R.id.weekText);
        dateView.setText("W "+ dates.getThisMonday().get(Calendar.WEEK_OF_YEAR));

        dateView  = (TextView)tl.findViewById(R.id.nxtWeek);
        dateView.setTag(dates.getNextMonday());

        dateView  = (TextView)tl.findViewById(R.id.prevWeek);
        dateView.setTag(dates.getPrevMonday());
        renderEvents(events);
    }

    /**
     * Renders the events in the week view
     * @param events the events to be rendered
     */
    public void renderEvents(List<Event> events){
        clearTable();
        for(Event e : events){
            Calendar endDate = e.dateToCalendar(e.getEndDate());
            Log.d("Event",e.getName());

            if(endDate.get(endDate.MINUTE)>=30) {
                endDate.add(Calendar.HOUR_OF_DAY, 1);

            }
            if(e instanceof DefaultEvent) {
                Calendar startDate = e.dateToCalendar(((DefaultEvent)e).getStartDate());
                if (startDate.get(startDate.MINUTE) >= 30) {
                    startDate.add(Calendar.HOUR_OF_DAY, 1);

                }
                TextView cell;
                while (startDate.before(endDate)) {
                    int cellNum = startDate.get(startDate.DAY_OF_WEEK) - 1;
                    // Log.d("stuff", startDate.get(startDate.HOUR_OF_DAY)+ " "+ startDate.get(startDate.DAY_OF_MONTH));

                    cell = (TextView) tl.findViewWithTag(new WeekEventClickData(null,(cellNum + ((startDate.get(startDate.HOUR_OF_DAY)) * 7))));
                    if (cell == null) {
                        Log.d("ERROR", cellNum + ((startDate.get(startDate.HOUR_OF_DAY)) * 7) + ", " + startDate.get(startDate.HOUR_OF_DAY) + ", " + startDate.get(startDate.DAY_OF_WEEK));
                    }
                    if (cell != null) {
                        WeekEventClickData eventData =(WeekEventClickData) cell.getTag();
                        eventData.setEvent(e);

                        if(e.getCourse()!=null) {
                            cell.setText(e.getCourse().getCourseID());
                        }else{
                            cell.setBackgroundResource(R.color.cellcolor);
                        }

                        cell.setTag(eventData);


                    }

                    startDate.add(startDate.HOUR_OF_DAY, 1);

                    if (startDate.get(startDate.HOUR_OF_DAY) > 23) {
                        Log.d("stuff", startDate.get(startDate.HOUR_OF_DAY) + " " + startDate.get(startDate.DAY_OF_MONTH));

                    }
                }
            }else{
                int cellNum = endDate.get(endDate.DAY_OF_WEEK) - 1;
                TextView cell = (TextView) tl.findViewWithTag(new WeekEventClickData(null,(cellNum + ((endDate.get(endDate.HOUR_OF_DAY)) * 7))));
                if (cell != null) {
                    WeekEventClickData cellData =(WeekEventClickData)cell.getTag();
                    cellData.setEvent(e);
                    if(e.getCourse()!=null) {
                        cell.setText(e.getCourse().getCourseID());
                    }else{
                        cell.setBackgroundResource(R.color.cellcolor);
                    }

                    cell.setTag(cellData);
                }
            }
        }
    }

    /**
     * Clears the Week View Table
     */
    private void clearTable(){
        for (int i = 1; i<=168;i++){
            TextView v = (TextView)tl.findViewWithTag(new WeekEventClickData(null,i));
            if(v!=null) {
                v.setBackgroundResource(R.drawable.cell_shape);
                v.setText("");
            }

        }



    }
}

