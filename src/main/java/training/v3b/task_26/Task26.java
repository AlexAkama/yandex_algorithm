package training.v3b.task_26;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Task26 {

    public static void main(String[] args) throws IOException {
        var input = "src/main/java/training/v3b/task_26/input_2.txt";
        File file = new File(input);
        FileReader fileReader = new FileReader(file);
        try (BufferedReader reader = new BufferedReader(fileReader)) {

            String line = reader.readLine();
            String[] split = line.split(" ");
            int n = Integer.parseInt(split[0]);
            int k = Integer.parseInt(split[1]);

            int[][] data = new int[n + 1][k + 1];
            for (int i = 1; i < n + 1; i++) {
                line = reader.readLine();
                split = line.split(" ");
                for (int j = 1; j < k + 1; j++) data[i][j] = Integer.parseInt(split[j - 1]);
            }

            int[][] dp = new int[n + 1][k + 1];

            dp[1][1] = data[1][1];
            for (int i = 2; i < k + 1; i++) dp[1][i] += dp[1][i - 1] + data[1][i];
            for (int i = 2; i < n + 1; i++) dp[i][1] += dp[i - 1][1] + data[i][1];

            for (int i = 2; i < n + 1; i++) {
                for (int j = 2; j < k + 1; j++) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + data[i][j];
                }
            }

            System.out.println(dp[n][k]);
        }

    }

}