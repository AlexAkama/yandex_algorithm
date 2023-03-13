package training.v3b.task_34;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task34 {

    private static final List<Set<Integer>> connectivityList = new ArrayList<>();
    private static Status[] marked;
    private static int[] res;
    private static int pointer;

    public static void main(String[] args) throws IOException {
        var input = "src/main/java/training/v3b/task_34/input_11.txt";
        File file = new File(input);
        FileReader fileReader = new FileReader(file);
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            String[] split = line.split("\\s");
            int n = Integer.parseInt(split[0]);

            if (n == 1) {
                System.out.println(-1);
                return;
            }

            int m = (split.length > 1) ? Integer.parseInt(split[1]) : 0;

            for (int i = 0; i < n + 1; i++) connectivityList.add(new HashSet<>());
            marked = new Status[n + 1];
            for (int i = 0; i < n + 1; i++) marked[i] = Status.WHITE;
            res = new int[n];
            pointer = n - 1;

            for (int i = 0; i < m; i++) {
                line = reader.readLine();
                split = line.split("\\s");
                int v1 = Integer.parseInt(split[0]);
                int v2 = Integer.parseInt(split[1]);
                if (v1 != v2) {
                    connectivityList.get(v1).add(v2);
                } else {
                    System.out.println(-1);
                    return;
                }
            }

            boolean hasCircle = false;
            for (int i = 1; i < n + 1; i++) {
                if (marked[i] == Status.WHITE) {
                    hasCircle = !dfs(i);
                }
            }

            if (hasCircle) System.out.println(-1);
            else {
                StringBuilder sb = new StringBuilder();
                for (int i : res) sb.append(i).append(" ");
                sb.setLength(sb.length() - 1);
                System.out.println(sb);
            }

        }
    }

    private static boolean dfs(int vertex) {
        if (marked[vertex] == Status.GREY) return false;
        if (marked[vertex] == Status.BLACK) return true;
        marked[vertex] = Status.GREY;
        Set<Integer> set = connectivityList.get(vertex);
        for (Integer connectedVertex : set) {
            if (!dfs(connectedVertex)) {
                return false;
            }

        }
        marked[vertex] = Status.BLACK;
        res[pointer--] = vertex;
        return true;
    }

    enum Status {
        WHITE, GREY, BLACK
    }

}
