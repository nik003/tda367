package gruppnan.timeline.controller;

import android.app.TimePickerDialog;

import java.sql.Time;
import java.text.DateFormat;
import java.util.Calendar;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import gruppnan.timeline.R;
import gruppnan.timeline.model.Course;
import gruppnan.timeline.model.DefaultEvent;



public class addEventFragment extends Fragment implements TimePickerDialog.OnTimeSetListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View v;

    private String eventType;
    private TextView titleTxt, nameTxt, descTxt;
    private Button startTimeBtn, endTimeBtn, saveEventBtn;

    private int startH, startM;
    private int startHF, startMF, endHF, endMF;
    private Boolean timePickBoolean;
    private Button whichButton;
    private String eventName, eventDescription;
    private Course corse;

    TimePickerDialog startTimePicker, endTimePicker;




    public addEventFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment addEventFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static addEventFragment newInstance(String param1) {
        addEventFragment fragment = new addEventFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    /** Set up view according to the type of event user wants to add */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        v = inflater.inflate(R.layout.fragment_add_event, container, false);
        setUpComponents(v);

        eventType = getArguments().getString("type");
        customizeFragment(eventType);
        return v;
    }

    private void setUpComponents(View v){
        titleTxt= (TextView) v.findViewById(R.id.eventTitleLabel);
        startTimeBtn = (Button) v.findViewById(R.id.startTimeBtn);
        endTimeBtn = (Button) v.findViewById(R.id.endTimeBtn);
        saveEventBtn = (Button) v.findViewById(R.id.saveEventBtn);
        saveEventBtn.setOnClickListener(onClickListener);
        startTimeBtn.setOnClickListener(onClickListener);
        endTimeBtn.setOnClickListener(onClickListener);


        nameTxt = (TextView) v.findViewById(R.id.eventNameTxt);
        descTxt = (TextView) v.findViewById(R.id.descTxt);

    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Calendar c = Calendar.getInstance();
            startH = c.get(Calendar.HOUR_OF_DAY);
            startM = c.get(Calendar.MINUTE);
            
            if (v.equals(startTimeBtn)){
                startTimePicker = new TimePickerDialog(getActivity(), addEventFragment.this, startH,startM,true);
                startTimePicker.show();
                whichButton = startTimeBtn;
            }
            else if (v.equals(endTimeBtn)){
                endTimePicker = new TimePickerDialog(getActivity(),addEventFragment.this, startH,startM,true);
                endTimePicker.show();
                whichButton = endTimeBtn;
            }
            else if (v.equals(saveEventBtn)){

            }

        }
    };


    private void customizeFragment(String type){
        if (type.equals("event")){
            titleTxt.setText("Add new event");

        }
        else if (type.equals("deadline")){
            titleTxt.setText("Add new deadline");
            nameTxt.setHint("Deadline name");
            startTimeBtn.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        if (whichButton.equals(startTimeBtn)){
            startHF = hourOfDay;
            startMF = minute;
            startTimeBtn.setText(startHF +" : " + startMF);
        }
        else if (whichButton.equals(endTimeBtn)){
            endHF = hourOfDay;
            endMF = minute;
            endTimeBtn.setText(endHF + " : " + endMF);
        }
    }

    public void getEventInfo(){
        eventName = nameTxt.getText().toString();
        eventDescription = descTxt.getText().toString();

    }
}
