package training_3B.task_19;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task_19 {

    private static final List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        var input = "src/main/java/training_3B/task_19/input_5.txt";
        File file = new File(input);
        FileReader fileReader = new FileReader(file);
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            int n = Integer.parseInt(line);
            for (int i = 0; i < n; i++) {
                line = reader.readLine();
                char c = line.charAt(0);
                if (c == '0') insert(line.substring(2));
                if (c == '1') System.out.println(extract());
            }
        }
    }

    public static void insert(String vol) {
        list.add(Integer.parseInt(vol));
        siftUp();
    }

    public static int extract() {
        int res = list.get(0);
        siftDown();
        return res;
    }

    public static void siftUp() {
        int pos = list.size() - 1;
        int parentPos = (pos - 1) / 2;
        while (pos > 0 && list.get(pos) > list.get(parentPos)) {
            Collections.swap(list, pos, parentPos);
            pos = parentPos;
            parentPos = (pos - 1) / 2;
        }
    }

    public static void siftDown() {
        list.set(0, list.get(list.size() - 1));
        int pos = 0;
        int leftChildPos = 1;
        int rightChildPos = 2;
        while (rightChildPos < list.size()) {
            int minChildPos = (list.get(leftChildPos) > list.get(rightChildPos))
                    ? leftChildPos
                    : rightChildPos;
            if (list.get(pos) < list.get(minChildPos)) {
                Collections.swap(list, pos, minChildPos);
                pos = minChildPos;
            } else break;
            leftChildPos = pos * 2 + 1;
            rightChildPos = pos * 2 + 2;
        }
        list.remove(list.size() - 1);
    }

}
