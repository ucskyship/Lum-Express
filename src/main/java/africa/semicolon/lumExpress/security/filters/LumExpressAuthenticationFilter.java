package africa.semicolon.lumExpress.security.filters;

import africa.semicolon.lumExpress.data.models.LumExpressUser;
import africa.semicolon.lumExpress.security.managers.LumExpressAuthenticationManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
@Slf4j
public class LumExpressAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final LumExpressAuthenticationManager lumExpressAuthenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

//        TODO: 1. create an Authentication object (which contains authentication credentials that isn't authenticated
        var email = request.getParameter("email");
        var password = request.getParameter("password");
        log.info("email::{}, password::{}", email, password);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

//        TODO: 2. use authentication manager to authenticate the user whose auth credentials are now contained within the authentication object
//        TODO: 3. get back the authentication object which has just been authenticated by the authentication manager

        var authenticatedToken = lumExpressAuthenticationManager.authenticate(authenticationToken);

//        TODO: 4. store authentication in securityContext
        if (authenticatedToken != null) {
            var securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authenticationToken);
            return authenticatedToken;
        }
        throw new BadCredentialsException("invalid username and password");
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

    }
}
