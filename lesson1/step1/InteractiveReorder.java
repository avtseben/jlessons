package lesson1.step1;

import java.util.Scanner;
import java.util.StringJoiner;

public class InteractiveReorder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String inputLine = scanner.nextLine();

            if (inputLine.equals("")) {
                continue;
            }

            String[] words = inputLine.split(" ");
            int size = words.length;

            StringJoiner stringJoiner = new StringJoiner(" ");
            for (int i = size - 1; i >= 0; i--) {
                stringJoiner.add(words[i]);
            }

            System.out.println("Result " + stringJoiner.toString());
        }
    }
}
