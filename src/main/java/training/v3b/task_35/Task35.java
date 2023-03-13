package training.v3b.task_35;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task35 {

    private static final List<Set<Integer>> connectivityList = new ArrayList<>();
    private static Status[] marked;
    private static List<Integer> circle;
    private static boolean circleDone = false;

    public static void main(String[] args) throws IOException {
        var input = "src/main/java/training/v3b/task_35/input_3.txt";
        File file = new File(input);
        FileReader fileReader = new FileReader(file);
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            int n = Integer.parseInt(line);

            connectivityList.add(new HashSet<>());
            marked = new Status[n + 1];
            for (int i = 1; i < n + 1; i++) {
                line = reader.readLine();
                String[] split = line.split("\\s");
                Set<Integer> set = new HashSet<>();
                for (int j = 1; j < n + 1; j++) {
                    int parseInt = Integer.parseInt(split[j - 1]);
                    if (parseInt == 1) set.add(j);
                }
                connectivityList.add(set);
                marked[i] = set.isEmpty() ? Status.BLACK : Status.WHITE;
            }
            circle = new ArrayList<>();

            for (int i = 1; i < n + 1; i++) {
                if (!circle.isEmpty()) break;
                dfs(i, 0);
            }

            if (circle.isEmpty()) System.out.println("NO");
            else {
                try (FileWriter writer = new FileWriter("src/main/java/training/v3b/task_35/output.txt")) {
                    writer.write("YES\n");
                    writer.write(circle.size() + "\n");
                    for (Integer integer : circle) writer.write(integer + " ");
                    writer.flush();
                }
            }
        }
    }

    private static boolean dfs(int vertex, int prev) {
        if (marked[vertex] == Status.BLACK) return true;
        if (marked[vertex] == Status.GREY) {
            circle.add(vertex);
            return false;
        }

        marked[vertex] = Status.GREY;
        Set<Integer> set = connectivityList.get(vertex);
        for (Integer connectedVertex : set) {
            if (connectedVertex == prev) continue;
            if (!dfs(connectedVertex, vertex)) {
                if (vertex == circle.get(0)) circleDone = true;
                if (!circleDone) circle.add(vertex);
                return false;
            }
        }

        marked[vertex] = Status.BLACK;
        return true;
    }

    enum Status {
        WHITE, GREY, BLACK
    }

}
