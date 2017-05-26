package gruppnan.timeline.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;

import gruppnan.timeline.model.Course;
import gruppnan.timeline.model.CourseContainer;
import gruppnan.timeline.view.SettingsView;

/**
 * Created by Melina Andersson
 * Controlls the settings view
 *
 * Used by:
 * Uses: SettingsView
 */

public class SettingsFragment extends Fragment implements SearchView.OnQueryTextListener, AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener, View.OnClickListener{

    String selectedCourseInSpinner;
    String selectedCourseInDialog;
    SettingsView mRootView;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = new SettingsView(inflater,container,this);
        return mRootView.getRootView();
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
        mRootView.initCourseDialog(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    //OnItemClick for listview inside course dialog
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        selectedCourseInDialog = adapterView.getItemAtPosition(i).toString();
    }

    public String getSelectedCourseInDialog(){
        return selectedCourseInDialog;
    }

    public String getSelectedCourseInSpinner(){
        return selectedCourseInSpinner;
    }

    public void setGoal(){
        for(Course course : CourseContainer.getCourseContainer().getAllCourses()) {
            if(course.getCourseID().equals(getSelectedCourseInSpinner())){
                course.setWeeklyGoal(mRootView.getNumberPickerValue());
            }
        }

    }

    public void updateTimeText(){
        mRootView.updateTimeText();
    }

    //OnClick for opening time dialog
    @Override
    public void onClick(View view) {
        mRootView.initTimeDialog();
    }

    //Course spinner listener
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedCourseInSpinner =  adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //Do nothing
    }


}
