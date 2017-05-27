package gruppnan.timeline.view;

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
import gruppnan.timeline.model.Course;
import gruppnan.timeline.model.CourseRepository;

/**
 * @author Melina Andersson
 * The settings view
 *
 * Used by: SettingsFragment
 * Uses: Course, CourseRepository
 */
public class SettingsView{

    private View mRootView;
    private SearchView searchView;
    private Spinner courseSpinner;
    private NumberPicker numberPicker;
    private TextView timeText;
    private ListView listView;
    private HashSet<Course> allCourses = new HashSet<>();
    private LayoutInflater inflater;
    private View cView, npView;



    public SettingsView(LayoutInflater inflater, ViewGroup container){
        mRootView = inflater.inflate(R.layout.settings_layout, container, false);
        this.inflater = inflater;

        searchView = (SearchView) mRootView.findViewById(R.id.search);
        courseSpinner = (Spinner) mRootView.findViewById(R.id.settings_course_spinner);
        timeText = (TextView) mRootView.findViewById(R.id.time_picker_text);

        initTimeView();
        initSearchView();

        //If courses exists the course spinner could be initialized
        if(!CourseRepository.getCourseRepository().getAllCourses().isEmpty()) {
            initCourseSpinner();
        }
    }

    /**
     * Inits the "add course" search view
     */
    public void initSearchView(){
        searchView.setQueryHint("Search course (TDA367)");
    }

    /**
     * Inits the time textview
     */
    public void initTimeView(){
        timeText.setText("15");
    }

    /**
     * Inits the course spinner
     */
    public void initCourseSpinner(){
        courseSpinner.setPrompt("Choose course");
        List<String> coursesInSpinner = new ArrayList<>();
        for(Course c : CourseRepository.getCourseRepository().getAllCourses()) {
            allCourses.add(c);
            coursesInSpinner.add(c.getCourseID());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mRootView.getContext(), android.R.layout.simple_spinner_item, coursesInSpinner);
        courseSpinner.setAdapter(adapter);
    }


    /**
     * Inits the dialog for choosing time
     */
    public void initTimeDialog(){
        npView = inflater.inflate(R.layout.time_picker_dialog, null);
        numberPicker = (NumberPicker) npView.findViewById(R.id.number_picker);
        initNumberPicker(numberPicker);
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
     * @param searchMatches
     */
    public void initCourseDialog(List<String> searchMatches){
        cView = inflater.inflate(R.layout.course_result_dialog, null);



        if(searchMatches!=null) {
            listView = (ListView) cView.findViewById(R.id.course_list);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, searchMatches);
            listView.setAdapter(adapter);
        }
    }

    public View getRootView() {
        return mRootView;
    }

    public TextView getTimeTextView(){
        return timeText;
    }

    public Spinner getCourseSpinnerView(){
        return courseSpinner;
    }

    public SearchView getSearchView(){
        return searchView;
    }

    public ListView getListView(){
        return listView;
    }

    public View getNumberPickerView() {
        return npView;
    }

    public View getCourseView(){
        return cView;
    }




}
