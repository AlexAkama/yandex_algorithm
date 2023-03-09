package training.v3b.task_15;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

public class Task15 {

    public static void main(String[] args) throws IOException {
        var input = "src/main/java/training/v3b/task_15/input_1.txt";
        File file = new File(input);
        FileReader fileReader = new FileReader(file);
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            var n = Integer.parseInt(line);
            int[] nums = new int[n];
            int[] res = new int[n];
            line = reader.readLine();
            String[] split = line.split(" ");
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(split[i]);
            }

            Deque<Pair> stack = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                Pair current = new Pair(i, nums[i]);
                while (true) {
                    Pair peek = stack.peek();
                    if (peek == null || current.vol >= peek.vol) {
                        break;
                    }
                    Pair pop = stack.pollFirst();
                    res[pop.pos] = i;
                }
                stack.push(current);
            }

            if (!stack.isEmpty()) {
                while (!stack.isEmpty()) {
                    Pair pop = stack.pop();
                    res[pop.pos] = -1;
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int vol : res) {
                sb.append(vol).append(" ");
            }
            sb.setLength(sb.length() - 1);

            System.out.println(sb);

        }
    }

    static class Pair {

        int pos;
        int vol;

        public Pair(int pos, int vol) {
            this.pos = pos;
            this.vol = vol;
        }

        @Override
        public String toString() {
            return "[" + pos + ":" + vol + "]";
        }

    }

}
