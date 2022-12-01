package mnk;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Position {
    Cell getCell();

    boolean isValid(Move move);

    int getM();

    int getN();
}
