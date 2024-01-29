package org.fittrack.fitnesstrackerdemo.fuckaround;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FindOut {
    public static void main(String[] args) {

//        String string = new String("biceps triceps");
//
//        String[] split = string.split("\\s+");
//
//        for (String s : split) {
//            System.out.println(s.contains(" "));
//            System.out.println(s);
//        }

//        Exercise ex = new Exercise();
//        ex.setName("Squat");
//
//        WorkoutVolume wv = new WorkoutVolume();
//        wv.setExercise(ex);
//        wv.setSetRangeMin(3);
//        wv.setSetRangeMax(4);
//        wv.setRepRangeMin(8);
//        wv.setRepRangeMax(12);

//        WorkoutVolumeToPretty wvtp = new WorkoutVolumeToPretty();
//
//        PrettyWorkoutVolume prettyWorkoutVolume = wvtp.convertOneToAnother(wv);
//
//        System.out.println(prettyWorkoutVolume);

        LocalDateTime currentDateTime = LocalDateTime.now();
        String formattedString = formatLocalDateTime(currentDateTime);

        System.out.println("Formatted LocalDateTime: " + formattedString);
        System.out.println("Non formatted LocalDateTime: " + currentDateTime);

    }

    public static String formatLocalDateTime(LocalDateTime localDateTime) {
        // Define the desired date-time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss-ns");

        // Format the LocalDateTime using the defined formatter
        String formattedDateTime = localDateTime.format(formatter);

        return formattedDateTime;
    }
}
