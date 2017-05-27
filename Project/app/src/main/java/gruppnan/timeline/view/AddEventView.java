package gruppnan.timeline.view;


import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


import gruppnan.timeline.R;
import gruppnan.timeline.controller.AddEventFragment;

/**
 * Created by Hannes
 * Sets up view with components so user can enter wanted settings for creation of event. Used by
 * AddEventFragment which functions as a controller class.
 */

public class AddEventView{


    private View view;

    private TextView titleTxt, nameTxt, descTxt, startTimeLbl;
    private Button startTimeBtn, endTimeBtn, saveEventBtn, whichButton;
    private Spinner courseSpinner;
    private TimePickerDialog startTimePicker, endTimePicker;
    private Context context;
    private TimePickerDialog.OnTimeSetListener timeSetListener;


    public AddEventView(LayoutInflater layoutInflater, ViewGroup container, TimePickerDialog.OnTimeSetListener timeSetListener){
        view = layoutInflater.inflate(R.layout.fragment_add_event,container,false);

        this.timeSetListener = timeSetListener;
        this.context = layoutInflater.getContext();
        setUpView(view);

    }



    /** initializes up the different view components*/
    private void setUpView(View v){

        startTimeBtn = (Button) v.findViewById(R.id.startTimeBtn);
        endTimeBtn = (Button) v.findViewById(R.id.endTimeBtn);
        saveEventBtn = (Button) v.findViewById(R.id.saveEventBtn);
        startTimePicker = new TimePickerDialog(context, timeSetListener,12,00,true);
        endTimePicker = new TimePickerDialog(context, timeSetListener, 13,00,true);
        titleTxt= (TextView) v.findViewById(R.id.eventTitleLabel);
        nameTxt = (TextView) v.findViewById(R.id.eventNameTxt);
        descTxt = (TextView) v.findViewById(R.id.descTxt);
        startTimeLbl = (TextView) v.findViewById(R.id.startTimeLbl);
        courseSpinner = (Spinner) v.findViewById(R.id.courseSpinner);


    }

    public void setUpText(String name, String description, String eventType){

        if (name==null){
            nameTxt.setText("");
        }else{
            nameTxt.setText(name);
        }
        if (description ==null){
            descTxt.setText("");
        }else{
            descTxt.setText(description);
        }

        customizeFragment(eventType);
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


    /** getters for UI elements */
    public View getView(){
        return this.view;
    }


    public TimePickerDialog getStartTimePicker(){
        return startTimePicker;
    }
    public TimePickerDialog getEndTimePicker(){
        return endTimePicker;
    }


    public Button getStartTimeBtn(){
        return startTimeBtn;
    }
    public Button getEndTimeBtn(){
        return endTimeBtn;
    }
    public Button getSaveEventBtn(){
        return saveEventBtn;
    }
    public TextView getNameTxt(){
        return nameTxt;
    }
    public TextView getDescTxt(){
        return descTxt;
    }
    public Spinner getCourseSpinner(){
        return courseSpinner;
    }

    public String getEventName(){
        return nameTxt.getText().toString();
    }
    public String getEventDesc(){
        return descTxt.getText().toString();
    }


    public void userNeedsToEnterName(){
        nameTxt.setHintTextColor(Color.RED);
        nameTxt.setHint("Please enter name");
    }

    public void setWhichButton(Button buttonPressed){
        whichButton = buttonPressed;
    }
    public Button getWhichButton(){
        return whichButton;
    }


}
