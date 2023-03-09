package training.v3b.task_25;

// https://site.ada.edu.az/~medv/acm/Docs%20e-olimp/Volume%2010/987.htm

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Task25 {

    public static void main(String[] args) throws IOException {
        var input = "src/main/java/training/v3b/task_25/input_1.txt";
        File file = new File(input);
        FileReader fileReader = new FileReader(file);
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            int n = Integer.parseInt(line);
            line = reader.readLine();
            String[] split = line.split(" ");

            int[] data = new int[n + 1];
            for (int i = 0; i < n; i++) {
                data[i + 1] = Integer.parseInt(split[i]);
            }
            Arrays.sort(data);

            int[] dp = new int[n + 1];
            dp[2] = data[2] - data[1];
            if (n > 2) {
                dp[3] = data[3] - data[1];
                for (int i = 4; i < n + 1; i++) {
                    dp[i] = Math.min(dp[i - 1], dp[i - 2]) + data[i] - data[i - 1];
                }
            }

            System.out.println(dp[n]);
        }
    }

}
