package training_3B.task_22;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Task_22 {

    public static void main(String[] args) throws IOException {
        var input = "src/main/java/training_3B/task_22/input_6.txt";
        File file = new File(input);
        FileReader fileReader = new FileReader(file);
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            String[] split = line.split(" ");
            int n = Integer.parseInt(split[0]);
            int k = Integer.parseInt(split[1]);

            if (n == 1) {
                System.out.println(1);
            } else {
                int[] ints = new int[n];
                int min = Math.min(k, n);
                for (int i = 0; i < min; i++) {
                    ints[i] = (int) Math.pow(2, i);
                }
                for (int i = min; i < n - 1; i++) {
                    int res = 0;
                    for (int j = i - min; j < i; j++) {
                        res += ints[j];
                    }
                    ints[i] = res;
                }
                System.out.println(ints[n - 2]);
            }
        }
    }

}
