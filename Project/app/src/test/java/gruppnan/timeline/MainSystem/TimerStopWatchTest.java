package gruppnan.timeline.MainSystem;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


import gruppnan.timeline.model.TimerStopWatchModel;


/**
 * @author Carlos Yechouh
 * Tests TimerStopWatchModel
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
