package gruppnan.timeline.MainSystem;

import android.util.Log;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


import gruppnan.timeline.model.TimerStopWatchModel;


/**
 * Created by carlo on 2017-05-26.
 */

public class TimerStopWatchTest {

    TimerStopWatchModel timerStopWatchModel;

    @Before
    public void setUp() {
        timerStopWatchModel = new TimerStopWatchModel();
        timerStopWatchModel.setTimeLeft(72000000);
    }

    @Test
    public void testGetTime() {
        assertEquals("20:00:00", timerStopWatchModel.getTime(timerStopWatchModel.getTimeLeft()));
    }


}