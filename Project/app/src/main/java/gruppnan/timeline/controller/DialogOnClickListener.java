package gruppnan.timeline.controller;

import android.content.DialogInterface;
import android.widget.Toast;

import java.util.Calendar;

import static android.content.DialogInterface.BUTTON_NEGATIVE;
import static android.content.DialogInterface.BUTTON_POSITIVE;

/**
 * @author Melina Andersson
 * Help class as listener for the different dialogs in settings view - to separate which dialog is clicked
 *
 * Used by: SettingsFragment
 * Uses: SettingsFragment,TimeEditHandler,CourseSystemInterface
 */
public class DialogOnClickListener implements DialogInterface.OnClickListener {
    private int id;
    private SettingsFragment settingsFragment;


    public DialogOnClickListener(SettingsFragment settingsFragment, int id) {
        this.settingsFragment = settingsFragment;
        this.id = id;
    }

    public void onClick(DialogInterface dialog, int which) {

        if(this.id == 1) {
            switch (which) {
                case BUTTON_NEGATIVE:
                    dialog.cancel();
                    break;
                case BUTTON_POSITIVE:

                    CourseSystemInterface csi = new TimeEditHandler();
                    Calendar cal1 = Calendar.getInstance();
                    Calendar cal2 = Calendar.getInstance();
                    cal1.set(Calendar.HOUR_OF_DAY, 0);
                    cal1.set(Calendar.MINUTE, 0);
                    cal1.add(Calendar.WEEK_OF_YEAR, -3);
                    cal2.setTime(cal1.getTime());
                    cal2.add(Calendar.MONTH, 3);
                    csi.getAddEvents(settingsFragment.getSelectedCourseInDialog(), cal1.getTime(), cal2.getTime());// (returns string)
                    Toast.makeText(settingsFragment.getContext(), "Added Course", Toast.LENGTH_LONG).show();
                    dialog.cancel();
                    settingsFragment.updateFragment();
                    break;
                default:
                    break;
            }
        } else if(this.id == 2) {
            switch (which) {
                case BUTTON_NEGATIVE:
                    dialog.cancel();
                    break;
                case BUTTON_POSITIVE:
                    //Add weekly goal for the chosen course in spinner
                    settingsFragment.updateTimeText();
                    settingsFragment.setWeekGoal();
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
                        //Add break goal for the chosen course in spinner
                        settingsFragment.updateBreakTimeText();
                        settingsFragment.setBreakGoal();
                        dialog.cancel();
                        break;
                    default:
                        break;
                }
        }
    }

}