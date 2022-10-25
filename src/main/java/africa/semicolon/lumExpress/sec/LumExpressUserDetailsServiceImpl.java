//package africa.semicolon.lumExpress.security;
//
//import africa.semicolon.lumExpress.services.userService.UserServiceImpl;
//import lombok.AllArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class LumExpressUserDetailsServiceImpl implements UserDetailsService {
//
//    private UserServiceImpl userService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        var foundUser = userService.getUerByUsername(username);
//        return new LumExpressSecureUser(foundUser);
//    }
//}
