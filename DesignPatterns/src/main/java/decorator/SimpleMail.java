package decorator;

public class SimpleMail implements EMail {
    private String content;

    public SimpleMail(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
