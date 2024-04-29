package utilities;

public class AutomationUtils {

    public static void sleep(long timeMs) {
        try {
            Logs.info("Waiting for %d ms", timeMs);
            Thread.sleep(timeMs);
        } catch(InterruptedException interruptedException) {
            Logs.error("Error after waiting: %s", interruptedException.getLocalizedMessage());
        }
    }
}
