package training_3B.task_04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Task_04 {

    public static void main(String[] args) throws IOException {
        var input = "src/main/java/training_3/division_b/day1/task_04/input_test0.txt";
        File file = new File(input);
        FileReader fileReader = new FileReader(file);
        String res = "-1";
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            int n = Integer.parseInt(line);
            line = reader.readLine();
            int k = Integer.parseInt(line);
            line = reader.readLine();
            int row = Integer.parseInt(line);
            line = reader.readLine();
            int position = Integer.parseInt(line);

            int odd = k % 2;
            int nextRow = row + k / 2;
            int prevRow = row - k / 2;
            int nextPosition = position;
            int prevPosition = position;
            if (odd == 1) {
                if (position + 1 > 2) {
                    nextRow++;
                    nextPosition = 1;
                } else {
                    nextPosition = 2;
                }
                if (position - 1 < 1) {
                    prevRow--;
                    prevPosition = 2;
                } else {
                    prevPosition = 1;
                }
            }
            boolean prevValid = prevRow > 0;
            boolean nextValid = (nextRow - 1) * 2 + nextPosition - 1 < n;

            if (prevValid && nextValid) {
                int dPrev = row - prevRow;
                int dNext = nextRow - row;
                if (dPrev < dNext) {
                    res = prevRow + " " + prevPosition;
                } else {
                    res = nextRow + " " + nextPosition;
                }
            } else if (!prevValid && nextValid) {
                    res = nextRow + " " + nextPosition;
            } else if (prevValid) {
                    res = prevRow + " " + prevPosition;
            }

            try (FileWriter writer = new FileWriter("src/main/java/training_3/division_b/day1/task_04/output.txt")) {
                writer.write(res);
                writer.flush();
            }
        }
    }

}
