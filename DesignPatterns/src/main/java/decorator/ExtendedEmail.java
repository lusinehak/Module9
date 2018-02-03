package decorator;

import templates.MailTemplates;

public class ExtendedEmail extends SimpleMail {
    public ExtendedEmail(String content) {
        super(content);
    }

    @Override
    public String getContent() {
        return super.getContent();
    }

    public String setGreetingsAndSignature(String name, String signiture) {
        String s = String.format(MailTemplates.signature, signiture);
        String g = String.format(MailTemplates.greetings, name);
        return g + "\n\n" + getContent() + "\n\n" + s;
    }
}
