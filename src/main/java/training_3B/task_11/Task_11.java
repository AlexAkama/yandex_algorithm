package training_3B.task_11;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Task_11 {

    private static List<String> LIST = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        var input = "src/main/java/training_3B/task_11/input_3.txt";
        File file = new File(input);
        FileReader fileReader = new FileReader(file);
        String line;
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            line = reader.readLine();
            List<String> res = new ArrayList<>();
            while (line != null) {
                String response = stack(line);
                res.add(response);
                if (response.equals("bye")) break;
                line = reader.readLine();
            }

            try (FileWriter writer = new FileWriter("src/main/java/training_3B/task_11/output.txt")) {
                for (String s : res) {
                    writer.write(s);
                    writer.write("\n");
                }
                writer.flush();
            }
        }

    }

    private static String stack(String s) {
        String res = "error";
        if (s.equals("size")) res = String.valueOf(LIST.size());
        else if (s.startsWith("push")) {
            String[] split = s.split(" ");
            LIST.add(split[1]);
            res = "ok";
        } else if (s.equals("pop") && !LIST.isEmpty()) {
            res = LIST.remove(LIST.size() - 1);
        } else if (s.equals("back") && !LIST.isEmpty()) {
            res = LIST.get(LIST.size() - 1);
        } else if (s.equals("clear")) {
            LIST = new ArrayList<>();
            res = "ok";
        } else if (s.equals("exit")) {
            res = "bye";
        }
        return res;
    }

}
