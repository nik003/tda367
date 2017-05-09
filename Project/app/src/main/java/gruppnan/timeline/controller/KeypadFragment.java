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
    String seconds, minutes, hours;
    Button oneButton, twoButton, threeButton,fourButton, fiveButton, sixButton, sevenButton, eightButton, nineButton, zeroButton;
    EditText timeText;
    int time;

    StringBuilder textBuilder;

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

        timeText = (EditText) getView().findViewById(R.id.editText);

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

            default:
                break;
        }



    }

/**
    private String updateTime() {
        seconds = time.substring(time.length()-2, time.length());
        minutes = time.substring(time.length()-4, time.length()-2);
        hours = time.substring(time.length()-6, time.length()-4);

        return "";
    }
**/


    private void addDigit(String number) {
        textBuilder.append(number);
        textBuilder.delete(0, 1);
    }

    private void removeDigit() {
        textBuilder.delete(textBuilder.length()-1, textBuilder.length());
        textBuilder.insert(0, "0");
    }
}
