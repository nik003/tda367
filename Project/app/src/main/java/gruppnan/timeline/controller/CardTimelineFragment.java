package gruppnan.timeline.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import gruppnan.timeline.R;
import gruppnan.timeline.model.DeadlineEventSet;

/**
 * Created by Melina on 05/05/2017.
 */

public class CardTimelineFragment extends Fragment {

    private TextView dateText, titleText;
    private ImageView doneMarker, exitMarker;
    private int pos,courseNumber, date;
    private String title;
    List<DeadlineEventSet> list;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.card_timeline,
                container, false);
        pos = getArguments().getInt("Position", 0);
        courseNumber = getArguments().getInt("Course", 0);
        doneMarker = (ImageView) view.findViewById(R.id.done_icon);
        exitMarker = (ImageView) view.findViewById(R.id.exit_icon);
        dateText = (TextView) view.findViewById(R.id.date1_timeline);
        titleText = (TextView) view.findViewById(R.id.text1_timeline);
        initContent();
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public void initContent(){
        doneMarker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //update deadlinevent data here
                removeFragment();
            }
        });

        exitMarker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeFragment();
            }
        });

        /*

        DeadlineEventSet d = list.get(pos);

        if(courseNumber == 1){
            date = d.getD1().getDate();
            title = d.getD1().getName();
        } else {
            date = d.getD2().getDate();
            title = d.getD2().getName();
        }

        setTexts();
        */

    }

    public void setTexts(){
        dateText.setText(date);
        titleText.setText(title);
    }

    public void removeFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.remove(this);
        fragmentTransaction.commit();
    }
}
