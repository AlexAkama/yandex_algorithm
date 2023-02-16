package training_3B.task_02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Task_1_2 {

    public static void main(String[] args) throws IOException {
        var input = "src/main/java/day1/task_2/input1.txt";
        File file = new File(input);
        FileReader fileReader = new FileReader(file);
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            int k = Integer.parseInt(line);
            line = reader.readLine();
            char[] chars = line.toCharArray();
            int[] w = new int[26];
            int max = 0;
            int p = 0;
            for (int i = 0; i < chars.length; i++) {
                max = Math.max(max, ++w[chars[i] - 'a']);
                if (max + k - 1 < i - p) w[chars[p++] - 'a']--;
            }
            String res = String.valueOf(chars.length - p);
            try (FileWriter writer = new FileWriter("src/main/java/day1/task_2/output.txt")) {
                writer.write(res);
                writer.flush();
            }
        }

    }

}
