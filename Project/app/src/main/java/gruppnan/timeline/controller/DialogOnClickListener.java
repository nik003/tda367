package gruppnan.timeline.controller;

import android.content.DialogInterface;

import static android.content.DialogInterface.BUTTON_NEGATIVE;
import static android.content.DialogInterface.BUTTON_POSITIVE;

/**
 * Created by Melina
 * Help class as listener for the different dialogs in settings view - to separate which dialog is clicked
 */
public class DialogOnClickListener implements DialogInterface.OnClickListener {
    private int id;

    SettingsFragment settingsFragment;


    public DialogOnClickListener(SettingsFragment settingsFragment, int id) {
        this.settingsFragment = settingsFragment;
        this.id = id;
    }

    public void onClick(DialogInterface dialog, int which) {

        if (id == 1) {
            switch (which) {
                case BUTTON_NEGATIVE:
                    dialog.cancel();
                    break;
                case BUTTON_POSITIVE:
                    //TODO add course
                    //settingsFragment.getSelectedCourseInDialog() (returns string)
                    dialog.cancel();
                    break;
                default:
                    break;
            }
        } else {
            switch (which) {
                case BUTTON_NEGATIVE:
                    dialog.cancel();
                    break;
                case BUTTON_POSITIVE:
                    //Add weekly goal for the chosen course in spinner
                    settingsFragment.updateTimeText();
                    settingsFragment.setGoal();
                    dialog.cancel();
                    break;
                default:
                    break;
            }
        }
    }

}