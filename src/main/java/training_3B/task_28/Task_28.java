package training_3B.task_28;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Task_28 {

    public static void main(String[] args) throws IOException {
        var input = "src/main/java/training_3B/task_28/input_01.txt";
        File file = new File(input);
        FileReader fileReader = new FileReader(file);
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            String[] split = line.split(" ");
            int n = Integer.parseInt(split[0]);
            int m = Integer.parseInt(split[1]);

            int[][] dp = new int[n + 2][m + 2];

            dp[2][2] = 1;
            for (int i = 2; i < n + 2; i++) {
                for (int j = 2; j < m + 2; j++) {
                    dp[i][j] += dp[i - 1][j - 2] + dp[i - 2][j - 1];
                }
            }

            System.out.println(dp[n + 1][m + 1]);

        }
    }

}
