package gruppnan.timeline.controller;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import gruppnan.timeline.R;

/**
 * Created by Melina on 13/05/2017.
 */

public class SettingsFragment extends Fragment {

    private SearchView searchView;
    private Spinner courseSpinner;
    NumberPicker numberPicker;
    TextView timeText;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_layout,
                container, false);
        searchView = (SearchView) view.findViewById(R.id.search);
        searchView.setQueryHint("Search code (TDA367)");
        courseSpinner = (Spinner) view.findViewById(R.id.settings_course_spinner);
        courseSpinner.setPrompt("Choose course");

        timeText = (TextView)view.findViewById(R.id.time_picker_text);

        timeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimeDialog(inflater);
            }
        });

        return view;
    }

    public void showTimeDialog(final LayoutInflater inflater){
        View npView = inflater.inflate(R.layout.time_picker_dialog, null);

        numberPicker = (NumberPicker) npView.findViewById(R.id.number_picker);
        String[] data = new String[]{"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(data.length-1);
        numberPicker.setValue(15);
        numberPicker.setDisplayedValues(data);

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
                                Log.e("","New Quantity Value : "+ numberPicker.getValue());

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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}
