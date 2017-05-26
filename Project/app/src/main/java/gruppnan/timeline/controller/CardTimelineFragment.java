package gruppnan.timeline.controller;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

import gruppnan.timeline.R;
import gruppnan.timeline.model.DeadlineEvent;
import gruppnan.timeline.model.EventRepository;
import gruppnan.timeline.view.CardTimelineView;

/**
 * Created by Melina Andersson
 * Controlls the detailed card view popping up when pressing a card on timeline
 *
 * Used by: DialogOnClickListener
 * Uses: CardTimelineView, DeadlineEvent, EventRepository
 */

public class CardTimelineFragment extends Fragment implements View.OnClickListener{

    private int id;
    private String title, date, description, hour, minute;
    private EventRepository eventRepository;
    private DeadlineEvent event;
    private final Calendar calendar = Calendar.getInstance();
    CardTimelineView mRootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = new CardTimelineView(inflater,container);
        initContent();
        initListeners();
        return mRootView.getRootView();
    }

    /**
     * Inits the texts and checkbox of the card
     */
    public void initContent(){
        //Checks if card belongs to timeline 1 or 2
        id = getArguments().getInt("ID", 0);

        //Initialize texts from event
        Map<Integer,DeadlineEvent> events;
        eventRepository = EventRepository.getEventRepository();
        events = eventRepository.getDeadlineEventMap();
        for(Map.Entry<Integer, DeadlineEvent> entry : events.entrySet()){
            if(this.id == entry.getKey()){
                event = entry.getValue();
                date = event.getDayofMonth() + " " + event.getMonthAsString();
                hour = (event.getHour() < 10 ? "0" : "")+ event.getHour();
                minute = (event.getMinute() < 10 ? "0" : "")+ event.getMinute();
                title = event.getName();
                description = event.getDescription();
            }
        }
        mRootView.setTexts(date,title,description,hour,minute);

        //If deadline is done, set checkbox marked
        if(event.isDone()){
            mRootView.toggleCheckBox();
        }

    }

    public void initListeners() {
        mRootView.getSaveButton().setOnClickListener(this);
        mRootView.getExitMarker().setOnClickListener(this);
        mRootView.getCheckBox().setOnClickListener(this);
        mRootView.getDateTextView().setOnClickListener(this);
    }

    /**
     * Sets time for event, returns message dialog box if wrong format
     */
    public void setTime(){
        int newHour = Integer.parseInt(mRootView.getHourText());
        int newMinute = Integer.parseInt(mRootView.getMinuteText());
        if(newHour <= 23 && newMinute <= 59){
            event.setHour(newHour);
            event.setMinute(newMinute);
            //If time format is approved, continue the save button action
            removeFragment();
            hideKeyboard();
        } else {
            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this.getContext());

            dlgAlert.setMessage("Please enter a time between 00:00-23:59");
            dlgAlert.setTitle("Wrong time format");
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();

            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
        }

    }

    /**
     * Updates date in event and in view
     */
    private void updateDate() {
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
        String date = day + " " + month;
        mRootView.updateDateText(date);
        event.setEndDate(DeadlineEvent.toDate(calendar));
    }

    /**
     * Removes fragment and returns to timeline
     */
    public void removeFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(this);
        fragmentTransaction.commit();
        fragmentManager.beginTransaction().replace(R.id.frame, new ContentTimelineFragment()).commit();
    }


    /**
     * Handles onclick events from view
     * @param view The view that is clicked
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.date_timeline:
                new DatePickerDialog(getContext(), dateListener, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.checkbox:
                if((mRootView.checkBoxIsChecked() && !event.isDone())){
                    event.setDone(true);
                } else {
                    event.setDone(false);
                }
                break;
            case R.id.exit_icon:
                removeFragment();
                hideKeyboard();
                break;
            case R.id.done_icon:
                event.setName(mRootView.getTitleText());
                event.setDescription(mRootView.getDescriptionText());
                setTime();
                break;
            default:
                break;
        }
    }

    /**
     * Hides keyboard, called when exiting card view
     */
    private void hideKeyboard(){
        InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    /**
     * DatePickerDialog Listener
     */
    final DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {

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
}
