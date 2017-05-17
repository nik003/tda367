package gruppnan.timeline.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import gruppnan.timeline.R;

/**
 * Created by Melina on 13/05/2017.
 */

public class SettingsFragment extends Fragment {

    private SearchView searchView;
    private Spinner courseSpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_layout,
                container, false);
        searchView = (SearchView) view.findViewById(R.id.search);
        searchView.setQueryHint("Search code (TDA367)");
        courseSpinner = (Spinner) view.findViewById(R.id.settings_course_spinner);
        courseSpinner.setPrompt("Choose course");

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}
