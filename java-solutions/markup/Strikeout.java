package markup;
import java.util.*;
public class Strikeout extends Abstract{

    public Strikeout(List<Markup> symbols) {
        super(symbols);
    }

    protected String mark() {
        return "~";
    }
    protected String lhtml() {
        return "<s>";
    }
    protected String rhtml() {
        return "</s>";
    }
}
