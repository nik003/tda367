package gruppnan.timeline.controller;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashSet;

import gruppnan.timeline.R;
import gruppnan.timeline.model.Course;
import gruppnan.timeline.model.CourseContainer;

/**
 * Created by Melina on 13/05/2017.
 */

public class SettingsFragment extends Fragment {

    private SearchView searchView;
    private Spinner courseSpinner;
    NumberPicker numberPicker;
    TextView timeText;
    LinearLayout settingsLayout;
    ArrayAdapter<String> adapter;
    String selectedCourse;
    HashSet<Course> allCourses = new HashSet<>();
    String coursesInSpinner[];
    int currValue;


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_layout,
                container, false);

        searchView = (SearchView) view.findViewById(R.id.search);
        settingsLayout = (LinearLayout) view.findViewById(R.id.settings_layout);
        courseSpinner = (Spinner) view.findViewById(R.id.settings_course_spinner);
        timeText = (TextView)view.findViewById(R.id.time_picker_text);

        initCourseSpinner();
        initSearchView(inflater);
        setListeners(inflater);

        return view;
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
                showTimeDialog(inflater);
            }
        });

        courseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedCourse = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Do nothing
            }
        });
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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, coursesInSpinner);
        courseSpinner.setAdapter(adapter);
    }


    public void initSearchView(final LayoutInflater inflater){
        searchView.setQueryHint("Search course (TDA367)");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String s) {
                showCourseDialog(inflater,s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    public void showTimeDialog(final LayoutInflater inflater){
        View npView = inflater.inflate(R.layout.time_picker_dialog, null);
        numberPicker = (NumberPicker) npView.findViewById(R.id.number_picker);

        //Configure numberpicker
        String[] data = new String[]{"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(data.length-1);
        numberPicker.setValue(currValue);
        numberPicker.setDisplayedValues(data);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

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
                                setGoal(numberPicker.getValue());

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
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        String searchMatches[] = {"hej","dä","hej","dä","hej","dä","hej","dä","hej","dä","hej","dä","hej","dä","hej","dä"};
        final ListView listView = (ListView) cView.findViewById(R.id.course_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(),android.R.layout.simple_list_item_1,searchMatches);
        listView.setAdapter(adapter);


        //Start dialog
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(inflater.getContext());
        alertDialogBuilder.setTitle("Select course");
        alertDialogBuilder.setView(cView);
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {


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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {



            }
        });
    }

    public void setGoal(int value){
        for(Course c : allCourses){
            if(c.getCourseID() == selectedCourse){
                c.setWeeklyGoal(value);
                // currValue = c.getWeeklyGoal(); ändra
            }
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }



    private void hideKeyboard(){
        InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
