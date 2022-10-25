package africa.semicolon.lumExpress.services.verificationTokenService;

import africa.semicolon.lumExpress.data.models.VerificationToken;
import africa.semicolon.lumExpress.exceptions.VerificationTokenException;

public interface iVerificationTokenService {
    VerificationToken createToken(String userEmail);
    boolean isValidVerificationToken(String token) throws VerificationTokenException;
}
