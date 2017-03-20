package authority;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by wanghuan on 2017/3/14.
 */
//自定义AuthenticationProvider
public class DemoAuthenticationProvider  implements AuthenticationProvider {
    @Autowired
    private DemoUserService demoUserService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name=authentication.getName();
        String password= (String) authentication.getCredentials();
        DemoUserDetails user= (DemoUserDetails) demoUserService.loadUserByUsername(name);
        if (user==null){
            throw new BadCredentialsException("User not found");
        }
        if (!password.equals(user.getPassword())){
            throw new BadCredentialsException("Wrong Password");
        }

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
