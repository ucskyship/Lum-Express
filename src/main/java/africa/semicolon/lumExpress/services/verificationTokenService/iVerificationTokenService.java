package africa.semicolon.lumExpress.services.verificationTokenService;

import africa.semicolon.lumExpress.data.models.VerificationToken;

public interface iVerificationTokenService {
    VerificationToken createToken(String userEmail);
    boolean isValidVerificationToken(String token);
}
