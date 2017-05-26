package gruppnan.timeline.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import gruppnan.timeline.R;
import gruppnan.timeline.controller.CardTimelineFragment;

/**
 * Created by Melina Andersson
 * The view for the detailed cards on timeline
 */
public class CardTimelineView{

    View mRootView;
    private EditText titleText, descriptionText, hourText, minuteText;
    private TextView dateText;
    private ImageView exitMarker;
    private Button saveButton;
    private CheckBox checkBox;
    CardTimelineFragment fragment;

    public CardTimelineView(LayoutInflater inflater, ViewGroup container,CardTimelineFragment fragment) {
        mRootView = inflater.inflate(R.layout.card_timeline, container, false);
        this.fragment = fragment;
        initViewElements(mRootView);
    }

    private void initViewElements(View view) {
        checkBox = (CheckBox) view.findViewById(R.id.checkbox);
        saveButton = (Button) view.findViewById(R.id.done_icon);
        exitMarker = (ImageView) view.findViewById(R.id.exit_icon);
        dateText = (TextView) view.findViewById(R.id.date_timeline);
        hourText = (EditText) view.findViewById(R.id.card_hour);
        minuteText = (EditText) view.findViewById(R.id.card_minute);
        titleText = (EditText) view.findViewById(R.id.title_timeline);
        descriptionText = (EditText) view.findViewById(R.id.description_timeline);
    }

    public void toggleCheckBox(){
        checkBox.toggle();
    }

    public boolean checkBoxIsChecked(){
        return checkBox.isChecked();
    }

    public void setTexts(String date, String title, String description, String hour, String minute){
        dateText.setText(date);
        titleText.setText(title);
        descriptionText.setText(description);
        hourText.setText(hour);
        minuteText.setText(minute);
    }



    public Button getSaveButton(){
        return saveButton;
    }

    public ImageView getExitMarker(){
        return exitMarker;
    }

    public CheckBox getCheckBox(){
        return checkBox;
    }

    public TextView getDateTextView(){
        return dateText;
    }

    public void updateDateText(String text){
        dateText.setText(text);
    }


    public String getTitleText(){
        return titleText.getText().toString();
    }

    public String getDescriptionText(){
        return descriptionText.getText().toString();
    }

    public String getHourText(){
        return hourText.getText().toString();
    }

    public String getMinuteText(){
        return minuteText.getText().toString();
    }


    public View getRootView() {
        return mRootView;
    }

}
