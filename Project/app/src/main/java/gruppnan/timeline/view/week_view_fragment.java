package gruppnan.timeline.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


import gruppnan.timeline.R;


/**
 * Created by Nikolai on 2017-05-04.
 */

public class week_view_fragment {
    private Context context;
    private TableLayout tl;
    private LayoutInflater inflater;
    private View.OnClickListener onCl;

    public week_view_fragment(Context c, TableLayout tl, View.OnClickListener onCl){
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
    public void setWeekDatesText(Calendar[] dates){
        String fromDate = dates[0].get(Calendar.DAY_OF_MONTH)+"/" + (dates[0].get(Calendar.MONTH)+1);
        String toDate = dates[1].get(Calendar.DAY_OF_MONTH)+"/" + (dates[1].get(Calendar.MONTH)+1);
        TextView dateView  = (TextView)tl.findViewById(R.id.tableDate);
        dateView.setText(fromDate+ "-" + toDate);

        dateView  = (TextView)tl.findViewById(R.id.weekText);
        dateView.setText("W "+ dates[0].get(Calendar.WEEK_OF_YEAR));

        dateView  = (TextView)tl.findViewById(R.id.nxtWeek);
        dateView.setTag(dates[2]);

        dateView  = (TextView)tl.findViewById(R.id.prevWeek);
        dateView.setTag(dates[3]);
    }
    private void renderEvents(HashMap<String,Calendar[]> event){
        for(Map.Entry<String,Calendar[]> e: event.entrySet()){
            String name  = e.getKey();
            Calendar startDate = e.getValue()[0];
            Calendar endDate = e.getValue()[1];
            int hour;
            if(startDate.get(startDate.MINUTE)>=30){
                startDate.add(Calendar.HOUR_OF_DAY,1);

            }
            if(endDate.get(startDate.MINUTE)>=30) {
                endDate.add(Calendar.HOUR_OF_DAY, 1);

            }
            
        }

    }


}

