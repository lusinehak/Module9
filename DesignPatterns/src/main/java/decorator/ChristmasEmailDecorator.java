package decorator;

public class ChristmasEmailDecorator extends EmailDecorator {
    public ChristmasEmailDecorator(EMail eMail) {
        super(eMail);
    }

    @Override
    public String getContent() {
        return super.getContent() + "\nMarry Christmas!!!!";
    }
}
