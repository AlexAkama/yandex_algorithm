package training_3B.task_10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Task_10 {

    // Подход в лоб - взять и посчитать,
    // но TL 1.08s 27.87Mb
    public static void main1(String[] args) throws IOException {
        var input = "src/main/java/training_3/division_b/day1/task_10/input1.txt";
        File file = new File(input);
        FileReader fileReader = new FileReader(file);
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            char[] chars = line.toCharArray();
            Map<Character, Integer> map = new TreeMap<>();

            for (int i = 0; i < chars.length; i++) {
                for (int j = chars.length - 1; j >= i; j--) {
                    for (int k = i; k <= j; k++) {
                        map.putIfAbsent(chars[k], 0);
                        map.put(chars[k], map.get(chars[k]) + 1);
                    }
                }
            }

            try (FileWriter writer = new FileWriter("src/main/java/training_3/division_b/day1/task_10/output.txt")) {
                for (Character character : map.keySet()) {
                    writer.write(character + ": " + map.get(character) + "\n");
                }
                writer.flush();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        var input = "src/main/java/training_3/division_b/day1/task_10/input1.txt";
        File file = new File(input);
        FileReader fileReader = new FileReader(file);
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            char[] chars = line.toCharArray();
            Map<Character, Long> map = new TreeMap<>();

            long sum;
            for (int i = 0; i < chars.length; i++) {
                sum = (long) (chars.length - i) * (i + 1);
                map.putIfAbsent(chars[i], 0L);
                map.put(chars[i], map.get(chars[i]) + sum);
            }

            StringBuilder sb = new StringBuilder();
            for (Character character : map.keySet()) {
                sb.append(character)
                        .append(": ")
                        .append(map.get(character))
                        .append("\n");
            }
            sb.deleteCharAt(sb.length() - 1);

            try (FileWriter writer = new FileWriter("src/main/java/training_3/division_b/day1/task_10/output.txt")) {
                writer.write(sb.toString());
                writer.flush();
            }
        }
    }

}
