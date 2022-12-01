package mnk;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int m = scan.nextInt();
        int n = scan.nextInt();
        int k = scan.nextInt();
        int until = scan.nextInt();
        final int result = new Match(
                new MNKBoard(m, n, k),
                new RandomPlayer(),
                new HumanPlayer(new Scanner(System.in)), until)
                .play(true);
        switch (result) {
            case 1 -> System.out.println("First win");
            case 2 -> System.out.println("Second win");
            case 0 -> System.out.println("Draw");
            default -> throw new AssertionError("Invalid input");
        }
    }
}
