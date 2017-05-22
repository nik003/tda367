package gruppnan.timeline.controller;


import android.app.TimePickerDialog;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

import gruppnan.timeline.R;
import gruppnan.timeline.model.Course;
import gruppnan.timeline.model.EventContainer;


public class AddEventFragment extends Fragment implements TimePickerDialog.OnTimeSetListener, View.OnFocusChangeListener {




    private View view;

    private String eventType;
    private TextView titleTxt, nameTxt, descTxt, startTimeLbl;
    private Button startTimeBtn, endTimeBtn, saveEventBtn;

    private int nrHour, nrMinute;
    private int startHF=12, startMF=00, endHF=13, endMF=00;

    private Spinner courseSpinner;
    private Button whichButton;
    private String eventName, eventDescription, showEndTime, showStarTime;
    //TODO fix course when possible.
    private Course course = new Course("course1", null);
    private long dateL;
    private int year, month,day;
    private Date yearMonthDay, completeStartDate, completeEndDate;
    private Calendar calendar;
    private EventContainer eventContainer;
    private FragmentManager fragmentManager;




    TimePickerDialog startTimePicker, endTimePicker;




    public AddEventFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /** Set up view according to the type of event user wants to add */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        view = inflater.inflate(R.layout.fragment_add_event, container, false);
        setUpComponents(view);
        getData();
        customizeFragment(eventType);

        return view;
    }

    private void setUpComponents(View v){
        titleTxt= (TextView) v.findViewById(R.id.eventTitleLabel);
        startTimeBtn = (Button) v.findViewById(R.id.startTimeBtn);
        endTimeBtn = (Button) v.findViewById(R.id.endTimeBtn);
        saveEventBtn = (Button) v.findViewById(R.id.saveEventBtn);



        saveEventBtn.setOnClickListener(onClickListener);
        startTimeBtn.setOnClickListener(onClickListener);
        endTimeBtn.setOnClickListener(onClickListener);

        startTimeBtn.setText(startHF +" : " +"0"+ startMF);
        endTimeBtn.setText(endHF + " : " +"0"+ endMF);

        courseSpinner = (Spinner) v.findViewById(R.id.courseSpinner);
        courseSpinner.setPrompt("Choose course");

        nameTxt = (TextView) v.findViewById(R.id.eventNameTxt);
        descTxt = (TextView) v.findViewById(R.id.descTxt);
        nameTxt.setOnFocusChangeListener(this);
        descTxt.setOnFocusChangeListener(this);
        startTimeLbl = (TextView) v.findViewById(R.id.startTimeLbl);
    }

    /** get user data from previous fragment */
    private void getData(){
        eventType = getArguments().getString("type");
        dateL = getArguments().getLong("date");

        yearMonthDay = new Date(dateL);
        eventContainer = EventContainer.getEventContainer();
        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction();
    }


    /** Listener for buttons, time buttons included*/
    private View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            calendar = Calendar.getInstance();
            nrHour = calendar.get(Calendar.HOUR_OF_DAY);
            nrMinute = calendar.get(Calendar.MINUTE);
            
            if (v.equals(startTimeBtn)){
                startTimePicker = new TimePickerDialog(getActivity(), AddEventFragment.this, nrHour, nrMinute,true);
                startTimePicker.show();
                whichButton = startTimeBtn;
            }
            else if (v.equals(endTimeBtn)){
                endTimePicker = new TimePickerDialog(getActivity(),AddEventFragment.this, nrHour, nrMinute,true);
                endTimePicker.show();
                whichButton = endTimeBtn;
            }
            else if (v.equals(saveEventBtn)){

                getEventInfo();
                createEvent();

                removeFragment();


            }
        }
    };

    public void removeFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.remove(this);
        fragmentTransaction.commit();
    }

    /** checks if data is correctly entered, creates event or deadline */
    private void createEvent(){
        if (eventName.equals("")){
            nameTxt.setHint("Please set event name");
        }
        else{
            if (eventType.equals("event")){
                eventContainer.createDefaultEvent(course,eventName,eventDescription,completeStartDate,completeEndDate);
            }
            else if (eventType.equals("deadline")){
                eventContainer.createDeadlineEvent(course,eventName,eventDescription,completeEndDate,false);
            }


        }
    }

    public void hideKeyboard(View view){
        InputMethodManager inputMethodManager =(InputMethodManager)getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }



    /** set up different components depending on what type of event is chosen */
    private void customizeFragment(String eventType){
        if (eventType.equals("event")){
            titleTxt.setText("Add new event");

        }
        else if (eventType.equals("deadline")){
            titleTxt.setText("Add new deadline");
            nameTxt.setHint("Deadline name");
            startTimeLbl.setVisibility(view.INVISIBLE);
            startTimeBtn.setVisibility(View.INVISIBLE);
        }
    }

    /** sets and displays event start and end time of event */
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        if (whichButton.equals(startTimeBtn)){
            startHF = hourOfDay;
            startMF = minute;
            showStarTime = (startHF <10 ? "0" : "")+startHF + " : " + (startMF <10 ? "0" : "")+startMF ;
            startTimeBtn.setText(showStarTime);
        }
        else if (whichButton.equals(endTimeBtn)){
            endHF = hourOfDay;
            endMF = minute;
            showEndTime = (endHF <10 ? "0" : "")+endHF + " : " + (endMF <10 ? "0" : "")+endMF ;
            endTimeBtn.setText(showEndTime);
        }
    }

    /** various data the user decides to save with event */
    public void getEventInfo(){
        eventName = nameTxt.getText().toString();
        eventDescription = descTxt.getText().toString();

        calendar.setTime(yearMonthDay);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        calendar.set(year,month,day,startHF, startMF);
        completeStartDate = calendar.getTime();

        calendar.setTime(yearMonthDay);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        calendar.set(year,month,day,startHF, startMF);
        completeEndDate = calendar.getTime();


    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus){
            hideKeyboard(v);
        }
    }


}