package training_3B.task_23;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

//https://tproger.ru/articles/dynprog-starters/
//
// Решение
// Наивное решение состоит в том, чтобы делить число на 3, пока это возможно, иначе на 2, если это возможно,
// иначе вычитать единицу, и так до тех пор, пока оно не обратится в единицу. Это неверное решение, т.к. оно исключает,
// например, возможность убавить число на единицу, а затем разделить на три, из-за чего на больших числах
// (например, 32718) возникают ошибки.
// Правильное решение заключается в нахождении для каждого числа от 2 до N минимального количества действий на основе
// предыдущих элементов, иначе говоря: F(N) = min(F(N-1), F(N/2), F(N/3)) + 1.
// Следует помнить, что все индексы должны быть целыми.

public class Task_23 {

    public static void main(String[] args) throws IOException {
        var input = "src/main/java/training_3B/task_23/input_8.txt";
        File file = new File(input);
        FileReader fileReader = new FileReader(file);
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            int n = Integer.parseInt(line);

            int[] counts = new int[n + 1];
            counts[1] = 0;

            int min;
            for (int i = 2; i < n + 1; i++) {
                min = counts[i - 1] + 1;
                if (i % 2 == 0) min = Math.min(min, counts[i / 2] + 1);
                if (i % 3 == 0) min = Math.min(min, counts[i / 3] + 1);
                counts[i] = min;
            }

            int i = n;
            StringBuilder sb = new StringBuilder();
            while (i > 1) {
                if (counts[i] == counts[i - 1] + 1) {
                    i--;
                    sb.insert(0, i).insert(0, " ");
                    continue;
                }
                if (i % 2 == 0 && counts[i] == counts[i / 2] + 1) {
                    i /= 2;
                    sb.insert(0, i).insert(0, " ");
                    continue;
                }
                i /= 3;
                sb.insert(0, i).insert(0, " ");
            }
            sb.append(" ").append(n);
            sb.delete(0, 1);

            System.out.println(counts[n]);
            System.out.println(sb);
        }

    }

}
