package training.v3b.task_33;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Двудольный граф

public class Task33 {

    private static final List<Set<Integer>> connectivityList = new ArrayList<>();
    private static int[] marked;

    public static void main(String[] args) throws IOException {
        var input = "src/main/java/training/v3b/task_33/input_12.txt";
        File file = new File(input);
        FileReader fileReader = new FileReader(file);
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            String[] split = line.split("\\s");
            int n = Integer.parseInt(split[0]);
            int m = (split.length > 1) ? Integer.parseInt(split[1]) : 0;

            if (m == 0) {
                System.out.println("YES");
                return;
            }

            for (int i = 0; i < n + 1; i++) connectivityList.add(new HashSet<>());
            marked = new int[n + 1];

            for (int i = 0; i < m; i++) {
                line = reader.readLine();
                split = line.split("\\s");
                int v1 = Integer.parseInt(split[0]);
                int v2 = Integer.parseInt(split[1]);
                if (v1 != v2) {
                    connectivityList.get(v1).add(v2);
                    connectivityList.get(v2).add(v1);
                }
            }

            boolean res = true;
            for (int i = 1; i < n + 1; i++) {
                if (marked[i] > 0) continue;
                res = dfs(i, 1);
                if (!res) break;
            }

            System.out.println(res ? "YES" : "NO");

        }
    }

    private static boolean dfs(int vertex, int marker) {
        if (marked[vertex] > 0) {
            return marked[vertex] == marker;
        }
        marked[vertex] = marker;
        Set<Integer> set = connectivityList.get(vertex);
        if (set.isEmpty()) return true;
        int nextMarker = nextMarker(marker);
        for (Integer connectedVertex : set) {
            if (!dfs(connectedVertex, nextMarker)) return false;
        }
        return true;
    }

    private static int nextMarker(int marker) {
        return 3 - marker;
    }

}
