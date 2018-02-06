package singleton;

public class Logger {

    private static Logger logger;
    private Logger() {}

    public static Logger getInstance() {
        if (logger == null) {
            logger = new Logger();
        }
        return logger;
    }

    public void info(String message) {
        System.out.println("Info: " + message);
    }

    public void error(String message) {
        System.out.println("Error: " + message);
    }
}
