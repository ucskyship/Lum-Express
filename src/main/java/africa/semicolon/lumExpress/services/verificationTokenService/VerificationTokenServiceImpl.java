package africa.semicolon.lumExpress.services.verificationTokenService;

import africa.semicolon.lumExpress.data.models.VerificationToken;
import africa.semicolon.lumExpress.utils.LumExpressUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class VerificationTokenServiceImpl implements iVerificationTokenService{

    @Override
    public VerificationToken generateVerificationToken(String userEmail) {
        var tokenString = LumExpressUtils.generateToken();
        var verificationToken = VerificationToken
                .builder()
                .token(tokenString)
                .userEmail(userEmail)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusSeconds(120))
                .build();
        return verificationToken;
    }
}
