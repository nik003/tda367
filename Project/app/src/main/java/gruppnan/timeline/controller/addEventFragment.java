package gruppnan.timeline.controller;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import org.w3c.dom.Text;

import gruppnan.timeline.R;
import gruppnan.timeline.model.DefaultEvent;


public class addEventFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String eventType;
    private TextView titleTextView;

    public addEventFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment addEventFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static addEventFragment newInstance(String param1) {
        addEventFragment fragment = new addEventFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
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
        // Inflate the layout for this fragment
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        View v = inflater.inflate(R.layout.fragment_add_event, container, false);
        titleTextView = (TextView) v.findViewById(R.id.eventTitleLabel);
        eventType = getArguments().getString("type");
        customizeFragment(eventType);
        return v;
    }


    private void customizeFragment(String type){
        if (type.equals("event")){
            titleTextView.setText("Add new event");
        }
        else if (type.equals("deadline")){
            titleTextView.setText("Add new deadline");
        }


    }


}
