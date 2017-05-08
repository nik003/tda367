package gruppnan.timeline.controller;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import gruppnan.timeline.R;
import gruppnan.timeline.view.KeypadView;

/**
 * A simple {@link Fragment} subclass.
 */
public class KeypadFragment extends Fragment {

    KeypadView keypadView;

    Button oneButton, twoButton, threeButton,fourButton, fiveButton, sixButtton, eightButton, nineButton, zeroButton;

    public KeypadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_keypad, container, false);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



}
