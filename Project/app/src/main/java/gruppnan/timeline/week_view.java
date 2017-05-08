package gruppnan.timeline;

import java.util.Calendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import gruppnan.timeline.view.week_view_fragment;

import static gruppnan.timeline.model.dateCalc.*;


public class week_view extends Fragment implements View.OnClickListener{
    private week_view_fragment wwf;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view  = inflater.inflate(R.layout.fragment_week_view, container, false);
        TableLayout tl = (TableLayout) view.findViewById(R.id.weekView);
        View.OnClickListener on = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };
        wwf =new week_view_fragment(view.getContext(),tl,this);
        wwf.setWeekDatesText(getCurrentWeekDates());
        wwf.createTable();

        return view;
    }


    @Override
    public void onClick(View v) {
        TextView clickedCell = (TextView) v;
       Log.d("EventDbg","apan sover");
        if(clickedCell.getId() == R.id.nxtWeek|| clickedCell.getId() == R.id.prevWeek) {
            Log.d("EventDbg",((Calendar)clickedCell.getTag()).toString());
            wwf.setWeekDatesText(getWeekDates((Calendar)clickedCell.getTag()));
        }
        if(clickedCell.getId() == R.id.cell){
             clickedCell.setText((String)clickedCell.getTag());
        }

    }
}
