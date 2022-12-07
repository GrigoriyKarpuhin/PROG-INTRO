package game;

public class Match {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private final int until;

    public Match(Board board, Player player1, Player player2, int until) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.until = until;
    }

    public int play(boolean log) {
        int score1 = 0;
        int score2 = 0;
        boolean turn = true;
        while (score1 < until && score2 < until) {
            board.createBord();
            final int result;
            if (turn) {
                result = new Game(board, player1, player2).play(log);
            } else {
                result = new Game(board, player2, player1).play(log);
            }
            turn = !turn;
            if (result != 0) {
                if (turn && result == 1 || !turn && result == 2) {
                    ++score2;
                } else {
                    ++score1;
                }
                if (log) {
                    System.out.println(System.lineSeparator() + "Game score: player1: " + score1 + ", player2: " + score2);
                }
            }
        }
        return score1 == until ? 1 : 2;
    }
}
