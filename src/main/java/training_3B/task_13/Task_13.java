package training_3B.task_13;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

public class Task_13 {

    public static void main(String[] args) throws IOException {
        var input = "src/main/java/training_3B/task_13/input_1.txt";
        File file = new File(input);
        FileReader fileReader = new FileReader(file);
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            System.out.println(postfixCalculate(line));
        }
    }

    private static int postfixCalculate(String s) {
        String[] split = s.split(" ");
        Deque<Integer> stack = new ArrayDeque<>();
        int res = 0;
        for (String el : split) {
            char c = el.charAt(0);
            if (Character.isDigit(c)) {
                stack.push(Integer.parseInt(el));
            } else {
                int left = stack.pop();
                int right = stack.pop();
                switch (c) {
                    case '+' -> res = right + left;
                    case '-' -> res = right - left;
                    case '*' -> res = right * left;
                }
                stack.push(res);
            }
        }
        return stack.pop();
    }

}
