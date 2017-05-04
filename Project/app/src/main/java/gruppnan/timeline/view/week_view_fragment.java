package gruppnan.timeline.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParser;

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

        View txtCell;
        TableRow tr;
        TextView tv;

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        /*
        tv = (TextView) txtCell.findViewById(R.id.cell);
        tv.setText("Back");
        tr.addView(tv);

        txtCell = inflater.inflate(R.layout.textcell,null);
        tv = (TextView) txtCell.findViewById(R.id.cell);
        tv.setText("Week 27");
        tr.addView(tv);

        txtCell = inflater.inflate(R.layout.textcell,null);
        tv = (TextView) txtCell.findViewById(R.id.cell);
        tv.setText("Next");
        tr.addView(tv);
        tl.addView(tr);
        */
        tr = new TableRow(context);
        tr.setId(View.generateViewId());
        txtCell = inflater.inflate(R.layout.textcell,null);
        tv = (TextView) txtCell.findViewById(R.id.cell);
        tv.setText("W 27");
        tr.addView(tv);

        txtCell = inflater.inflate(R.layout.textcell,null);
        tv = (TextView) txtCell.findViewById(R.id.cell);
        tv.setText("Mon");
        tr.addView(tv);

        txtCell = inflater.inflate(R.layout.textcell,null);
        tv = (TextView) txtCell.findViewById(R.id.cell);
        tv.setText("Tue");
        tr.addView(tv);

        txtCell = inflater.inflate(R.layout.textcell,null);
        tv = (TextView) txtCell.findViewById(R.id.cell);
        tv.setText("Wed");
        tr.addView(tv);

        txtCell = inflater.inflate(R.layout.textcell,null);
        tv = (TextView) txtCell.findViewById(R.id.cell);
        tv.setText("Thu");
        tr.addView(tv);

        txtCell = inflater.inflate(R.layout.textcell,null);
        tv = (TextView) txtCell.findViewById(R.id.cell);
        tv.setText("Fri");
        tr.addView(tv);

        txtCell = inflater.inflate(R.layout.textcell,null);
        tv = (TextView) txtCell.findViewById(R.id.cell);
        tv.setText("Sat");
        tr.addView(tv);

        txtCell = inflater.inflate(R.layout.textcell,null);
        tv = (TextView) txtCell.findViewById(R.id.cell);
        tv.setText("Sun");
        tr.addView(tv);
        tl.addView(tr);
       /* tr.setGravity(Gravity.CENTER);
        tv.setClickable(true);
        tv.setText("<<");
        tv.setBackgroundResource(R.drawable.cell_shape);
        tv.setPadding(3,3,3,3);

        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,TableRow.LayoutParams.FILL_PARENT,1.0f));
        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tr.addView(tv);


        tv = new TextView(context);
        tv.setClickable(true);
        tv.setText("Week 27");
        tv.setBackgroundResource(R.drawable.cell_shape);
        tv.setPadding(3,3,3,3);
        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tr.addView(tv);


        tv = new TextView(context);
        tv.setClickable(true);
        tv.setText(">>");
        tv.setBackgroundResource(R.drawable.cell_shape);
        tv.setPadding(3,3,3,3);

        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tr.addView(tv);*/

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
}

