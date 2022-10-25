package africa.semicolon.lumExpress.exceptions;

public class VerificationTokenException extends LumExpressException {
    public VerificationTokenException(String token) {
        super(token);
    }
}
