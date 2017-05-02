package gruppnan.timeline.controller;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import gruppnan.timeline.R;

public class ItemTimelineFragment extends Fragment {

    TextView txt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String str = "28 May";
        View v= inflater.inflate(R.layout.item_timeline, container, false);
        txt=(TextView) v.findViewById(R.id.date1_timeline);
        setText(str);
        return v;


    }

    public void setText(String text){

        txt.setText(text);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }



}