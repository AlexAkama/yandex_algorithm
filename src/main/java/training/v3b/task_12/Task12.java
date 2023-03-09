package training.v3b.task_12;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

public class Task12 {

    public static void main(String[] args) throws IOException {
        var input = "src/main/java/training/v3b/task_12/input_9.txt";
        File file = new File(input);
        FileReader fileReader = new FileReader(file);
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();

            System.out.println(line);
            System.out.println((isGoodBracketSequence(line))
                    ? "yes"
                    : "no");
        }
    }

    private static boolean isGoodBracketSequence(String s) {
        if (s.length() == 1) return false;
        char[] chars = s.toCharArray();
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : chars) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char pop = stack.pop();
                if (c == ')') {
                    if (pop != '(') return false;
                } else if (c == '}') {
                    if (pop != '{') return false;
                } else if (c == ']') {
                    if (pop != '[') return false;
                }
            }
        }
        return stack.isEmpty();
    }

}
