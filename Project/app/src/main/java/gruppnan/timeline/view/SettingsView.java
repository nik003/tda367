package gruppnan.timeline.view;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
 * Created by Melina Andersson
 * The settings view
 */
public class SettingsView{

    private View mRootView;
    private SearchView searchView;
    private Spinner courseSpinner;
    private NumberPicker numberPicker;
    private TextView timeText;
    private String coursesInSpinner[];
    private HashSet<Course> allCourses = new HashSet<>();
    private SettingsFragment fragment;
    private LayoutInflater inflater;

    public SettingsView(LayoutInflater inflater, ViewGroup container, SettingsFragment fragment){
        mRootView = inflater.inflate(R.layout.settings_layout, container, false);
        this.fragment = fragment;
        this.inflater = inflater;

        searchView = (SearchView) mRootView.findViewById(R.id.search);
        courseSpinner = (Spinner) mRootView.findViewById(R.id.settings_course_spinner);
        timeText = (TextView) mRootView.findViewById(R.id.time_picker_text);

        initTimeView();
        initSearchView();

        //If courses exists the course spinner could be initialized
        if(!CourseContainer.getCourseContainer().getAllCourses().isEmpty()) {
            initCourseSpinner();
        }
    }

    /**
     * Inits the "add course" search view
     */
    public void initSearchView(){
        searchView.setQueryHint("Search course (TDA367)");
        searchView.setOnQueryTextListener(fragment);
    }

    /**
     * Inits the time textview
     */
    public void initTimeView(){
        timeText.setOnClickListener(fragment);
        timeText.setText("15");
    }

    /**
     * Inits the course spinner
     */
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


    /**
     * Inits the dialog for choosing time
     */
    public void initTimeDialog(){
        View npView = inflater.inflate(R.layout.time_picker_dialog, null);
        numberPicker = (NumberPicker) npView.findViewById(R.id.number_picker);
        initNumberPicker(numberPicker);
        openTimeDialog(npView);
    }

    /**
     * Init the values for the numberpicker in the dialog
     * @param numberPicker
     */
    public void initNumberPicker(NumberPicker numberPicker){
        String[] data = new String[]{"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(data.length-1);
        numberPicker.setValue(Integer.parseInt(getTimeText()));
        numberPicker.setDisplayedValues(data);
    }

    /**
     * Opens time dialog
     * @param npView the view element for the dialog
     */
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

    /**
     * Sets the chosen time in text
     */
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


    /**
     * Inits the course dialog to display search results
     * @param search
     */
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

    /**
     * Opens the course dialog
     * @param cView
     */
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


    public View getRootView() {
        return mRootView;
    }


}
