package africa.semicolon.lumExpress.exceptions;

public class VerificationException extends RuntimeException {
    public VerificationException(String token) {
        super(token);
    }
}
