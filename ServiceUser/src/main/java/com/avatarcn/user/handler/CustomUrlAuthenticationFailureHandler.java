package com.avatarcn.user.handler;

import com.avatarcn.user.exception.UserErrorCode;
import com.avatarcn.user.response.JsonBean;
import com.google.gson.Gson;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by z1ven on 2017/10/19 10:31
 */
public class CustomUrlAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private boolean forwardToDestination = false;
    private boolean allowSessionCreation = true;

    public CustomUrlAuthenticationFailureHandler() {
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        this.saveException(httpServletRequest, e);
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.getWriter().write(new Gson().toJson(new JsonBean<>(UserErrorCode.USER_ERROR)));
    }

    protected final void saveException(HttpServletRequest request, AuthenticationException exception) {
        if (this.forwardToDestination) {
            request.setAttribute("SPRING_SECURITY_LAST_EXCEPTION", exception);
        } else {
            HttpSession session = request.getSession(false);
            if (session != null || this.allowSessionCreation) {
                request.getSession().setAttribute("SPRING_SECURITY_LAST_EXCEPTION", exception);
            }
        }

    }

    public boolean isForwardToDestination() {
        return forwardToDestination;
    }

    public void setForwardToDestination(boolean forwardToDestination) {
        this.forwardToDestination = forwardToDestination;
    }

    public boolean isAllowSessionCreation() {
        return allowSessionCreation;
    }

    public void setAllowSessionCreation(boolean allowSessionCreation) {
        this.allowSessionCreation = allowSessionCreation;
    }
}
