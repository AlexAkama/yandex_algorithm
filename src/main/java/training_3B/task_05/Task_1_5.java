package training_3B.task_05;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class Task_1_5 {

    public static void main(String[] args) throws IOException {
        var input = "src/main/java/training_3B/task_05/input.txt";
        File file = new File(input);
        FileReader fileReader = new FileReader(file);
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            int n = Integer.parseInt(line);
            long sum = 0;
            if (n != 1) {
                int[] abc = new int[n];
                Set<Integer> set = new TreeSet<>();
                for (int i = 0; i < n; i++) {
                    line = reader.readLine();
                    abc[i] = Integer.parseInt(line);
                    set.add(Integer.parseInt(line));
                }
                int prevLevel = 0;
                for (Integer level : set) {
                    boolean[] frame = getFrame(abc, level - 1);
                    int goods = countGoods(frame);
                    if (goods == 0) break;
                    sum += (long) goods * (level - prevLevel);
                    prevLevel = level;
                }

            }

            try (FileWriter writer = new FileWriter("src/main/java/training_3B/task_05/output.txt")) {
                writer.write(String.valueOf(sum));
                writer.flush();
            }
        }
    }

    private static boolean[] getFrame(int[] ints, int level) {
        boolean[] frame = new boolean[ints.length];
        for (int i = 0; i < ints.length; i++) {
            frame[i] = ints[i] > level;
        }
        return frame;
    }

    private static int countGoods(boolean[] frame) {
        int count = 0;
        for (int i = 1; i < frame.length; i++) {
            if (frame[i - 1] && frame[i]) count++;
        }
        return count;
    }

}
