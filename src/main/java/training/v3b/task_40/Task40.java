package training.v3b.task_40;

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

public class Task40 {

    public static void main(String[] args) throws IOException {
        var input = "src/main/java/training/v3b/task_40/input_17.txt";
        File file = new File(input);
        FileReader fileReader = new FileReader(file);
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String inputLine = reader.readLine();
            int n = Integer.parseInt(inputLine);

            inputLine = reader.readLine();
            int m = Integer.parseInt(inputLine);

            if (m == 1) {
                System.out.println(0);
                return;
            }

            List<List<Integer>> lines = new ArrayList<>(m + 1);
            lines.add(new ArrayList<>());

            String[] split;
            for (int i = 1; i < m + 1; i++) {
                inputLine = reader.readLine();
                split = inputLine.split("\\s");
                List<Integer> list = new ArrayList<>();
                for (int j = 1; j < split.length; j++) {
                    list.add(Integer.parseInt(split[j]));
                }
                lines.add(list);
            }

            inputLine = reader.readLine();
            split = inputLine.split("\\s");
            int startStation = Integer.parseInt(split[0]);
            int finishStation = Integer.parseInt(split[1]);

            List<Integer> startLines = new ArrayList<>();
            List<Integer> finishLines = new ArrayList<>();
            for (int lineNumber = 0; lineNumber < lines.size(); lineNumber++) {
                if (lines.get(lineNumber).contains(startStation)) startLines.add(lineNumber);
                if (lines.get(lineNumber).contains(finishStation)) finishLines.add(lineNumber);
            }

            List<Set<Integer>> crossGraph = new ArrayList<>();
            for (int i = 0; i < lines.size(); i++) crossGraph.add(new HashSet<>());

            for (int i = 1; i < lines.size() - 1; i++) {
                for (int j = i + 1; j < lines.size(); j++) {
                    var line1 = lines.get(i);
                    var line2 = lines.get(j);
                    if (line1.size() > line2.size()) {
                        var temp = line1;
                        line1 = line2;
                        line2 = temp;
                    }
                    for (Integer line1Station : line1) {
                        if (line2.contains(line1Station)) {
                            crossGraph.get(i).add(j);
                            crossGraph.get(j).add(i);
                            break;
                        }
                    }
                }
            }

            if (lines.size() == 3
                    && startLines.size() == 1 && finishLines.size() == 1) {
                var startLine = (int) startLines.get(0);
                var finishLine = (int) finishLines.get(0);
                if (startLine == finishLine) {
                    System.out.println(0);
                    return;
                }
                if (crossGraph.get(startLine).contains(finishLine)
                        && crossGraph.get(finishLine).contains(startLine)) {
                    System.out.println(1);
                    return;
                }
            }

            boolean stop = false;
            int res = Integer.MAX_VALUE;
            for (int startLine : startLines) {
                for (int finishLine : finishLines) {
                    if (startLine == finishLine) {
                        res = 0;
                        stop = true;
                        break;
                    }
                    Deque<Integer> deque = new ArrayDeque<>();
                    deque.addFirst(startLine);
                    deque.addFirst(finishLine);
                    int[] lengths = new int[crossGraph.size()];
                    int[] prevs = new int[crossGraph.size()];
                    int[] heads = new int[crossGraph.size()];
                    heads[startLine] = startLine;
                    heads[finishLine] = finishLine;
                    while (!deque.isEmpty()) {
                        int cur = deque.pollFirst();
                        int prev = prevs[cur];
                        Set<Integer> set = crossGraph.get(cur);
                        for (Integer next : set) {
                            if (next == prev) continue;
                            if (heads[next] == 0 && lengths[next] == 0) {
                                lengths[next] = lengths[cur] + 1;
                                prevs[next] = cur;
                                heads[next] = heads[cur];
                                deque.add(next);
                            } else if (heads[next] != heads[cur]) {
                                res = Math.min(lengths[next] + lengths[cur] + 1, res);
                                break;
                            }
                        }
                    }
                }
                if (stop) break;
            }

            System.out.println(res == Integer.MAX_VALUE ? -1 : res);

        }
    }

}
