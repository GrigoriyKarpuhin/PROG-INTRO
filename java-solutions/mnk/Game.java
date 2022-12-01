package mnk;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Game {
    private final Board board;
    private final Player player1;
    private final Player player2;

    public Game(Board board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
    }

    public int play(boolean log) {
        while (true) {
            final int result1 = makeMove(player1, 1, log);
            if (result1 != -1)  {
                return result1;
            }
            final int result2 = makeMove(player2, 2, log);
            if (result2 != -1)  {
                return result2;
            }
        }
    }

    private int makeMove(Player player, int no, boolean log) {
        Move move;
        try {
            move = player.makeMove(board.getPosition());
        } catch (Exception e) {
            move = new Move(-1, -1, Cell.E);
        }
        final Result result = board.makeMove(move);
        if (log) {
            System.out.println(System.lineSeparator() + "Player: " + no);
            System.out.println(move);
            System.out.println(board);
            System.out.println("Result: " + result);
        }
        return switch (result) {
            case WIN -> no;
            case LOSE -> 3 - no;
            case DRAW -> 0;
            case UNKNOWN -> -1;
        };
    }
}
