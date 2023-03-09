package training.v3b.task_01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Task01 {

    public static void main(String[] args) throws IOException {
        var input = "src/main/java/day1/task_1/input1.txt";
        File file = new File(input);
        FileReader fileReader = new FileReader(file);
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            Map<Character, Integer> map = new TreeMap<>();
            int max = 0;
            StringBuilder sb = new StringBuilder();
            while (line != null) {
                char[] chars = line.toCharArray();
                for (char key : chars) {
                    if (key != ' ') {
                        map.putIfAbsent(key, 0);
                        int count = map.get(key) + 1;
                        map.put(key, count);
                        if (count > max) max = count;
                    }
                }
                line = reader.readLine();
            }
            for (int i = 0; i < max; i++) {
                for (Character c : map.keySet()) {
                    if (map.get(c) > max - i - 1) sb.append('#');
                    else sb.append(' ');
                }
                sb.append('\n');
            }
            for (Character c : map.keySet()) sb.append(c);
            try (FileWriter writer = new FileWriter("src/main/java/day1/task_1/output.txt")) {
                writer.write(sb.toString());
                writer.flush();
            }

        }

    }

}
