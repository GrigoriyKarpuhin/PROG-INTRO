package game;

import java.util.Scanner;

public class HumanPlayer implements Player {
    private final Scanner scan;

    public HumanPlayer(Scanner scan) {
        this.scan = scan;
    }

    @Override
    public Move makeMove(Position position) {
        System.out.println(System.lineSeparator() + "Current board");
        System.out.println(position);
        System.out.println(position.getCell() + "'s turn");
        while (true) {
            try {
                String bufR = scan.next();
                String bufC = scan.next();
                int r = Integer.parseInt(bufR);
                int c = Integer.parseInt(bufC);
                Move move = new Move(r, c, position.getCell());
                if (position.isValid(move)) {
                    return move;
                }
                System.out.println("Wrong cell!");
            } catch (Exception e) {
                System.out.println("Write TWO NUMBERS, please!");
            }
            System.out.println("Try again");
        }
    }
}
