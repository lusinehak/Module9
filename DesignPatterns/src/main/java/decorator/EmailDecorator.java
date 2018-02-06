package decorator;

public abstract class EmailDecorator implements EMail {
    protected EMail newMail;

    public EmailDecorator(EMail eMail) {
        newMail = eMail;
    }

    public String getContent() {
        return newMail.getContent();
    }
}
