package training.v3b.task_35;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task35ml {

    private static final List<Set<Integer>> connectivityList = new ArrayList<>();
    private static Status[] marked;
    private static List<Integer> circle;

    public static void main(String[] args) throws IOException {
        var input = "src/main/java/training/v3b/task_35/input_10.txt";
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
            marked = new Status[n + 1];
            for (int i = 0; i < n + 1; i++) marked[i] = Status.WHITE;
            circle = new ArrayList<>();

            for (int i = 1; i < n + 1; i++) {
                if (marked[i] == Status.WHITE && (!dfs(i, 0))) break;
            }

            if (circle.isEmpty()) System.out.println("NO");
            else {
                System.out.println("YES");
                System.out.println(circle.size());
                StringBuilder sb = new StringBuilder();
                for (Integer integer : circle) sb.append(integer).append(" ");
                sb.setLength(sb.length() - 1);
                System.out.println(sb);
            }
        }
    }

    private static boolean dfs(int vertex, int prev) {
        if (marked[vertex] == Status.BLACK) return true;
        if (marked[vertex] == Status.GREY && (circle.isEmpty())) {
                circle.add(vertex);
                return false;

        }
        marked[vertex] = Status.GREY;
        Set<Integer> set = connectivityList.get(vertex);
        for (Integer connectedVertex : set) {
            if (connectedVertex == prev) continue;
            if (!dfs(connectedVertex, vertex)) {
                if (circle.get(0) != vertex) {
                    circle.add(vertex);
                    return false;
                } else {
                    return true;
                }
            }
        }

        marked[vertex] = Status.BLACK;
        return true;
    }

    enum Status {
        WHITE, GREY, BLACK
    }

}
