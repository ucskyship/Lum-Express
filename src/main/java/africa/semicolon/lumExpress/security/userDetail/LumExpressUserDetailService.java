package africa.semicolon.lumExpress.security.userDetail;

import africa.semicolon.lumExpress.data.models.LumExpressUser;
import africa.semicolon.lumExpress.exceptions.UserNotFoundException;
import africa.semicolon.lumExpress.services.userService.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LumExpressUserDetailService implements UserDetailsService {

    private final UserServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LumExpressUser user = null;
        try {
            user = userService.getUerByUsername(username);
            return new LumExpressSecureUser(user);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
