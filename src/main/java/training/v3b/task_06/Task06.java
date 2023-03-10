package training.v3b.task_06;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Task06 {

    private static final List<Interval> LIST = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        var input = "src/main/java/training/v3b/task_06/input_7.txt";
        File file = new File(input);
        FileReader fileReader = new FileReader(file);
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            int m = Integer.parseInt(line);
            line = reader.readLine();
            int n = Integer.parseInt(line);
            for (int i = 0; i < n; i++) {
                line = reader.readLine();
                String[] split = line.split(" ");
                var applicant = new Interval(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                checkingIntersection(applicant);
                LIST.add(applicant);
            }
            System.out.println(LIST.size());
        }
    }

    private static void checkingIntersection(Interval applicant) {
        List<Interval> toDelete = new ArrayList<>();
        for (int i = LIST.size() - 1; i > -1; i--) {
            if (LIST.get(i).hasIntersection(applicant)) toDelete.add(LIST.get(i));
        }
        if (!toDelete.isEmpty()) {
            for (Interval i : toDelete) {
                LIST.remove(i);
            }
        }
    }

    static class Interval {

        int start;
        int finish;

        public Interval(int start, int finish) {
            this.start = start;
            this.finish = finish;
        }

        public boolean hasIntersection(Interval interval) {
            return (interval.start >= start && interval.start <= finish)
                    || (interval.finish >= start && interval.finish <= finish)
                    || (interval.start <= start && interval.finish >= finish);
        }

    }

}
