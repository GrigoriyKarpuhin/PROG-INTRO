package markup;
import java.util.*;
public class Strong extends Abstract{

    public Strong(List<Markup> symbols) {
        super(symbols);
    }

    protected String mark() {
        return "__";
    }
    protected String lhtml() {
        return "<strong>";
    }
    protected String rhtml() {
        return "</strong>";
    }
}
