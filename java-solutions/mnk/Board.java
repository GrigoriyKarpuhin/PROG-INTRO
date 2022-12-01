package mnk;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Board {
    Position getPosition();

    Result makeMove(Move move);

    void createBord();
}
