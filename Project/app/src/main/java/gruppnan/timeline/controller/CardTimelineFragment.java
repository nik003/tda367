package gruppnan.timeline.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import gruppnan.timeline.R;
import gruppnan.timeline.model.DeadlineEvent;
import gruppnan.timeline.model.EventContainer;

/**
 * Created by Melina on 05/05/2017.
 */

public class CardTimelineFragment extends Fragment {

    private TextView dateText, titleText, descriptionText;
    private ImageView doneMarker, exitMarker;
    private RadioButton radioButton;
    private int id,courseNumber;
    private String title, date, description;
    EventContainer eventContainer;
    DeadlineEvent event;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.card_timeline,
                container, false);
        initViewElements(view);
        initContent();
        initListeners();
        return view;
    }


    private void initViewElements(View view) {
        id = getArguments().getInt("ID", 0);
        courseNumber = getArguments().getInt("Course", 0);
        radioButton = (RadioButton) view.findViewById(R.id.radiobutton);
        doneMarker = (ImageView) view.findViewById(R.id.done_icon);
        exitMarker = (ImageView) view.findViewById(R.id.exit_icon);
        dateText = (TextView) view.findViewById(R.id.date1_timeline);
        titleText = (TextView) view.findViewById(R.id.text1_timeline);
        descriptionText = (TextView) view.findViewById(R.id.description_timeline);
        descriptionText.setFocusable(true);
        descriptionText.setEnabled(true);
        descriptionText.setClickable(true);
        descriptionText.setFocusableInTouchMode(true);
    }

    public void initContent(){
        Map<Integer,DeadlineEvent> events = new HashMap<>();
        eventContainer = EventContainer.getEventContainer();
        events = eventContainer.getDeadlineEventMap();
        for(Map.Entry<Integer, DeadlineEvent> entry : events.entrySet()){
            if(this.id == entry.getKey()){
                event = entry.getValue();
                date = event.getDayofMonth() + " " + event.getMonthAsString();
                title = event.getName();
                description = event.getDescription();
            }
        }
        setTexts();
    }

    public void setTexts(){
        dateText.setText(date);
        titleText.setText(title);
        descriptionText.setText(description);
    }

    private void initListeners() {
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

        radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    event.setDone(true);
                } else {
                    event.setDone(false);
                }
            }

        });

    }

    public void removeFragment(){

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.remove(this);
        fragmentTransaction.commit();

        fragmentManager.beginTransaction().replace(R.id.frame, new ContentTimelineFragment()).commit();


    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}
