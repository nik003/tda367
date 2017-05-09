package gruppnan.timeline.controller;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import gruppnan.timeline.R;
import gruppnan.timeline.view.KeypadView;

/**
 * A simple {@link Fragment} subclass.
 */
public class KeypadFragment extends Fragment implements View.OnClickListener {

    KeypadView keypadView;
    int seconds, minutes, hours;
    Button oneButton, twoButton, threeButton,fourButton, fiveButton, sixButton, sevenButton, eightButton, nineButton, zeroButton, deleteButton, okButton;
    EditText timeText;
    int time;

    StringBuilder textBuilder;

    public KeypadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        oneButton = (Button) getView().findViewById(R.id.oneButton);
        oneButton.setOnClickListener(this);
        twoButton = (Button) getView().findViewById(R.id.twoButton);
        twoButton.setOnClickListener(this);
        threeButton = (Button) getView().findViewById(R.id.threeButton);
        threeButton.setOnClickListener(this);
        fourButton = (Button) getView().findViewById(R.id.fourButton);
        fourButton.setOnClickListener(this);
        fiveButton = (Button) getView().findViewById(R.id.fiveButton);
        fiveButton.setOnClickListener(this);
        sixButton = (Button) getView().findViewById(R.id.sixButton);
        sixButton.setOnClickListener(this);
        sevenButton = (Button) getView().findViewById(R.id.sevenButton);
        sevenButton.setOnClickListener(this);
        eightButton = (Button) getView().findViewById(R.id.eightButton);
        eightButton.setOnClickListener(this);
        nineButton = (Button) getView().findViewById(R.id.nineButton);
        nineButton.setOnClickListener(this);
        zeroButton = (Button) getView().findViewById(R.id.zeroButton);
        zeroButton.setOnClickListener(this);
        deleteButton = (Button) getView().findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(this);
        okButton = (Button) getView().findViewById(R.id.okButton);
        okButton.setOnClickListener(this);

        timeText = (EditText) getView().findViewById(R.id.editText);
        return inflater.inflate(R.layout.fragment_keypad, container, false);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        

        textBuilder.append("000000");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.oneButton:
                addDigit(oneButton.getText().toString());
                break;

            case R.id.twoButton:
                addDigit(twoButton.getText().toString());
                break;

            case R.id.threeButton:
                addDigit(threeButton.getText().toString());
                break;

            case R.id.fourButton:
                addDigit(fourButton.getText().toString());
                break;

            case R.id.fiveButton:
                addDigit(fiveButton.getText().toString());
                break;

            case R.id.sixButton:
                addDigit(sixButton.getText().toString());
                break;

            case R.id.sevenButton:
                addDigit(sevenButton.getText().toString());
                break;

            case R.id.eightButton:
                addDigit(eightButton.getText().toString());
                break;

            case R.id.nineButton:
                addDigit(nineButton.getText().toString());
                break;

            case R.id.zeroButton:
                addDigit(zeroButton.getText().toString());
                break;

            case R.id.deleteButton:
                removeDigit();
                break;

            case R.id.okButton:
                continueToTimer();
                break;

            default:
                break;
        }
    }


    private void addDigit(String number) {
        textBuilder.append(number);
        textBuilder.delete(0, 1);
        updateTime();
        displayText();
    }

    private void removeDigit() {
        textBuilder.delete(textBuilder.length()-1, textBuilder.length());
        textBuilder.insert(0, "0");
        updateTime();
        displayText();
    }

    private void displayText() {
        timeText.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }

    private void updateTime() {
        seconds = Integer.parseInt(textBuilder.substring(textBuilder.length()-2, textBuilder.length()));
        minutes = Integer.parseInt(textBuilder.substring(textBuilder.length()-4, textBuilder.length()-2));
        hours = Integer.parseInt(textBuilder.substring(textBuilder.length()-6, textBuilder.length()-4));
    }

    private void continueToTimer() {

    }
}
