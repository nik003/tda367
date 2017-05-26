package gruppnan.timeline.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
    private AlertDialog.Builder alertCourseDialogBuilder, alertTimeDialogBuilder;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = new SettingsView(inflater,container);
        setListeners();
        return mRootView.getRootView();
    }

    public void setListeners(){
        mRootView.getSearchView().setOnQueryTextListener(this);
        mRootView.getCourseSpinnerView().setOnItemSelectedListener(this);
        mRootView.getTimeTextView().setOnClickListener(this);

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
        openCourseDialog();
        mRootView.getListView().setOnItemClickListener(this);
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
        openTimeDialog();
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

    /**
     * Opens time dialog
     */
    public void openTimeDialog(){
        View npView = mRootView.getNumberPickerView();
        //Start dialog with numberpicker
        alertTimeDialogBuilder = new AlertDialog.Builder(getContext());
        alertTimeDialogBuilder.setTitle("Select number of hours");
        alertTimeDialogBuilder.setView(npView);
        alertTimeDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Ok",new DialogOnClickListener(this,2))
                .setNegativeButton("Cancel",new DialogOnClickListener(this,2));
        AlertDialog alertDialog = alertTimeDialogBuilder.create();
        alertDialog.show();
    }


    /**
     * Opens the course dialog
     */
    public void openCourseDialog() {
        View cView = mRootView.getCourseView();
        alertCourseDialogBuilder = new AlertDialog.Builder(getContext());
        alertCourseDialogBuilder.setTitle("Select course");
        alertCourseDialogBuilder.setView(cView);
        alertCourseDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Add Course",new DialogOnClickListener(this,1))
                .setNegativeButton("Cancel",new DialogOnClickListener(this,1));

        AlertDialog alertDialog = alertCourseDialogBuilder.create();
        alertDialog.show();

    }






}
