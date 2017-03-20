package authority;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wanghuan on 2017/3/9.
 */
public class AuthticationFailureHandlerImpl implements AuthenticationFailureHandler{
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                httpServletResponse.setHeader("Content-Type","text/html;charset=UTF-8");
                String message="failed";
                byte[] bytemessage=message.getBytes("UTF-8");
                httpServletResponse.getOutputStream().write(bytemessage );
    }
}
