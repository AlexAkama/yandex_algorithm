package training_3B.task_03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Task_03 {

    public static void main(String[] args) throws IOException {

        var input = "src/main/java/training_3B/task_03/input.txt";
        File file = new File(input);
        FileReader fileReader = new FileReader(file);

        try (BufferedReader reader = new BufferedReader(fileReader)) {

            String line = reader.readLine();
            int n = Integer.parseInt(line);

            var diegoCardSource = reader.readLine();

            line = reader.readLine();
            int k = Integer.parseInt(line);

            var collectorsSource = reader.readLine();

            if (n == 0) {
                for (var v : new int[k])
                    System.out.println(v);
                return;
            }

            if (k == 0) return;

            StringTokenizer st = new StringTokenizer(diegoCardSource, " ");
            var diegoCardSet = new TreeSet<Integer>();
            while (st.hasMoreTokens()) diegoCardSet.add(Integer.parseInt(st.nextToken()));
            var diegoCards = new int[diegoCardSet.size()];
            int count = 0;
            for (Integer cardValue : diegoCardSet) diegoCards[count++] = cardValue;

            st = new StringTokenizer(collectorsSource, " ");
            var requirement = new int[k];
            count = 0;
            while (st.hasMoreTokens()) requirement[count++] = Integer.parseInt(st.nextToken());

            try (FileWriter writer = new FileWriter("src/main/java/training_3B/task_03/output.txt")) {
                for (var max : requirement) {
                    int left = 0;
                    int right = diegoCards.length - 1;
                    while (left <= right) {
                        int mid = (left + right) / 2;
                        if (diegoCards[mid] >= max) {
                            right = mid - 1;
                        } else {
                            left = mid + 1;
                        }
                    }
                    writer.write(String.valueOf(left));
                    writer.write("\n");
                }
                writer.flush();
            }
        }
    }

}
