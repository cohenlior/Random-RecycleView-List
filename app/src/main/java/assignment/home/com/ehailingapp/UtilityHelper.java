package assignment.home.com.ehailingapp;

import java.util.concurrent.TimeUnit;

public class UtilityHelper {
    private static final long MINUTE = 1;
    private static final long HOUR = 60 * MINUTE;

    // Format how the time is displayed depending whether the given time is in minutes or in hours.
    public static String timeFormatter(int time){
        if (time > HOUR) {
            long hours = TimeUnit.MINUTES.toHours((long) time);
            long remainMinutes = time - TimeUnit.HOURS.toMinutes(hours);

            return String.format("%dh %dm", hours, remainMinutes);
        }

        return String.format("%dm", time);
    }

    // Get a random time by given a range in minutes.
    public static int getRandomETA(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
}
