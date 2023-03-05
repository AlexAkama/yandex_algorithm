package training_3B.task_29;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

// dp - храним стоимость обедов
// dp(день, купоны)
// Попасть в текущий день можем только из предыдущего.
// Бывают дни кода ты можешь получить купон.
// Если поели по купону в день когда можно получить купон, купон не получаем.
public class Task_29 {

    public static void main(String[] args) throws IOException {
        var input = "src/main/java/training_3B/task_29/input_9.txt";
        File file = new File(input);
        FileReader fileReader = new FileReader(file);
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            int n = Integer.parseInt(line);

            int[] data = new int[n + 1];
            int limit = 100;
            int k = 0;
            for (int i = 1; i < n + 1; i++) {
                line = reader.readLine();
                data[i] = Integer.parseInt(line);
                if (data[i] > limit) k++;
            }

            int[][] dp = new int[n + 1][k + 2];
            int max = 100 * 300 + 1;
            for (int i = 1; i < k + 2; i++) dp[0][i] = max;

            int couponCount = 0;
            for (int i = 1; i < n + 1; i++) {
                boolean overLimit = data[i] > limit;
                if (overLimit) couponCount++;
                for (int j = 0; j < k + 2; j++) {
                    if (j > couponCount) dp[i][j] = max;
                    else calculate(i, j, overLimit, dp, data);
                }

            }

            int used = 0;
            int i = n;
            int j = 0;
            StringBuilder sb = new StringBuilder();
            while (i > 0) {
                if (data[i] != 0 && dp[i][j] == dp[i - 1][j + 1]) {
                    used++;
                    sb.insert(0, i + "\n");
                    j++;
                    if (data[i] > limit) k--;
                } else if (data[i] > limit && (--j < 0)) {
                    j = 0;
                }
                i--;
            }

            System.out.println(dp[n][0]);
            System.out.println((k - used) + " " + used);
            System.out.println(sb);

        }
    }

    private static void calculate(int i, int j, boolean overLimit, int[][] dp, int[] data) {
        int dx = j == 0 || !overLimit ? 0 : 1;
        dp[i][j] = Math.min(dp[i - 1][j - dx] + data[i], dp[i - 1][j + 1]);
    }


}
