package markup;
import java.util.*;
public class Paragraph extends Abstract{

    public Paragraph(List<Markup> symbols) {
        super(symbols);
    }

    protected String mark() {
        return "";
    }
    protected String lhtml() {
        return "";
    }
    protected String rhtml() {
        return "";
    }
}
