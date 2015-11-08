package org.mcsearchin.babysitter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by tbrock on 10/27/15.
 */
public class BabySitterCalculatorTest {

    private BabySitterCalculator babySitterCalculator;

    @Before
    public void setUp() {
        babySitterCalculator = new BabySitterCalculator();
    }

    @Test
    public void testOneHourBeforeBedTime() {
        Assert.assertEquals(12, babySitterCalculator.calculateWages(7, 8, 8));
    }

    @Test
    public void testTwoHoursBeforeBedTime() {
        Assert.assertEquals(24, babySitterCalculator.calculateWages(6, 8, 8));
    }

    @Test
    public void testOneHourAfterBedTime() {
        Assert.assertEquals(8, babySitterCalculator.calculateWages(8, 9, 8));
    }

    @Test
    public void testTwoHoursAfterBedTime() {
        Assert.assertEquals(16, babySitterCalculator.calculateWages(8, 10, 8));
    }

    @Test
    public void testTwoHoursSpanningBedTime() {
        Assert.assertEquals(20, babySitterCalculator.calculateWages(7, 9, 8));
    }

    @Test
    public void testTwoHoursAfterLaterBedTime() {
        Assert.assertEquals(16, babySitterCalculator.calculateWages(10, 12, 10));
    }

    @Test
    public void testOneHourAfterMidnight() {
        Assert.assertEquals(16, babySitterCalculator.calculateWages(1, 2, 12));
    }

    @Test
    public void testOneHourAtMidnight() {
        Assert.assertEquals(16, babySitterCalculator.calculateWages(12, 1, 12));
    }

    @Test
    public void testTwoHoursAfterBedtimeSpanningMidnight() {
        Assert.assertEquals(24, babySitterCalculator.calculateWages(11, 1, 11));
    }

    @Test
    public void testTwoHoursSpanningBedtimeAtMidnight() {
        Assert.assertEquals(28, babySitterCalculator.calculateWages(11, 1, 12));
    }

    @Test
    public void testThreeHoursSpanningBedtimeBeforeMidnight() {
        Assert.assertEquals(36, babySitterCalculator.calculateWages(10, 1, 11));
    }

    @Test
    public void testElevenHoursWithAReasonableBedtime() {
        Assert.assertEquals(136, babySitterCalculator.calculateWages(5, 4, 9));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEndTimeBeforeStartTime() {
        babySitterCalculator.calculateWages(6, 5, 8);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBedTimeAfterMidnight() {
        babySitterCalculator.calculateWages(6, 5, 1);
    }
}
