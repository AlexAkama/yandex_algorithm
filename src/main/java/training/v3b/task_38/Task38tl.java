package training.v3b.task_38;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

public class Task38tl {

    private static final int[][] DELTA = new int[][]{
            {2, 1},
            {1, 2},
            {-1, 2},
            {-2, 1},
            {-2, -1},
            {-1, -2},
            {1, -2},
            {2, -1}
    };
    private static int n;
    private static int m;
    private static int s;
    private static int t;

    public static void main(String[] args) throws IOException {
        var input = "src/main/java/training/v3b/task_38/input_2.txt";
        File file = new File(input);
        FileReader fileReader = new FileReader(file);
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            String[] split = line.split("\\s");
            n = Integer.parseInt(split[0]);
            m = Integer.parseInt(split[1]);
            s = Integer.parseInt(split[2]);
            t = Integer.parseInt(split[3]);
            int q = Integer.parseInt(split[4]);

            Point[] points = new Point[q];
            for (int i = 0; i < q; i++) {
                line = reader.readLine();
                split = line.split("\\s");
                int x = Integer.parseInt(split[0]);
                int y = Integer.parseInt(split[1]);
                points[i] = new Point(x, y);
            }

            int res = 0;
            for (Point point : points) {
                int bfs = bfs(point);
                if (bfs == -1) {
                    System.out.println(-1);
                    return;
                } else res += bfs;
            }

            System.out.println(res);

        }
    }

    private static int bfs(Point point) {
        if (point.x == s && point.y == t) return 0;
        int[][] grid = new int[n + 1][m + 1];
        Deque<Point> deque = new ArrayDeque<>();
        deque.addLast(point);
        while (!deque.isEmpty()) {
            point = deque.pollFirst();
            for (int[] vector : DELTA) {
                int dx = vector[0];
                int dy = vector[1];
                Point next = new Point(point.x + dx, point.y + dy);
                if (outOfGrid(next)) continue;
                if (grid[next.x][next.y] == 0) {
                    grid[next.x][next.y] = grid[point.x][point.y] + 1;
                    deque.addLast(next);
                }
            }
        }
        return grid[s][t] == 0 ? -1 : grid[s][t];
    }

    private static boolean outOfGrid(Point point) {
        return point.x < 1 || point.y < 1 || point.x > n || point.y > m;
    }

    static class Point {

        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return x + ":" + y;
        }

    }

}
