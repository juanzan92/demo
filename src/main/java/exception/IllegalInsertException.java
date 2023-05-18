package exception;

public class IllegalInsertException extends RuntimeException {
    public IllegalInsertException(String message) {
        super(message);
    }
}
