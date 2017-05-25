package gruppnan.timeline.controller;


import android.app.TimePickerDialog;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import gruppnan.timeline.model.Course;
import gruppnan.timeline.model.EventContainer;
import gruppnan.timeline.view.AddEventView;


public class AddEventFragment extends Fragment implements TimePickerDialog.OnTimeSetListener, View.OnClickListener{

    private EventContainer eventContainer;
    private AddEventView addEventView;


    private int startHour =12, startMinute=0, endHour=13, endMinute=0;
    private String startTimeStr, endTimeStr;
    private String eventName, eventDesc;
    private String eventType;
    private Long yearMonthDayLong;
    private int eventID;
    private int year,month,day;
    private Date yearMonthDay, completeStartDate, completeEndDate;
    private Calendar calendar = Calendar.getInstance();

    //TODO fix correct course implementation
    private Course course = new Course("course1", null);
    private TimePickerDialog endTimePicker,startTimePicker;

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
        getData();

        addEventView = new AddEventView(inflater, container,this);
        startTimeStr = (startHour <10 ? "0" : "")+ startHour + " : " + (startMinute <10 ? "0" : "")+ startMinute;
        addEventView.getStartTimeBtn().setText(startTimeStr);
        endTimeStr = (endHour <10 ? "0" : "")+ endHour + " : " + (endMinute <10 ? "0" : "")+ endMinute;
        addEventView.getEndTimeBtn().setText(endTimeStr);

        eventContainer = EventContainer.getEventContainer();
        return addEventView.getView();

    }




    private void removeFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.remove(this);
        fragmentTransaction.commit();
    }





    /** get user choices from previous fragment */
    private void getData(){
        eventType = getArguments().getString("type");
        yearMonthDayLong = getArguments().getLong("date");
        eventID = getArguments().getInt("id");

        /** if editing already created event */
        if (eventID!=0){
            year = getArguments().getInt("year");
            month = getArguments().getInt("month");
            day = getArguments().getInt("day");
            endHour = getArguments().getInt("endHour");
            endMinute = getArguments().getInt("endMinute");


            startHour = getArguments().getInt("startHour");
            startMinute = getArguments().getInt("startMinute");



            calendar.set(year,month,day);
            yearMonthDay = calendar.getTime();


        }else{

            yearMonthDay = new Date(yearMonthDayLong);
        }

    }

    /** saves event time that user chooses with timePicker widget */
    @Override
    public void onTimeSet(TimePicker view, int hour, int minute) {

        if (addEventView.getWhichButton().equals(addEventView.getStartTimeBtn())){
            startHour = hour;
            startMinute = minute;
            startTimeStr = (startHour <10 ? "0" : "")+ startHour + " : " + (startMinute <10 ? "0" : "")+ startMinute;
            addEventView.getStartTimeBtn().setText(startTimeStr);
        }else if (addEventView.getWhichButton().equals(addEventView.getEndTimeBtn())){
            endHour = hour;
            endMinute = minute;
            endTimeStr = (endHour <10 ? "0" : "")+ endHour + " : " + (endMinute <10 ? "0" : "")+ endMinute;
            addEventView.getEndTimeBtn().setText(endTimeStr);
        }
    }

    /** identifies which button has been clicked and acts accordingly */
    @Override
    public void onClick(View view) {
        if (view.equals(addEventView.getStartTimeBtn())){
            startTimePicker = addEventView.getStartTimePicker();
            startTimePicker.show();
            addEventView.setWhichButton(addEventView.getStartTimeBtn());
        }else if (view.equals(addEventView.getEndTimeBtn())){
            endTimePicker = addEventView.getEndTimePicker();
            endTimePicker.show();
            addEventView.setWhichButton(addEventView.getEndTimeBtn());
        }else if (view.equals(addEventView.getSaveEventBtn())){
            getEventInfo();

            if (eventID!=0){
                eventContainer.removeEvent(eventID);

            }
            createEvent();

        }
    }

    private void createEvent(){
        if (eventName.equals("")){
            addEventView.userNeedsToEnterName();
        } else if (eventType.equals("event")){

            eventContainer.createDefaultEvent(course,eventName,eventDesc,completeStartDate,completeEndDate);
            removeFragment();
        }
        else if (eventType.equals("deadline")){
            eventContainer.createDeadlineEvent(course,eventName,eventDesc,completeEndDate,false);
            removeFragment();
        }
    }

    /** get user entered event settings from view */
    private void getEventInfo(){
        eventName = addEventView.getEventName();
        eventDesc = addEventView.getEventDesc();

        calendar.setTime(yearMonthDay);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        calendar.set(year,month,day, startHour, startMinute);
        completeStartDate = calendar.getTime();


        calendar.set(year,month,day, endHour, endMinute);
        completeEndDate = calendar.getTime();

        addEventView.getSaveEventBtn().setText(completeStartDate.toString());
    }
}
