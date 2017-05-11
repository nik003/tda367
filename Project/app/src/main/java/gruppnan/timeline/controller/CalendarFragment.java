package gruppnan.timeline.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.github.clans.fab.FloatingActionButton;

import gruppnan.timeline.R;


public class CalendarFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FloatingActionButton fab1,fab2;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentManager fragmentManager;
    private FragmentTransaction ft;
    private CalendarView calendarView;

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
        View view = inflater.inflate(R.layout.calendar_view,container,false);
        setUpViewComponents(view);

        return view;
    }
    private void setUpViewComponents(View v){

        fab1 = (FloatingActionButton) v.findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) v.findViewById(R.id.fab2);
        fab1.setOnClickListener(btnListener);
        fab2.setOnClickListener(btnListener);

        calendarView = (CalendarView) v.findViewById(R.id.calendarView);

    }

    private View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Bundle bundle = new Bundle();
            Long dateLong = calendarView.getDate();

            if (view.equals(fab1)){
                Fragment newFragment = new AddEventFragment();
                bundle.putString("type", "event");
                bundle.putLong("date", dateLong);

                newFragment.setArguments(bundle);

                fragmentManager = getActivity().getSupportFragmentManager();
                ft = fragmentManager.beginTransaction();
                ft.replace(R.id.calendar_fragment, newFragment).addToBackStack(null).commit();



            }
            else if (view.equals(fab2)){
                bundle.putString("type", "deadline");
                bundle.putLong("date", dateLong);
                Fragment newFragment = new AddEventFragment();
                newFragment.setArguments(bundle);

                fragmentManager = getActivity().getSupportFragmentManager();
                ft = fragmentManager.beginTransaction();
                ft.replace(R.id.calendar_fragment, newFragment).addToBackStack(null).commit();

            }
        }
    };




}
