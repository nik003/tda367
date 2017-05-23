package gruppnan.timeline.view;

import android.os.Bundle;
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
 * Created by Melina on 23/05/2017.
 */

public class CardTimelineView implements ViewMVC{

    View mRootView;
    private EditText titleText, descriptionText;
    private TextView dateText;
    private ImageView exitMarker;
    private Button saveButton;
    private CheckBox checkBox;
    CardTimelineFragment fragment;

    public CardTimelineView(LayoutInflater inflater, ViewGroup container,CardTimelineFragment fragment) {
        mRootView = inflater.inflate(R.layout.card_timeline, container, false);
        this.fragment = fragment;
        initViewElements(mRootView);
        initListeners();
    }


    private void initViewElements(View view) {
        checkBox = (CheckBox) view.findViewById(R.id.checkbox);
        saveButton = (Button) view.findViewById(R.id.done_icon);
        exitMarker = (ImageView) view.findViewById(R.id.exit_icon);
        dateText = (TextView) view.findViewById(R.id.date_timeline);
        titleText = (EditText) view.findViewById(R.id.title_timeline);
        descriptionText = (EditText) view.findViewById(R.id.description_timeline);
    }

    public void toggleCheckBox(){
        checkBox.toggle();
    }

    public boolean checkBoxIsChecked(){
        return checkBox.isChecked();
    }

    public void setTexts(String date, String title, String description){
        dateText.setText(date);
        titleText.setText(title);
        descriptionText.setText(description);
    }

    private void initListeners() {
        saveButton.setOnClickListener(fragment);
        exitMarker.setOnClickListener(fragment);
        checkBox.setOnClickListener(fragment);
        dateText.setOnClickListener(fragment);
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

    @Override
    public View getRootView() {
        return mRootView;
    }

    @Override
    public Bundle getViewState() {
        return null;
    }
}