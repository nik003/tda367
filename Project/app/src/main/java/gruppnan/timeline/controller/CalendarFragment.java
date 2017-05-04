package gruppnan.timeline.controller;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import gruppnan.timeline.R;



public class CalendarFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button chooseEventButton, eventButton, deadlineButton;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentManager fragmentManager;
    private FragmentTransaction ft;

    public CalendarFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static CalendarFragment newInstance(String param1, String param2) {
        CalendarFragment fragment = new CalendarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calendar,container,false);
        setUpViewComponents(v);
        return v;
    }
    private void setUpViewComponents(View v){

        eventButton = (Button) v.findViewById(R.id.eventButton);
        eventButton.setOnClickListener(btnListener);
        deadlineButton = (Button) v.findViewById(R.id.deadlineButton);
        deadlineButton.setOnClickListener(btnListener);

        fragmentManager = getActivity().getFragmentManager();
        ft = fragmentManager.beginTransaction();
    }

    private View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.equals(eventButton)){
                eventButton.setBackgroundColor(Color.GREEN);
                addEventFragment newFragment = new addEventFragment();
                ft.replace(android.R.id.content, newFragment).commit();

            }
            else if (view.equals(deadlineButton)){
                eventButton.setBackgroundColor(Color.BLUE);
            }
        }
    };




}
