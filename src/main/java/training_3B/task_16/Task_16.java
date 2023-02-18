package training_3B.task_16;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Task_16 {

    private static List<String> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        var input = "src/main/java/training_3B/task_16/input_3.txt";
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
            case "push":
                list.add(split[1]);
                res = "ok";
                break;
            case "front":
                if (!list.isEmpty()) res = list.get(0);
                break;
            case "pop":
                if (!list.isEmpty()) res = list.remove(0);
                break;
            case "size":
                res = String.valueOf(list.size());
                break;
            case "clear":
                list = new ArrayList<>();
                res = "ok";
                break;
            case "exit":
                res = "bye";
                break;
        }
        return res;
    }

}
