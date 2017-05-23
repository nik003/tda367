package gruppnan.timeline.view;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import gruppnan.timeline.R;
import gruppnan.timeline.controller.DialogOnClickListener;
import gruppnan.timeline.controller.SettingsFragment;
import gruppnan.timeline.model.Course;
import gruppnan.timeline.model.CourseContainer;

/**
 * Created by Melina on 21/05/2017.
 */

public class SettingsView implements ViewMVC {


    View mRootView;

    SearchView searchView;
    Spinner courseSpinner;
    NumberPicker numberPicker;
    TextView timeText;
    LinearLayout settingsLayout;

    String coursesInSpinner[];
    HashSet<Course> allCourses = new HashSet<>();

    SettingsFragment fragment;
    LayoutInflater inflater;


    public SettingsView(LayoutInflater inflater, ViewGroup container, SettingsFragment fragment){
        mRootView = inflater.inflate(R.layout.settings_layout, container, false);

        searchView = (SearchView) mRootView.findViewById(R.id.search);
        settingsLayout = (LinearLayout) mRootView.findViewById(R.id.settings_layout);
        courseSpinner = (Spinner) mRootView.findViewById(R.id.settings_course_spinner);
        timeText = (TextView) mRootView.findViewById(R.id.time_picker_text);
        timeText.setOnClickListener(fragment);
        timeText.setText("15");

        this.fragment = fragment;
        this.inflater = inflater;

        initCourseSpinner();
        initSearchView();

    }


    public void initSearchView(){
        searchView.setQueryHint("Search course (TDA367)");
        searchView.setOnQueryTextListener(fragment);
    }

    //The course spinner for setting the weekly goal
    public void initCourseSpinner(){
        courseSpinner.setPrompt("Choose course");
        coursesInSpinner = new String[2];
        int i=0;
        for(Course c : CourseContainer.getCourseContainer().getAllCourses()) {
            allCourses.add(c);
            coursesInSpinner[i] = c.getCourseID();
            i++;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mRootView.getContext(), android.R.layout.simple_spinner_item, coursesInSpinner);
        courseSpinner.setAdapter(adapter);
        courseSpinner.setOnItemSelectedListener(fragment);
    }


    public void initTimeDialog(){
        View npView = inflater.inflate(R.layout.time_picker_dialog, null);
        numberPicker = (NumberPicker) npView.findViewById(R.id.number_picker);
        initNumberPicker(numberPicker);
        openTimeDialog(npView);
    }


    public void initNumberPicker(NumberPicker numberPicker){
        String[] data = new String[]{"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(data.length-1);
        numberPicker.setValue(Integer.parseInt(getTimeText()));
        numberPicker.setDisplayedValues(data);
    }

    public void openTimeDialog(View npView){

        //Start dialog with numberpicker
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(inflater.getContext());
        alertDialogBuilder.setTitle("Select number of hours");
        alertDialogBuilder.setView(npView);
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Ok",new DialogOnClickListener(fragment,2))
                .setNegativeButton("Cancel",new DialogOnClickListener(fragment,2));
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    public void updateTimeText(){
            String value = "" + getNumberPickerValue();
            timeText.setText(value);

    }

    public String getTimeText(){
        return (String) timeText.getText();
    }


    public int getNumberPickerValue(){
        return numberPicker.getValue();
    }


    public void initCourseDialog(final String search){
        View cView = inflater.inflate(R.layout.course_result_dialog, null);
        //getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        List<String> searchMatches = new ArrayList<>();
        //TODO Init list of searchmatches here - you have search string as parameter

        final ListView listView = (ListView) cView.findViewById(R.id.course_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(),android.R.layout.simple_list_item_1,searchMatches);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(fragment);

        openCourseDialog(cView);
    }


    public void openCourseDialog(View cView) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(inflater.getContext());
        alertDialogBuilder.setTitle("Select course");
        alertDialogBuilder.setView(cView);
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Add Course",new DialogOnClickListener(fragment,1))
                .setNegativeButton("Cancel",new DialogOnClickListener(fragment,1));
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

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
