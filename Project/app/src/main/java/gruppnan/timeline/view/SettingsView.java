package gruppnan.timeline.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashSet;

import gruppnan.timeline.R;
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

    Object selectedCourse;

    public SettingsView(LayoutInflater inflater, ViewGroup container, SettingsFragment fragment){
        mRootView = inflater.inflate(R.layout.settings_layout, container, false);

        searchView = (SearchView) mRootView.findViewById(R.id.search);
        settingsLayout = (LinearLayout) mRootView.findViewById(R.id.settings_layout);
        courseSpinner = (Spinner) mRootView.findViewById(R.id.settings_course_spinner);
        timeText = (TextView) mRootView.findViewById(R.id.time_picker_text);

        this.fragment = fragment;

        initCourseSpinner();
        initSearchView(inflater);
        setListeners(inflater);

    }


    public void initSearchView(final LayoutInflater inflater){
        searchView.setQueryHint("Search course (TDA367)");
        searchView.setOnQueryTextListener(fragment);
    }

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
    }

    public void setListeners(final LayoutInflater inflater){
        settingsLayout.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent ev)
            {
                //hideKeyboard();
                return false;
            }
        });


        timeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initTimeDialog(inflater);
            }
        });

        courseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedCourse =  adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Do nothing
            }
        });
    }


    public void initTimeDialog(final LayoutInflater inflater){
        View npView = inflater.inflate(R.layout.time_picker_dialog, null);
        numberPicker = (NumberPicker) npView.findViewById(R.id.number_picker);
        configureNumberPicker(numberPicker);
        startNumberPickerDialog(inflater,npView);
    }


    public void configureNumberPicker(NumberPicker numberPicker){
        String[] data = new String[]{"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(data.length-1);
        numberPicker.setValue(15);
        numberPicker.setDisplayedValues(data);
    }

    public void startNumberPickerDialog(final LayoutInflater inflater, View npView){

        //Start dialog with numberpicker
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(inflater.getContext());
        alertDialogBuilder.setTitle("Select number of hours");
        alertDialogBuilder.setView(npView);
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                String s = "" + numberPicker.getValue();
                                timeText.setText(s);
                                //setGoal(numberPicker.getValue());

                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    public void showCourseDialog(final LayoutInflater inflater, final String search){
        View cView = inflater.inflate(R.layout.course_result_dialog, null);
        //getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        String searchMatches[] = {"hej","dä","hej","dä","hej","dä","hej","dä","hej","dä","hej","dä","hej","dä","hej","dä"};
        final ListView listView = (ListView) cView.findViewById(R.id.course_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(),android.R.layout.simple_list_item_1,searchMatches);
        listView.setAdapter(adapter);

        startCourseDialog(inflater,cView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


            }
        });

    }


    public void startCourseDialog(final LayoutInflater inflater, View cView) {
        //Start dialog
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(inflater.getContext());
        alertDialogBuilder.setTitle("Select course");
        alertDialogBuilder.setView(cView);
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Add Course",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                //add course here

                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                dialog.cancel();
                            }
                        });
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
