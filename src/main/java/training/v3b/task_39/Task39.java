package training.v3b.task_39;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

public class Task39 {

    private static final char AIR = '.';
    private static final char SPELEOLOGIST = 'S';
    private static final int[][] DELTA = new int[][]{
            {1, 0, 0},
            {-1, 0, 0},
            {0, 1, 0},
            {0, -1, 0},
            {0, 0, 1},
            {0, 0, -1}
    };

    private static char[][][] mine;
    private static int[][][] visited;


    public static void main(String[] args) throws IOException {
        var input = "src/main/java/training/v3b/task_39/input_1.txt";
        File file = new File(input);
        FileReader fileReader = new FileReader(file);
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            int n = Integer.parseInt(line);

            mine = new char[n][n][n];
            visited = new int[n][n][n];

            Point start = null;

            for (int i = 0; i < n; i++) {
                reader.readLine();
                for (int j = 0; j < n; j++) {
                    line = reader.readLine();
                    for (int k = 0; k < n; k++) {
                        var c = line.charAt(k);
                        if (c == SPELEOLOGIST) {
                            start = new Point(i, j, k);
                            markVisited(start, 1);
                        } else
                            mine[i][j][k] = c;
                    }
                }
            }

            var point = start;
            boolean done = false;
            Deque<Point> deque = new ArrayDeque<>();
            deque.addLast(start);
            while (!done) {
                point = deque.pollFirst();
                for (int[] vector : DELTA) {
                    int dx = vector[0];
                    int dy = vector[1];
                    int dz = vector[2];
                    int marker = visited[point.x][point.y][point.z];
                    var nextPoint = new Point(point.x + dx, point.y + dy, point.z + dz);
                    if (isFreePoint(nextPoint) && isNotVisited(nextPoint)) {
                        markVisited(nextPoint, marker + 1);
                        deque.addLast(nextPoint);
                        if (nextPoint.x == 0) {
                            done = true;
                            point = nextPoint;
                            break;
                        }
                    }
                }
            }

            System.out.println(visited[point.x][point.y][point.z] - 1);

        }

    }

    private static boolean isFreePoint(Point point) {
        return isFreePosition(point.x, point.y, point.z);
    }

    private static boolean isFreePosition(int x, int y, int z) {
        if (outOfMine(x, y, z)) return false;
        return mine[x][y][z] == AIR;
    }

    private static boolean outOfMine(int x, int y, int z) {
        int n = mine.length;
        return x < 0 || y < 0 || z < 0 ||
                x > n - 1 || y > n - 1 || z > n - 1;
    }

    private static void markVisited(Point point, int marker) {
        if (outOfMine(point.x, point.y, point.z)) return;
        visited[point.x][point.y][point.z] = marker;
    }

    private static boolean isNotVisited(Point point) {
        if (outOfMine(point.x, point.y, point.z)) return false;
        return visited[point.x][point.y][point.z] == 0;
    }

    static class Point {

        int x;
        int y;
        int z;

        public Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

    }

}
