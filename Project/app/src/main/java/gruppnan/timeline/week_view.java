package gruppnan.timeline;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import gruppnan.timeline.view.week_view_fragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link week_view.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link week_view#newInstance} factory method to
 * create an instance of this fragment.
 */
public class week_view extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view  = inflater.inflate(R.layout.fragment_week_view, container, false);
        TableLayout tl = (TableLayout) view.findViewById(R.id.weekView);
        week_view_fragment wwf =new week_view_fragment(view.getContext(),tl);
        wwf.createTable();
        return view;
    }


}
