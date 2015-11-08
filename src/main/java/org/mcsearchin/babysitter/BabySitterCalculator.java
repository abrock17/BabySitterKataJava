package org.mcsearchin.babysitter;

public class BabySitterCalculator {

    private final int RELATIVE_MIDNIGHT = 0;
    private final int CLOCK_MIDNIGHT = 12;
    private final int MAX_CLOCK_TIME = 4;
    private final int PRE_BED_TIME_RATE = 12;
    private final int POST_BED_TIME_RATE = 8;
    private final int POST_MIDNIGHT_RATE = 16;

    public int calculateWages(int startHour, int endHour, int bedTimeHour) {
        int relativeStartHour = toRelativeHour(startHour);
        int relativeEndHour = toRelativeHour(endHour);
        int relativeBedTimeHour = toRelativeHour(bedTimeHour);
        validateRelativeHours(relativeStartHour, relativeEndHour, relativeBedTimeHour);

        int preBedTimeWages = 0, postBedTimeWages = 0;
        if (relativeStartHour < RELATIVE_MIDNIGHT) {
            preBedTimeWages = (Integer.min(relativeEndHour, relativeBedTimeHour)
                    - relativeStartHour) * PRE_BED_TIME_RATE;
            postBedTimeWages = (Integer.min(relativeEndHour, RELATIVE_MIDNIGHT)
                    - Integer.max(relativeStartHour, relativeBedTimeHour)) * POST_BED_TIME_RATE;
        }
        int postMidnightWages = (Integer.max(relativeEndHour, RELATIVE_MIDNIGHT)
                - Integer.max(relativeStartHour, RELATIVE_MIDNIGHT)) * POST_MIDNIGHT_RATE;
        return preBedTimeWages + postBedTimeWages + postMidnightWages;
    }

    private int toRelativeHour(int hour) {
        return hour > MAX_CLOCK_TIME && hour <= CLOCK_MIDNIGHT ? hour - CLOCK_MIDNIGHT : hour;
    }

    private void validateRelativeHours(int relativeStartHour, int relativeEndHour, int relativeBedTimeHour) {
        if (relativeEndHour < relativeStartHour) {
            throw new IllegalArgumentException("end hour cannot be before start hour");
        }
        if (relativeBedTimeHour > RELATIVE_MIDNIGHT) {
            throw new IllegalArgumentException("bed time cannot be after midnight");
        }
    }
}
