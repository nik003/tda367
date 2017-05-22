package gruppnan.timeline.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashSet;

import gruppnan.timeline.model.Course;
import gruppnan.timeline.view.SettingsView;

/**
 * Created by Melina on 13/05/2017.
 */

public class SettingsFragment extends Fragment implements SearchView.OnQueryTextListener {

    private SearchView searchView;
    private Spinner courseSpinner;
    NumberPicker numberPicker;
    TextView timeText;
    LinearLayout settingsLayout;
    ArrayAdapter<String> adapter;
    Object selectedCourse;
    HashSet<Course> allCourses = new HashSet<>();
    String coursesInSpinner[];

    SettingsView settingsView;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        settingsView = new SettingsView(inflater,container,this);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return settingsView.getRootView();
    }




    public void setGoal(int value){
        for(Course c : allCourses){
            if(c.equals(selectedCourse)){
                c.setWeeklyGoal(value);
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

    @Override
    public boolean onQueryTextSubmit(String query) {
        //Open course dialog
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
