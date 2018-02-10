package com.avatarcn.user.handler;

import com.avatarcn.user.exception.ErrorCode;
import com.avatarcn.user.response.JsonBean;
import com.avatarcn.user.utils.Constant;
import com.google.gson.Gson;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by z1ven on 2017/10/19 09:59
 */
public class CustomUrlAuthenticationSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler implements AuthenticationSuccessHandler {

    public CustomUrlAuthenticationSuccessHandler() {
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        this.handle(httpServletRequest, httpServletResponse, authentication);
        this.clearAuthenticationAttributes(httpServletRequest);
    }

    protected final void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        }
    }

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String redirect = request.getParameter(Constant.REDIRECT_PARAMETER);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(new Gson().toJson(new JsonBean<>(ErrorCode.SUCCESS, redirect)));
    }
}
