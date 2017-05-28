package gruppnan.timeline.MainSystem;

import org.junit.Before;
import org.junit.Test;

import gruppnan.timeline.model.KeypadModel;

import static org.junit.Assert.assertEquals;

/**
 * @author Carlos Yechouh
 * Tests KeypadModel
 */

public class KeypadTest {

    KeypadModel keypadModel;

    @Before
    public void setUp() {
        keypadModel = new KeypadModel();
    }

    @Test
    public void testAddDigit() {
        keypadModel.addDigit("1");
        keypadModel.addDigit("0");
        keypadModel.addDigit("0");
        keypadModel.addDigit("0");
        keypadModel.addDigit("0");
        keypadModel.addDigit("0");
        keypadModel.addDigit("5");
        assertEquals(36000000, keypadModel.getTime());
    }

    @Test
    public void testRemoveDigit() {
        keypadModel.addDigit("4");
        keypadModel.addDigit("5");
        keypadModel.removeDigit();
        assertEquals(4000, keypadModel.getTime());
    }

    @Test
    public void testGetTime() {
        keypadModel.addDigit("1");
        assertEquals(1000, keypadModel.getTime());
    }
}
