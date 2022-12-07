package game;

import java.util.Random;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class RandomPlayer implements Player {
    private final Random random;
    public RandomPlayer(final Random random) {
        this.random = random;
    }
    public RandomPlayer() {
        this(new Random());
    }

    @Override
    public Move makeMove(Position position) {
        while (true) {
            final Move move = new Move(
                    random.nextInt(position.getM()),
                    random.nextInt(position.getN()),
                    position.getCell()
            );
            if (position.isValid(move)) {
                return move;
            }
        }
    }
}
