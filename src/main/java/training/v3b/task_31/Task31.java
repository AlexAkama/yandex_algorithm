package training.v3b.task_31;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task31 {

    private static final List<Set<Integer>> connectivityList = new ArrayList<>();
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        var input = "src/main/java/training/v3b/task_31/input_2.txt";
        File file = new File(input);
        FileReader fileReader = new FileReader(file);
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            String[] split = line.split("\\s");
            int n = Integer.parseInt(split[0]);
            int m = Integer.parseInt(split[1]);

            for (int i = 0; i < n + 1; i++) connectivityList.add(new HashSet<>());
            visited = new boolean[n + 1];

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

            List<Integer> list = dfs(1, new ArrayList<>());

            System.out.println(list.size());
            if (!list.isEmpty()) {
                Collections.sort(list);
                StringBuilder sb = new StringBuilder();
                for (Integer integer : list) sb.append(integer).append(" ");
                sb.setLength(sb.length() - 1);
                System.out.println(sb);
            }
        }
    }

    private static List<Integer> dfs(int vertex, List<Integer> list) {
        visited[vertex] = true;
        list.add(vertex);
        Set<Integer> set = connectivityList.get(vertex);
        for (Integer connectedVertex : set) {
            if (!visited[connectedVertex]) {
                dfs(connectedVertex, list);
            }
        }
        return list;
    }


}
