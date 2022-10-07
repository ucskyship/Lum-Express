package africa.semicolon.lumExpress.services.verificationTokenService;

import africa.semicolon.lumExpress.data.models.VerificationToken;

public interface iVerificationTokenService {
    VerificationToken generateVerificationToken(String userEmail);
}
