package gruppnan.timeline.controller;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gruppnan.timeline.R;
import gruppnan.timeline.model.KeypadModel;
import gruppnan.timeline.view.KeypadView;


/**
 * Created by carlo on 2017-05-02.
 * Controller class which deals with the custom keypad interactions.
 */

public class KeypadFragment extends Fragment implements View.OnClickListener {

    private KeypadView keypadView;
    private KeypadModel keypadModel;
    private View view;

    public static KeypadFragment newInstance(String course) {
        KeypadFragment fragment = new KeypadFragment();
        Bundle args = new Bundle();
        args.putString("course", course);
        fragment.setArguments(args);
        return fragment;
    }

    public KeypadFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        keypadModel = new KeypadModel();
        keypadView = new KeypadView(inflater, container);
        view = keypadView.getView();

        if (container != null) {
            container.removeAllViews();
        }

        addListeners();

        keypadView.getTimeText().setText("00:00:00");
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zeroButton:
                keypadModel.addDigit(keypadView.getButton(0).getText().toString());
                break;

            case R.id.oneButton:
                keypadModel.addDigit(keypadView.getButton(1).getText().toString());
                break;

            case R.id.twoButton:
                keypadModel.addDigit(keypadView.getButton(2).getText().toString());
                break;

            case R.id.threeButton:
                keypadModel.addDigit(keypadView.getButton(3).getText().toString());
                break;

            case R.id.fourButton:
                keypadModel.addDigit(keypadView.getButton(4).getText().toString());
                break;

            case R.id.fiveButton:
                keypadModel.addDigit(keypadView.getButton(5).getText().toString());
                break;

            case R.id.sixButton:
                keypadModel.addDigit(keypadView.getButton(6).getText().toString());
                break;

            case R.id.sevenButton:
                keypadModel.addDigit(keypadView.getButton(7).getText().toString());
                break;

            case R.id.eightButton:
                keypadModel.addDigit(keypadView.getButton(8).getText().toString());
                break;

            case R.id.nineButton:
                keypadModel.addDigit(keypadView.getButton(9).getText().toString());
                break;

            case R.id.deleteButton:
                keypadModel.removeDigit();
                break;

            case R.id.okButton:
                continueToTimer();
                break;

            default:
                break;
        }
        displayText();
    }

    /**
     * Saves the input in a bundle and returns to the previous screen
     */
    private void continueToTimer() {

        getFragmentManager().popBackStackImmediate();
    }

    /**
     * Add listener to each button.
     */
    private void addListeners() {
        for(int i = 0; i < keypadView.buttons.length; i++) {
            keypadView.getButton(i).setOnClickListener(this);
        }
    }

    /**
     * Displays current time passed/left on screen.
     */
    public void displayText() {
        keypadView.getTimeText().setText(String.format("%02d:%02d:%02d", keypadModel.getHours(), keypadModel.getMinutes(), keypadModel.getSeconds()));
    }
}
