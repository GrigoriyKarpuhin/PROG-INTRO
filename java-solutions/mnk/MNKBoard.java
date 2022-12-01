package mnk;

import java.util.Arrays;
import java.util.Map;

public class MNKBoard implements Board, Position {

    private static final Map<mnk.Cell, Character> SYMBOLS = Map.of(
            mnk.Cell.X, 'X',
            mnk.Cell.O, 'O',
            mnk.Cell.E, '.'
    );

    private final mnk.Cell[][] cells;
    private mnk.Cell turn;

    private final int m;
    private final int n;
    private final int k;

    public MNKBoard(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        cells = new Cell[m][n];
        createBord();
    }

    public void createBord() {
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Result makeMove(Move move) {
        if (!isValid(move)) {
            return mnk.Result.LOSE;
        }
        cells[move.getRow()][move.getColumn()] = move.getValue();
        int empty = m * n;
        empty--;
        int Diag1 = count(move, -1, -1) + count(move, 1, 1) - 1;
        int Diag2 = count(move, -1, 1) + count(move, 1, -1) - 1;
        int inRow = count(move, 0, -1) + count(move, 0, 1) - 1;
        int inCol = count(move, -1, 0) + count(move, 1, 0) - 1;

        if (Diag1 >= k || Diag2 >= k || inRow >= k || inCol >= k) {
            return Result.WIN;
        }
        if (empty == 0) {
            return Result.DRAW;
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }

    private int count(Move move, final int increaseRow, final int increaseColumn) {
        int row = move.getRow();
        int col = move.getColumn();
        int result = 0;

        while (0 <= col && col < n
                && 0 <= row && row < m
                && cells[row][col] == turn) {
            row += increaseRow;
            col += increaseColumn;
            result++;
        }

        return result;
    }


    @Override
    public boolean isValid(Move move) {
        return 0 <= move.getRow() && move.getRow() < m
                && 0 <= move.getColumn() && move.getColumn() < n
                && cells[move.getRow()][move.getColumn()] == mnk.Cell.E
                && turn == getCell();
    }

    @Override
    public int getM() {
        return m;
    }

    @Override
    public int getN() {
        return n;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(" ");
        for (int i = 0; i < m; i++) {
            builder.append(" ").append(i);
        }
        for (int r = 0; r < n; r++) {
            builder.append("\n");
            builder.append(r);
            for (int c = 0; c < m; c++) {
                builder.append(" ").append(SYMBOLS.get(cells[c][r]));
            }
        }
        return builder.toString();
    }
}
