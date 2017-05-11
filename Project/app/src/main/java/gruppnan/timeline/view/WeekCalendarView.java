package gruppnan.timeline.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


import gruppnan.timeline.R;
import gruppnan.timeline.model.DeadlineEvent;
import gruppnan.timeline.model.DefaultEvent;
import gruppnan.timeline.model.Event;
import gruppnan.timeline.model.EventContainer;


/**
 * Created by Nikolai on 2017-05-04.
 */

public class WeekCalendarView {
    private Context context;
    private TableLayout tl;
    private LayoutInflater inflater;
    private View.OnClickListener onCl;

    public WeekCalendarView(Context c, TableLayout tl, View.OnClickListener onCl){
        context = c;
        this.tl = tl;
        this.onCl = onCl;

    }


    private void createWeekView(){
        TextView tv;

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

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
    private TableRow createRow(int i){
        TableRow tr = new TableRow(context);
        View txtCell = inflater.inflate(R.layout.textcell,null);
        TextView tv = (TextView) txtCell.findViewById(R.id.cell);
        tv.setText(i + ":00");
        tr.addView(tv);
        for (int j = 0; j < 7; j++) {
            tr.addView(createCell(tr,i,j));
        }



        return tr;



    }
    private TextView createCell(TableRow tr,int row,int col){
        View txtCell;
        int cellNo = ((row)*7)+(col+1);
        txtCell = inflater.inflate(R.layout.textcell,null);
        TextView tv = (TextView) txtCell.findViewById(R.id.cell);
        tv.setTag(cellNo + "");
        tv.setOnClickListener(onCl);
        tv.setClickable(true);
        return tv;

    }
    public void updateView(Calendar[] dates){
        String fromDate = dates[0].get(Calendar.DAY_OF_MONTH)+"/" + (dates[0].get(Calendar.MONTH)+1);
        String toDate = dates[1].get(Calendar.DAY_OF_MONTH)+"/" + (dates[1].get(Calendar.MONTH)+1);
        TextView dateView  = (TextView)tl.findViewById(R.id.tableDate);
        List<Event> events = EventContainer.getEventContainer().getEventsByDates(dates[0],dates[1]);

        dateView.setText(fromDate+ "-" + toDate);

        dateView  = (TextView)tl.findViewById(R.id.weekText);
        dateView.setText("W "+ dates[0].get(Calendar.WEEK_OF_YEAR));

        dateView  = (TextView)tl.findViewById(R.id.nxtWeek);
        dateView.setTag(dates[2]);

        dateView  = (TextView)tl.findViewById(R.id.prevWeek);
        dateView.setTag(dates[3]);
        renderEvents(events);
    }
    public void renderEvents(List<Event> events){
        clearTable();
        for(Event e : events){
            Calendar endDate = e.dateToCalendar(e.getEndDate());


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

                    cell = (TextView) tl.findViewWithTag((cellNum + ((startDate.get(startDate.HOUR_OF_DAY)) * 7)) + "");
                    if (cell == null) {
                        Log.d("ERROR", cellNum + ((startDate.get(startDate.HOUR_OF_DAY)) * 7) + ", " + startDate.get(startDate.HOUR_OF_DAY) + ", " + startDate.get(startDate.DAY_OF_WEEK));
                    }
                    if (cell != null) {
                        cell.setBackgroundResource(R.color.cellcolor);
                    }

                    startDate.add(startDate.HOUR_OF_DAY, 1);

                    if (startDate.get(startDate.HOUR_OF_DAY) > 23) {
                        Log.d("stuff", startDate.get(startDate.HOUR_OF_DAY) + " " + startDate.get(startDate.DAY_OF_MONTH));

                    }
                }
            }else{
                int cellNum = endDate.get(endDate.DAY_OF_WEEK) - 1;
                TextView cell = (TextView) tl.findViewWithTag((cellNum + ((endDate.get(endDate.HOUR_OF_DAY)) * 7)) + "");
                if (cell != null) {
                    cell.setBackgroundResource(R.color.cellcolor);
                }
            }
        }
    }
    private void clearTable(){
        for (int i = 1; i<=168;i++){
            TextView v = (TextView)tl.findViewWithTag(i+"");
            if(v!=null) {
                v.setBackgroundResource(R.drawable.cell_shape);
                v.setText("");
            }

        }



    }
}

