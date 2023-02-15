package training_3.division_b.day1.task_07;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

public class Task_1_7 {

    public static void main(String[] args) throws IOException, ParseException {
        var input = "src/main/java/training_3/division_b/day1/task_07/input_test3.txt";
        File file = new File(input);
        FileReader fileReader = new FileReader(file);
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String request = reader.readLine();
            String server = reader.readLine();
            String response = reader.readLine();
            int requestInSecond = timeInSecond(request);
            int responseInSecond = timeInSecond(response);
            int delta = (requestInSecond < responseInSecond)
                    ? (int) Math.round((double) (responseInSecond - requestInSecond) / 2)
                    : (int) Math.round((double) (24 * 60 * 60 - requestInSecond + responseInSecond) / 2);

            System.out.println(delta);

            String res = addSeconds(server, delta);

            System.out.println(res);

            try (FileWriter writer = new FileWriter("src/main/java/training_3/division_b/day1/task_07/output.txt")) {
                writer.write(String.valueOf(res));
                writer.flush();
            }

        }
    }

    private static int timeInSecond(String s) {
        String[] split = s.split(":");
        return Integer.parseInt(split[0]) * 60 * 60
                + Integer.parseInt(split[1]) * 60
                + Integer.parseInt(split[2]);
    }

    private static String addSeconds(String s, Integer delta) {
        if (delta == 0) return s;
        String[] split = s.split(":");
        int hours = Integer.parseInt(split[0]);
        int minutes = Integer.parseInt(split[1]);
        int seconds = Integer.parseInt(split[2]);
        seconds += delta;
        if (seconds > 59) {
            minutes += seconds / 60;
            seconds %= 60;
            if (minutes > 59) {
                hours += minutes / 60;
                minutes %= 60;
            }
            if (hours > 23) {
                hours %= 24;
            }
        }
        return leaderZero(hours) + ":" + leaderZero(minutes) + ":" + leaderZero(seconds);
    }

    private static String leaderZero(int i) {
        return (i < 10) ? "0" + i : "" + i;
    }

}
