package markup;

public interface Markup {
    void toMarkdown(StringBuilder build);
    void toHtml(StringBuilder build);
}
