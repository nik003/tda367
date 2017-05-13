package gruppnan.timeline.controller;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import gruppnan.timeline.R;
import gruppnan.timeline.model.DeadlineEvent;
import gruppnan.timeline.model.EventContainer;

/**
 * Created by Melina on 05/05/2017.
 */

public class CardTimelineFragment extends Fragment {

    private EditText titleText, descriptionText;
    private TextView dateText;
    private ImageView exitMarker;
    private Button saveButton;
    private CheckBox checkBox;
    private int id,courseNumber;
    private String title, date, description;
    EventContainer eventContainer;
    DeadlineEvent event;

    final Calendar calendar = Calendar.getInstance();

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
        checkBox = (CheckBox) view.findViewById(R.id.checkbox);
        saveButton = (Button) view.findViewById(R.id.done_icon);
        exitMarker = (ImageView) view.findViewById(R.id.exit_icon);
        dateText = (TextView) view.findViewById(R.id.date_timeline);
        titleText = (EditText) view.findViewById(R.id.title_timeline);
        descriptionText = (EditText) view.findViewById(R.id.description_timeline);
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
        //If deadline is done, set checkbox marked
        if(event.isDone()){
            checkBox.toggle();
        }
    }

    public void setTexts(){
        dateText.setText(date);
        titleText.setText(title);
        descriptionText.setText(description);
    }

    private void initListeners() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                event.setName(titleText.getText().toString());
                event.setDescription(descriptionText.getText().toString());
                removeFragment();
                hideKeyboard();
            }
        });

        exitMarker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeFragment();
                hideKeyboard();
            }
        });

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((checkBox.isChecked() && !event.isDone())){
                    event.setDone(true);
                } else if(checkBox.isChecked() && event.isDone()) {
                    event.setDone(false);
                } else {
                    event.setDone(false);
                }
            }
        });


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDate();

            }

        };

        dateText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getContext(), date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



    }

    private void updateDate() {
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
        String date = day + " " + month;
        dateText.setText(date);
        event.setEndDate(DeadlineEvent.toDate(calendar));
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

    private void hideKeyboard(){
        InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }


}
