package markup;

public class Text implements Markup{
    private final String text;

    public Text(String text) {
        this.text = text;
    }

    public void toMarkdown(StringBuilder build){
        build.append(text);
    }
    public void toHtml(StringBuilder build){
        build.append(text);
    }
}
