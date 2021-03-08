package blackjack.view;

public class ErrorView {

    private static final String ERROR_PREFIX = "[ERROR] ";

    public static void printErrorMessage(String message) {
        System.out.println(ERROR_PREFIX + message);
    }
}
