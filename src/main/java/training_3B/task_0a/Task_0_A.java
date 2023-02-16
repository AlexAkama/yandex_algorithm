package training_3B.task_0a;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Task_0_A {

    public static void main(String[] args) throws IOException {
        var input = "src/main/java/day0/task_a/input_test2.txt";
        File file = new File(input);
        FileReader fileReader = new FileReader(file);
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            var n = Integer.parseInt(line);
            line = reader.readLine();
            String[] split = line.split("\\s");

            List<Integer> nums = new ArrayList<>();
            for (String s : split) {
                nums.add(Integer.parseInt(s));
            }

            for (int i = 0; i < n - 1; i++) {
                if (nums.get(i + 1) < nums.get(i)) {
                    System.out.println(-1);
                    return;
                }
            }
            System.out.println(nums.get(n-1) - nums.get(0));
        }
    }

}
