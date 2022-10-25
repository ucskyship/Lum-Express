package africa.semicolon.lumExpress.security.managers;

import africa.semicolon.lumExpress.security.providers.LumExpressAuthenticationProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LumExpressAuthenticationManager implements AuthenticationManager {

    private LumExpressAuthenticationProvider lumExpressAuthenticationProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return lumExpressAuthenticationProvider.authenticate(authentication);
    }
}
