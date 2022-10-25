package africa.semicolon.lumExpress.security.providers;

import africa.semicolon.lumExpress.security.userDetail.LumExpressUserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
public class LumExpressAuthenticationProvider implements AuthenticationProvider {

    private final LumExpressUserDetailService lumExpressUserDetailService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var userDetails = lumExpressUserDetailService.loadUserByUsername((String)authentication.getPrincipal());
        if (userDetails != null) {
            if (passwordEncoder.matches((String)authentication.getCredentials(), userDetails.getPassword())){
                UsernamePasswordAuthenticationToken authenticatedToken =
                        new UsernamePasswordAuthenticationToken(
                                authentication.getPrincipal(),
                                userDetails.getAuthorities());
                return authenticatedToken;
            }
            throw new BadCredentialsException("password incorrect");
        }
        throw new AuthenticationCredentialsNotFoundException("email does not exist");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        var appAuthType = UsernamePasswordAuthenticationToken.class;
        return authentication.equals(appAuthType);
    }
}
