package authority;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by wanghuan on 2017/3/8.
 */
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setHeader("Content-Type","text/html;charset=UTF-8");
        String message="success";
        byte[] bytemessage=message.getBytes("UTF-8");
        httpServletResponse.getOutputStream().write(bytemessage );

    }
}
