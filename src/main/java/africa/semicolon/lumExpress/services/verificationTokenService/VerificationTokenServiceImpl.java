package africa.semicolon.lumExpress.services.verificationTokenService;

import africa.semicolon.lumExpress.data.models.VerificationToken;
import africa.semicolon.lumExpress.data.repositories.VerificationTokenRepository;
import africa.semicolon.lumExpress.exceptions.VerificationTokenException;
import africa.semicolon.lumExpress.utils.LumExpressUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class VerificationTokenServiceImpl implements iVerificationTokenService{
    private final VerificationTokenRepository verificationTokenRepository;

    @Override
    public VerificationToken createToken(String userEmail) {
        var tokenString = LumExpressUtils.generateToken();
        var verificationToken = VerificationToken
                .builder()
                .token(tokenString)
                .userEmail(userEmail)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusSeconds(120))
                .build();
        return verificationTokenRepository.save(verificationToken);
    }

    @Override
    public boolean isValidVerificationToken(String token) throws VerificationTokenException {
        var foundToken = verificationTokenRepository.findByToken(token).orElseThrow(
                ()-> new VerificationTokenException("token not found"));
        if(isTokenNotExpired(foundToken)) return true;
        throw new VerificationTokenException("token has expired");
    }

    private boolean isTokenNotExpired(VerificationToken verificationToken) {
        return LocalDateTime.now().isBefore(verificationToken.getExpiresAt());
    }
}
