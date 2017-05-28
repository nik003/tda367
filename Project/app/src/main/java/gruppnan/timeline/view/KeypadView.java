package gruppnan.timeline.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import gruppnan.timeline.R;

/**
 * Created by carlo on 2017-05-04.
 */

public class KeypadView {

    private View view;
    private Button zeroButton, oneButton, twoButton, threeButton,fourButton, fiveButton, sixButton, sevenButton, eightButton, nineButton, deleteButton, okButton;
    public Button[] buttons;
    private TextView timeText;



    public KeypadView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_keypad, container, false);

        timeText = (TextView) view.findViewById(R.id.editText);

        zeroButton = (Button) view.findViewById(R.id.zeroButton);
        oneButton = (Button) view.findViewById(R.id.oneButton);
        twoButton = (Button) view.findViewById(R.id.twoButton);
        threeButton = (Button) view.findViewById(R.id.threeButton);
        fourButton = (Button) view.findViewById(R.id.fourButton);
        fiveButton = (Button) view.findViewById(R.id.fiveButton);
        sixButton = (Button) view.findViewById(R.id.sixButton);
        sevenButton = (Button) view.findViewById(R.id.sevenButton);
        eightButton = (Button) view.findViewById(R.id.eightButton);
        nineButton = (Button) view.findViewById(R.id.nineButton);
        deleteButton = (Button) view.findViewById(R.id.deleteButton);
        okButton = (Button) view.findViewById(R.id.okButton);

        buttons = new Button[] {zeroButton, oneButton, twoButton, threeButton,fourButton, fiveButton, sixButton, sevenButton, eightButton, nineButton, deleteButton, okButton};
    }

    public Button getButton(int i) {
        return buttons[i];
    }

    public TextView getTimeText() {
        return timeText;
    }

    public View getView() {
        return view;
    }


}
