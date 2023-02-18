package training_3B.task_17;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

public class Task_17 {

    public static void main(String[] args) throws IOException {
        var input = "src/main/java/training_3B/task_17/input_3.txt";
        File file = new File(input);
        FileReader fileReader = new FileReader(file);
        try (BufferedReader reader = new BufferedReader(fileReader)) {

            Deque<Integer> player1 = new ArrayDeque<>();
            Deque<Integer> player2 = new ArrayDeque<>();

            String line = reader.readLine();
            String[] source = line.split(" ");
            for (String s : source) player1.addLast(Integer.parseInt(s));

            line = reader.readLine();
            source = line.split(" ");
            for (String s : source) player2.addLast(Integer.parseInt(s));

            String res = "botva";
            int limit = 1_000_000;
            int count = 0;
            while (count++ < limit) {
                int card1 = player1.pollFirst();
                int card2 = player2.pollFirst();
                int winner = winCard(card1, card2);
                if (winner == 1) {
                    player1.addLast(card1);
                    player1.addLast(card2);
                } else {
                    player2.addLast(card1);
                    player2.addLast(card2);
                }
                if (player1.isEmpty() || player2.isEmpty()) {
                    if (player1.isEmpty()) res = "second " + count;
                    if (player2.isEmpty()) res = "first " + count;
                    break;
                }
            }
            System.out.println(res);
        }
    }

    private static int winCard(int card1, int card2) {
        if (card1 == 0 && card2 == 9) return 1;
        if (card1 == 9 && card2 == 0) return 2;
        return (card1 > card2) ? 1 : 2;
    }

}
