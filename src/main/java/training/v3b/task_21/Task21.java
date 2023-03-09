package training.v3b.task_21;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Task21 {

    public static void main(String[] args) throws IOException {
        var input = "src/main/java/training/v3b/task_21/input_1.txt";
        File file = new File(input);
        FileReader fileReader = new FileReader(file);
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            int n = Integer.parseInt(line);

            int[] dp = new int[n + 2];
            dp[0] = 1;
            dp[1] = 2;
            dp[2] = 4;

            for (int i = 3; i < n + 1; i++) {
                dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
            }

            System.out.println(dp[n]);
        }

    }

}
