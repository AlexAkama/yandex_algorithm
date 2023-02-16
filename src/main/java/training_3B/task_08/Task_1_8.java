package training_3B.task_08;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Task_1_8 {

    public static void main(String[] args) throws IOException {
        var input = "src/main/java/training_3/division_b/day1/task_08/input1.txt";
        File file = new File(input);
        FileReader fileReader = new FileReader(file);
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            int k = Integer.parseInt(line);
            line = reader.readLine();
            String[] split = line.split(" ");
            int minX = Integer.parseInt(split[0]);
            int maxX = minX;
            int minY = Integer.parseInt(split[1]);
            int maxY = minY;
            for (int i = 0; i < k - 1; i++) {
                line = reader.readLine();
                split = line.split(" ");
                int newX = Integer.parseInt(split[0]);
                int newY = Integer.parseInt(split[1]);
                if (newX < minX) minX = newX;
                else if (newX > maxX) maxX = newX;
                if (newY < minY) minY = newY;
                else if (newY > maxY) maxY = newY;
            }

            String res = minX + " " + minY + " " + maxX + " " + maxY;
            try (FileWriter writer = new FileWriter("src/main/java/training_3/division_b/day1/task_08/output.txt")) {
                writer.write(res);
                writer.flush();
            }
        }
    }

}
