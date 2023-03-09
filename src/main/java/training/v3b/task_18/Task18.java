package training.v3b.task_18;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Task18 {

    private static List<String> list = new ArrayList<>(135);

    public static void main(String[] args) throws IOException {
        var input = "src/main/java/training/v3b/task_18/input_9.txt";
        File file = new File(input);
        FileReader fileReader = new FileReader(file);
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line;
            String response = "";
            while (!response.equals("bye")) {
                line = reader.readLine();
                response = doOperation(line);
                System.out.println(response);
            }
        }
    }

    private static String doOperation(String s) {
        String res = "error";
        String[] split = s.split(" ");
        switch (split[0]) {
            case "push_front" -> {
                list.add(0, split[1]);
                res = "ok";
            }
            case "push_back" -> {
                list.add(split[1]);
                res = "ok";
            }
            case "pop_front" -> {
                if (!list.isEmpty()) res = list.remove(0);
            }
            case "pop_back" -> {
                if (!list.isEmpty()) res = list.remove(list.size() - 1);
            }
            case "front" -> {
                if (!list.isEmpty()) res = list.get(0);
            }
            case "back" -> {
                if (!list.isEmpty()) res = list.get(list.size() - 1);
            }
            case "size" -> res = String.valueOf(list.size());
            case "clear" -> {
                list = new ArrayList<>(135);
                res = "ok";
            }
            case "exit" -> res = "bye";
        }
        return res;
    }

}
