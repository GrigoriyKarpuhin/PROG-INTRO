package markup;
import java.util.*;
public class Emphasis extends Abstract implements Markup{

    public Emphasis(List<Markup> symbols) {
        super(symbols);
    }

    protected String mark() {
        return "*";
    }
    protected String lhtml() {
        return "<em>";
    }
    protected String rhtml() {
        return "</em>";
    }
}
