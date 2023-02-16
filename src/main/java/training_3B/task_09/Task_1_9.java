package training_3B.task_09;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//
// Вычислить сумму всех элементов в подматрице за постоянное время
// https://www.techiedelight.com/ru/calculate-sum-elements-sub-matrix-constant-time/
//
public class Task_1_9 {

    public static void main(String[] args) throws IOException {
        var input = "src/main/java/training_3/division_b/day1/task_09/input1.txt";
        File file = new File(input);
        FileReader fileReader = new FileReader(file);
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            String[] split = line.split(" ");
            int n = Integer.parseInt(split[0]);
            int m = Integer.parseInt(split[1]);
            int k = Integer.parseInt(split[2]);

            int[][] matrix = new int[n][m];
            for (int i = 0; i < n; i++) {
                line = reader.readLine();
                split = line.split(" ");
                for (int j = 0; j < m; j++) {
                    matrix[i][j] = Integer.parseInt(split[j]);
                }
            }

            int[][] prefix = calculatePrefixSum(matrix);

            int[] sums = new int[k];
            for (int i = 0; i < k; i++) {
                line = reader.readLine();
                split = line.split(" ");
                int x1 = Integer.parseInt(split[0]) - 1;
                int y1 = Integer.parseInt(split[1]) - 1;
                int x2 = Integer.parseInt(split[2]) - 1;
                int y2 = Integer.parseInt(split[3]) - 1;
                sums[i] = calculateSubSum(x1, y1, x2, y2, prefix);
            }

            try (FileWriter writer = new FileWriter("src/main/java/training_3/division_b/day1/task_09/output.txt")) {
                writer.write(String.valueOf(sums[0]));
                for (int i = 1; i < k; i++) {
                    writer.write("\n");
                    writer.write(String.valueOf(sums[i]));
                }
                writer.flush();
            }
        }
    }

    private static int[][] calculatePrefixSum(int[][] source) {

        int[][] res = new int[source.length][source[0].length];
        res[0][0] = source[0][0];

        for (int j = 1; j < source[0].length; j++) {
            res[0][j] = source[0][j] + res[0][j - 1];
        }

        for (int i = 1; i < source.length; i++) {
            res[i][0] = source[i][0] + res[i - 1][0];
        }

        for (int i = 1; i < source.length; i++) {
            for (int j = 1; j < source[0].length; j++) {
                res[i][j] = source[i][j] + res[i - 1][j] + res[i][j - 1]
                        - res[i - 1][j - 1];
            }
        }

        return res;
    }

    private static int calculateSubSum(int x1, int y1, int x2, int y2, int[][] prefix) {
        int sum = prefix[x2][y2];
        if (x1 > 0) sum -= prefix[x1 - 1][y2];
        if (y1 > 0) sum -= prefix[x2][y1 - 1];
        if (x1 > 0 && y1 > 0) sum += prefix[x1 - 1][y1 - 1];
        return sum;
    }

}
