package training.v3b.task_36;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task36 {

    private static final List<Set<Integer>> connectivityList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        var input = "src/main/java/training/v3b/task_36/input_5.txt";
        File file = new File(input);
        FileReader fileReader = new FileReader(file);
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            int n = Integer.parseInt(line);

            connectivityList.add(new HashSet<>());
            for (int i = 0; i < n; i++) {
                line = reader.readLine();
                String[] split = line.split("\\s");
                Set<Integer> set = new HashSet<>();
                for (int j = 1; j < n + 1; j++) {
                    int parseInt = Integer.parseInt(split[j - 1]);
                    if (parseInt == 1) set.add(j);
                }
                connectivityList.add(set);
            }

            line = reader.readLine();
            String[] split = line.split("\\s");
            int v1 = Integer.parseInt(split[0]);
            int v2 = Integer.parseInt(split[1]);

            if (v1 == v2) {
                System.out.println(0);
                return;
            }

            if (connectivityList.get(v1).contains(v2)) {
                System.out.println(1);
                return;
            }

            int[] lengths = new int[n + 1];
            int[] heads = new int[n + 1];
            int[] prevs = new int[n + 1];

            Deque<Integer> deque = new ArrayDeque<>();
            deque.add(v1);
            deque.add(v2);
            heads[v1] = v1;
            heads[v2] = v2;

            int res = Integer.MAX_VALUE;
            while (!deque.isEmpty()) {
                int cur = deque.pollFirst();
                int prev = prevs[cur];
                Set<Integer> set = connectivityList.get(cur);
                for (Integer next : set) {
                    if (next == prev) continue;
                    if (lengths[next] == 0) {
                        deque.addLast(next);
                        lengths[next] = lengths[cur] + 1;
                        heads[next] = heads[cur];
                        prevs[next] = cur;
                    }
                    if (heads[next] != heads[cur]) {
                        res = Math.min(lengths[next] + lengths[cur] + 1, res);
                    }
                }

            }
            res = res == Integer.MAX_VALUE ? -1 : res;
            System.out.println(res);

        }

    }

}
