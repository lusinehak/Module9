package decorator;

public class SimpleEmail implements EMail {
    public String getContent() {
        return "Hello,\nThis is a basic contant.\n";
    }
}
