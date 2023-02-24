package training_3B.task_00b;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Task_B {

    public static void main(String[] args) throws IOException {
        var input = "src/main/java/day0/task_b/input1.txt";
        File file = new File(input);
        FileReader fileReader = new FileReader(file);
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            int size = Integer.parseInt(line);
            Map<Integer, Integer> map = new TreeMap<>();
            for (int i = 0; i < size; i++) {
                line = reader.readLine();
                String[] split = line.split("\\s");
                var operation = split[4];
                if (operation.equals("B")) continue;
                int vector = (operation.equals("A")) ? -1 : 1;
                var id = Integer.parseInt(split[3]);
                var day = Integer.parseInt(split[0]);
                var hour = Integer.parseInt(split[1]);
                var min = Integer.parseInt(split[2]);
                var time = 24 * 60 * day + 60 * hour + min;
                map.putIfAbsent(id, 0);
                map.put(id, map.get(id) + vector * time);
            }

            List<String> list = new ArrayList<>();
            for (Integer key : map.keySet()) {
                list.add(map.get(key).toString());
            }

            String response = String.join(" ", list);

            var output = "src/main/java/day1/task_b/output.txt";
            file = new File(output);
            try (FileWriter fileWriter = new FileWriter(file)) {
                fileWriter.write(response);
            }

            for (int id : map.keySet()) {
                System.out.println(id + ": " + map.get(id));
            }
        }
    }

}
