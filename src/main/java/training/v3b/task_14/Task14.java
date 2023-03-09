package training.v3b.task_14;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

public class Task14 {

    public static void main(String[] args) throws IOException {
        var input = "src/main/java/training/v3b/task_14/input_3.txt";
        File file = new File(input);
        FileReader fileReader = new FileReader(file);
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            int n = Integer.parseInt(line);
            int[] nums = new int[n];
            line = reader.readLine();
            String[] split = line.split(" ");
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(split[i]);
            }

            System.out.println((isGoodTrain(nums)) ? "YES" : "NO");
        }
    }

    private static boolean isGoodTrain(int[] nums) {
        if (nums.length == 1) return true;
        Deque<Integer> stack = new ArrayDeque<>();
        int wait = 1;
        for (int num : nums) {
            if (num != wait) stack.add(num);
            else {
                wait++;
                while (!stack.isEmpty() && stack.peekLast() == wait) {
                    stack.pollLast();
                    wait++;
                }
            }
        }
        return stack.isEmpty();
    }

}
