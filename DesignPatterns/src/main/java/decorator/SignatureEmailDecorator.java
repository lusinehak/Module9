package decorator;

public class SignatureEmailDecorator extends ChristmasEmailDecorator {
    public SignatureEmailDecorator(EMail email) {
        super(email);
    }
    @Override
    public String getContent() {
        return super.getContent() + "\n\nRegards,\nLusine";
    }
}
