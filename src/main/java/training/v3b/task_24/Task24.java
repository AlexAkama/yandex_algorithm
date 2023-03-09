package training.v3b.task_24;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Task24 {

    public static void main(String[] args) throws IOException {
        var input = "src/main/java/training/v3b/task_24/input_3.txt";
        File file = new File(input);
        FileReader fileReader = new FileReader(file);
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            int n = Integer.parseInt(line);

            int offset = 3;
            int[][] data = new int[n + offset][offset];

            int[] dp = new int[n + offset];

            int max = 3601;
            for (int i = 0; i < offset; i++) {
                data[i] = new int[]{max, max, max};
            }

            for (int i = offset; i < n + offset; i++) {
                line = reader.readLine();
                String[] split = line.split(" ");
                data[i][0] = Integer.parseInt(split[0]);
                data[i][1] = Integer.parseInt(split[1]);
                data[i][2] = Integer.parseInt(split[2]);

                dp[i] = Math.min(dp[i - 1] + data[i][0],
                        Math.min(
                                dp[i - 2] + data[i - 1][1],
                                dp[i - 3] + data[i - 2][2]));
            }

            System.out.println(dp[n - 1 + offset]);
        }
    }

}
