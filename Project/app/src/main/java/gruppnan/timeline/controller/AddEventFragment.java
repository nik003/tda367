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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import gruppnan.timeline.model.Course;
import gruppnan.timeline.model.CourseRepository;
import gruppnan.timeline.model.EventRepository;
import gruppnan.timeline.view.AddEventView;

/**
 * @author Hannes
 * Controller class that makes it possible to create events. Initializes AddEventView and gets user
 * input from said view class.
 */

public class AddEventFragment extends Fragment implements TimePickerDialog.OnTimeSetListener,
        View.OnClickListener, AdapterView.OnItemSelectedListener, View.OnFocusChangeListener {

    private EventRepository eventRepository;
    private CourseRepository courseRepository;
    private AddEventView addEventView;


    private int startHour =12, startMinute=0, endHour=13, endMinute=0;
    private String startTimeStr, endTimeStr;
    private String eventName, eventDesc;
    private String eventType;
    private String selectedCourseStr;
    private Long yearMonthDayLong;
    private int eventID;
    private int year,month,day;
    private Date yearMonthDay, completeStartDate, completeEndDate;
    private Calendar calendar = Calendar.getInstance();
    private ArrayAdapter<String> spinnerAdapater;
    private List <String> courseListStr = new ArrayList<>();
    private HashSet<Course> courseList = new HashSet<>();
    private Iterator<Course> courseIterator;

    private Course course;


    public AddEventFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventRepository = EventRepository.getEventRepository();
        courseRepository = CourseRepository.getCourseRepository();
        //setUpCourseLists();

    }
    /** Set up view according to the type of event user wants to add */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getData();
        setUpCourseLists(); //important to init before addEventView
        addEventView = new AddEventView(inflater, container,this);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setTimeBtnTexts();
        setSpinnerAdapter();
        setListeners();
        customizeView();


        return addEventView.getView();

    }
    private void setSpinnerAdapter(){
        spinnerAdapater = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, courseListStr);
        spinnerAdapater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        addEventView.getCourseSpinner().setAdapter(spinnerAdapater);
    }


    private void setListeners(){
        addEventView.getSaveEventBtn().setOnClickListener(this);
        addEventView.getEndTimeBtn().setOnClickListener(this);
        addEventView.getStartTimeBtn().setOnClickListener(this);
        addEventView.getCourseSpinner().setOnItemSelectedListener(this);
        addEventView.getNameTxt().setOnFocusChangeListener(this);
        addEventView.getDescTxt().setOnFocusChangeListener(this);
    }

    private void customizeView(){

        String name = getArguments().getString("name");
        String desc = getArguments().getString("description");
        String eventType = getArguments().getString("type");
        String course = getArguments().getString("course");
        if (course!=null){
            addEventView.getCourseSpinner().setSelection(courseListStr.indexOf(course));
        }
        addEventView.setUpText(name,desc,eventType);
    }

    /** removes this fragment which is placed on top of CalendarFragment and MonthCalendarView*/
    private void removeFragment(){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(this).commit();

    }

    /** list for course spinner */
    private void setUpCourseLists(){
        courseList.addAll(courseRepository.getAllCourses());

        courseIterator = courseList.iterator();
        courseListStr.add("Course");
        while(courseIterator.hasNext()){
            courseListStr.add(courseIterator.next().getCourseID());
        }

    }

    /** get user choices from previous fragment */
    private void getData(){
        eventType = getArguments().getString("type");
        yearMonthDayLong = getArguments().getLong("date");
        eventID = getArguments().getInt("id");


        /** if editing already created event, set correct info */
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

    /** saves time for event that user chooses with timePicker widget located in MonthCalendarView */
    @Override
    public void onTimeSet(TimePicker view, int hour, int minute) {

        if (addEventView.getWhichButton().equals(addEventView.getStartTimeBtn())){
            startHour = hour;
            startMinute = minute;
            setTimeBtnTexts();
        }else if (addEventView.getWhichButton().equals(addEventView.getEndTimeBtn())){
            endHour = hour;
            endMinute = minute;
            setTimeBtnTexts();
        }
    }

    private void setTimeBtnTexts(){
        startTimeStr = (startHour <10 ? "0" : "")+ startHour + " : " + (startMinute <10 ? "0" : "")+ startMinute;
        addEventView.getStartTimeBtn().setText(startTimeStr);
        endTimeStr = (endHour <10 ? "0" : "")+ endHour + " : " + (endMinute <10 ? "0" : "")+ endMinute;
        addEventView.getEndTimeBtn().setText(endTimeStr);
    }

    /** identifies which button has been clicked and acts accordingly */
    @Override
    public void onClick(View view) {
        if (view.equals(addEventView.getStartTimeBtn())){
            addEventView.getStartTimePicker().show();
            addEventView.setWhichButton(addEventView.getStartTimeBtn());
        }else if (view.equals(addEventView.getEndTimeBtn())){
            addEventView.getEndTimePicker().show();
            addEventView.setWhichButton(addEventView.getEndTimeBtn());
        }else if (view.equals(addEventView.getSaveEventBtn())){
            getEventInfo();

            if (eventID!=0){
                eventRepository.removeEvent(eventID);

            }
            createEvent();

        }
    }
    /** uses eventContainer to create new event/deadline. Closes fragment if successful*/
    private void createEvent(){
        if (eventName.equals("")){
            addEventView.userNeedsToEnterName();

        } else if (eventType.equals("event")){

            eventRepository.createDefaultEvent(course,eventName,eventDesc,completeStartDate,completeEndDate);
            removeFragment();
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
        else if (eventType.equals("deadline")){
            eventRepository.createDeadlineEvent(course,eventName,eventDesc,completeEndDate,false);
            removeFragment();
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();

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
        getCourse();


        calendar.set(year,month,day, endHour, endMinute);
        completeEndDate = calendar.getTime();


    }

    /** gets user selected course */
    private void getCourse(){

        for (Course c : courseList){
            if (c.getCourseID().equalsIgnoreCase(selectedCourseStr)){
                course = c;
            }else{
             course=null;
            }
        }
    }

    /** listener for course spinner */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        selectedCourseStr = adapterView.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /** disengages from UI elements when user changes focus */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus){
            hideKeyboard(v);
        }
    }
    private void hideKeyboard(View view){
        InputMethodManager inputMethodManager =(InputMethodManager)getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
