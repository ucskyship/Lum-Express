//package africa.semicolon.lumExpress.security;
//
//import africa.semicolon.lumExpress.data.models.Authority;
//import africa.semicolon.lumExpress.data.models.LumExpressUser;
//import lombok.AllArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//@AllArgsConstructor
//public class LumExpressSecureUser implements UserDetails {
//
//    private final LumExpressUser lumExpressUser;
//
//    @Override
//    public String getPassword() {
//        return lumExpressUser.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return lumExpressUser.getEmail();
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        lumExpressUser.getAuthorities().forEach(authority ->
//            addUserAuthoritiesToAuthoritiesList(authorities, authority));
//        return authorities;
//    }
//
//    private static void addUserAuthoritiesToAuthoritiesList(List<SimpleGrantedAuthority> authorities, Authority authority) {
//        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority.name());
//        authorities.add(simpleGrantedAuthority);
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
