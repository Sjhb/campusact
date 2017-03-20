package authority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.Arrays;

/**
 * Created by wanghuan on 2017/3/14.
 */
@Service("DemoUserDetailsImpl")
public class DemoUserService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        DemoUserDetails user=new DemoUserDetails(1L,"王欢","password", Arrays.asList(new String[]{"ROLE_ADMIN", "ROLE_USER"}));
        return user;
    }
}
