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
 */

/**
 * A simple {@link Fragment} subclass.
 */
public class KeypadFragment extends Fragment implements View.OnClickListener {

    private KeypadView keypadView;
    private KeypadModel keypadModel;
    private View view;

    public static KeypadFragment newInstance(String type) {
        KeypadFragment fragment = new KeypadFragment();
        fragment.getArguments().putString("type", type);
        return fragment;
    }

    public KeypadFragment() {
        super();
        setArguments(new Bundle());
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


    private void continueToTimer() {
        Fragment fragment = getFragmentManager().findFragmentByTag("timerStopWatchMain");
        fragment.getArguments().putLong("time", keypadModel.getTime());
        getFragmentManager().popBackStackImmediate();
    }


    public void onBackPressed() {
        getFragmentManager().popBackStackImmediate();
    }


    private void addListeners() {
        for(int i = 0; i < keypadView.buttons.length; i++) {
            keypadView.getButton(i).setOnClickListener(this);
        }
    }

    public void displayText() {
        keypadView.getTimeText().setText(String.format("%02d:%02d:%02d", keypadModel.getHours(), keypadModel.getMinutes(), keypadModel.getSeconds()));
    }
}
