package authority;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Created by wanghuan on 2017/3/14.
 */
public class DemoUserDetails extends DemoUser implements UserDetails {

    public DemoUserDetails(Long id, String name, String password, List<String> role){
        super(id,name,password,role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities () {
        if(super.getRole() == null || super.getRole().size() <1){
            return AuthorityUtils.commaSeparatedStringToAuthorityList("");
        }
        StringBuilder commaBuilder = new StringBuilder();
        for(String role : super.getRole()){
            commaBuilder.append(role).append(",");
        }
        String authorities = commaBuilder.substring(0,commaBuilder.length()-1);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
