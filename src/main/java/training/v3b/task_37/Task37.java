package training.v3b.task_37;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task37 {

    private static final List<Set<Integer>> connectivityList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        var input = "src/main/java/training/v3b/task_37/input_3.txt";
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
                System.out.println(v1 + " " + v2);
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

            int crossPoint = 0;
            int prevCrossPoint = 0;
            int resLength = Integer.MAX_VALUE;
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
                        int length = lengths[next] + lengths[cur] + 1;
                        if (length < resLength) {
                            resLength = length;
                            crossPoint = next;
                            prevCrossPoint = cur;
                        }
                    }
                }

            }
            resLength = resLength == Integer.MAX_VALUE ? -1 : resLength;
            System.out.println(resLength);
            if (resLength > 0) {
                int[] resPath = new int[resLength + 1];
                int crossPosition = resLength / 2;
                resPath[crossPosition] = crossPoint;
                int pos = resPath[crossPosition];
                int count = -1;
                while (crossPosition + count > -1) {
                    pos = prevs[pos];
                    resPath[crossPosition + count--] = pos;
                }
                count = 1;
                prevs[crossPoint] = prevCrossPoint;
                pos = resPath[crossPosition];
                while (crossPosition + count < resPath.length) {
                    pos = prevs[pos];
                    resPath[crossPosition + count++] = pos;
                }

                if (resPath[0] != v1) reverse(resPath);

                StringBuilder sb = new StringBuilder();
                for (int i : resPath) {
                    sb.append(i).append(" ");
                }
                sb.setLength(sb.length() - 1);
                System.out.println(sb);
            }

        }

    }

    private static void reverse(int[] ints) {
        for (int i = 0; i < ints.length / 2; i++) {
            int tmp = ints[i];
            ints[i] = ints[ints.length - i - 1];
            ints[ints.length - i - 1] = tmp;
        }
    }

}
