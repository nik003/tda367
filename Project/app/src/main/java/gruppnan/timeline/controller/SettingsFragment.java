package gruppnan.timeline.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.List;

import gruppnan.timeline.R;
import gruppnan.timeline.model.Course;
import gruppnan.timeline.model.CourseRepository;
import gruppnan.timeline.view.SettingsView;

/**
 * @author Melina Andersson
 * Controlls the settings view
 *
 * Used by: MainActivity, DialogOnClickListener,ContentTimelineFragment
 * Uses: SettingsView, Course, CourseRepository, DialogOnClickListener,CourseSystemInterface,TimeEditHandler
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

    /**
     * Sets listeners for elements in view
     */
    public void setListeners(){
        mRootView.getSearchView().setOnQueryTextListener(this);
        mRootView.getCourseSpinnerView().setOnItemSelectedListener(this);
        mRootView.getTimeTextView().setOnClickListener(this);
        mRootView.getBreakTextView().setOnClickListener(this);
    }


    public void updateTimeText(){
        mRootView.updateTimeText();
    }

    public void updateBreakTimeText(){
        mRootView.updateBreakTimeText();
    }


    /**
     * Invoked when search button is pressed in searchview
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        CourseSystemInterface csi  = new TimeEditHandler();

        List<String>searchMatches  = csi.searchCourses(query);
        Log.d("Query",searchMatches.toString());

        mRootView.initCourseDialog(searchMatches);
        openCourseDialog();
        mRootView.getListView().setOnItemClickListener(this);
        return false;

    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    /**
     * Invoked when course is chosen in dialog
     */
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


    /**
     * Sets weekly goal for the selected course in spinner
     */
    public void setWeekGoal(){
        for(Course course : CourseRepository.getCourseRepository().getAllCourses()) {
            if(course.getCourseID().equals(getSelectedCourseInSpinner())){
                course.setWeeklyGoal(mRootView.getNumberPickerValue()*60L*60L*1000L);
            }
        }

    }

    /**
     * Sets break goal for the selected course in spinner
     */
    public void setBreakGoal(){
        for(Course course : CourseRepository.getCourseRepository().getAllCourses()) {
            if(course.getCourseID().equals(getSelectedCourseInSpinner())){
                course.setBreakGoal(mRootView.getBreakNumberPickerValue()*60L*1000L);
            }
        }    }

    /**
     * Invoked when click on time text
     * Inits the time dialog to choose a time
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.time_picker_text:
                mRootView.initTimeDialog();
                openTimeDialog("Select number of hours", 2, true);
                break;
            case R.id.break_picker_text:
                mRootView.initBreakTimeDialog();
                openTimeDialog("Select number of minutes", 3, false);
                break;
            default:
                break;
        }

    }

    /**
     * Invoked when a course is selected in spinner
     */
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
    public void openTimeDialog(String title, int id, boolean isWeekNumberPicker){
        View npView;
        if(isWeekNumberPicker) {
            npView = mRootView.getNumberPickerView();
        } else {
            npView = mRootView.getBreakNumberPickerView();
        }
        //Start dialog with numberpicker
        alertTimeDialogBuilder = new AlertDialog.Builder(getContext());
        alertTimeDialogBuilder.setTitle(title);
        alertTimeDialogBuilder.setView(npView);
        alertTimeDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Ok",new DialogOnClickListener(this,id))
                .setNegativeButton("Cancel",new DialogOnClickListener(this,id));
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


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * Replaces the fragment with itself so that content updates
     */
    public void updateFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(this);
        fragmentTransaction.commit();
        fragmentManager.beginTransaction().replace(R.id.frame, new SettingsFragment()).commit();
    }


}
