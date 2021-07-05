package edu.wctc;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // I tried thinking of my own questions, but I just couldn't.
        // So I copied these verbatim from the assignment entry in Canvas
        String[] questions = {
                "mild or spicy",
                "tea or coffee",
                "breakfast or brunch",
                "summer or winter",
                "paper or plastic"
        };

        final int BEST = 3;

        Scanner scanner = new Scanner(System.in);

        while(true) {
            int score = 0;
            boolean[] map = { false, false, false, false, false };
            for (int q = 0; q < questions.length; q++) {
                System.out.printf("%s (enter 1 or 2) > ", questions[q]);
                String answer = scanner.nextLine();

                // not really any error handling but this seems pretty efficient for the purposes of this assignment
                if (answer.equals("2")) { score += 1; map[q] = !map[q]; }
            }

            // I could have just use an else for that last else if, but I don't like having a blank else statement when that block is intended for certain conditions
            if (score < BEST) { System.out.println("\nyou prefer life to be calm and organized"); }
            else if (score == BEST) { System.out.println("\nyou prefer a good balance in life"); }
            else if (score > BEST) { System.out.println("\nyou prefer life to be spontaneous and active"); }

            System.out.println("\nresults:");
            for (int q = 0; q < questions.length; q++) { System.out.printf("%s : %dpts.\n", questions[q], map[q] ? 1 : 0); }

            System.out.print("\nplay again? (Y/n)");
            if (!scanner.nextLine().toUpperCase().equals("Y")) {
                System.out.println("\nthank you for playing!");
                break;
            }
        }

        scanner.close();
    }
}