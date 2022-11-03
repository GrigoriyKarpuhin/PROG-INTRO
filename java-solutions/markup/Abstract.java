package markup;
import java.util.*;
public abstract class Abstract implements Markup{
    protected final List<Markup> symbols;

    protected Abstract(List<Markup> symbols) {
        this.symbols = symbols;
    }
    protected abstract String mark();
    protected abstract String lhtml();
    protected abstract String rhtml();
    public void toMarkdown(StringBuilder build){
        build.append(mark());
        for (int i = 0; i < symbols.size(); i++) {
            Markup line = symbols.get(i);
            line.toMarkdown(build);
        }
        build.append(mark());
    }
    public void toHtml(StringBuilder build){
        build.append(lhtml());
        for (int i = 0; i < symbols.size(); i++) {
            Markup line = symbols.get(i);
            line.toHtml(build);
        }
        build.append(rhtml());
    }
}
