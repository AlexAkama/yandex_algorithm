package training.v3b.task_30;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

// НОП = наибольшая общая подпоследовательность
// dp(i, j) - это максимальная длинна НОП для префикса первой строки до i и второй строки до j

public class Task30 {

    public static void main(String[] args) throws IOException {
        var input = "src/main/java/training/v3b/task_30/input_02.txt";
        File file = new File(input);
        FileReader fileReader = new FileReader(file);
        try (BufferedReader reader = new BufferedReader(fileReader)) {

            String line = reader.readLine();
            int n = Integer.parseInt(line.trim());
            line = reader.readLine();
            String[] split = line.split("\\s");
            int[] sourceN = new int[n + 1];
            for (int i = 1; i < n + 1; i++) sourceN[i] = Integer.parseInt(split[i - 1]);

            line = reader.readLine();
            int m = Integer.parseInt(line.trim());
            line = reader.readLine();
            split = line.split("\\s");
            int[] sourceM = new int[m + 1];
            for (int i = 1; i < m + 1; i++) sourceM[i] = Integer.parseInt(split[i - 1]);

            int[][] dp = new int[m + 1][n + 1];

            for (int i = 1; i < m + 1; i++) {
                for (int j = 1; j < n + 1; j++) {
                    dp[i][j] = (sourceM[i] == sourceN[j]) ? dp[i - 1][j - 1] + 1 : Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }

            int i = m;
            int j = n;
            StringBuilder sb = new StringBuilder();
            while (i > 0 && j > 0) {
                if (sourceN[j] == sourceM[i]) {
                    sb.insert(0, sourceN[j] + " ");
                    i--;
                    j--;
                } else if (dp[i - 1][j] > dp[i][j - 1]) i--;
                else j--;
            }
            System.out.println(sb);

        }

    }


}
