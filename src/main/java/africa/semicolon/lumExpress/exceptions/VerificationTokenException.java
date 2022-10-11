package africa.semicolon.lumExpress.exceptions;

public class VerificationTokenException extends RuntimeException {
    public VerificationTokenException(String token) {
        super(token);
    }
}
