package util;

public class TetrisException extends RuntimeException {

    public TetrisException() {
    }

    public TetrisException(String message) {
        super(message);
    }

    public TetrisException(String message, Exception ex) {
        super(message, ex);
    }
}
