package singleton;

public class Logger {

    private Logger() {}

    public static void info(String message) {
        System.out.println(message);
    }

    public static void error(String message) {
        System.out.println(message);
        System.exit(1);
    }
}
