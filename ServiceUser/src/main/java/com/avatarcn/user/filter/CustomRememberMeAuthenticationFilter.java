package com.avatarcn.user.filter;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by z1ven on 2017/10/11 16:46
 */
public class CustomRememberMeAuthenticationFilter extends RememberMeAuthenticationFilter {

    private ApplicationEventPublisher eventPublisher;
    private AuthenticationSuccessHandler successHandler;
    private AuthenticationManager authenticationManager;
    private RememberMeServices rememberMeServices;

    private SessionRegistry sessionRegistry;
    private SessionAuthenticationStrategy sessionStrategy = new NullAuthenticatedSessionStrategy();

    public CustomRememberMeAuthenticationFilter(AuthenticationManager authenticationManager, RememberMeServices rememberMeServices, SessionRegistry sessionRegistry) {
        super(authenticationManager, rememberMeServices);
        this.authenticationManager = authenticationManager;
        this.rememberMeServices = rememberMeServices;
        this.sessionRegistry = sessionRegistry;
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;
        if (!request.getServletPath().equals("/login") && SecurityContextHolder.getContext().getAuthentication() == null) {
            Authentication rememberMeAuth = this.rememberMeServices.autoLogin(request, response);
            if (rememberMeAuth != null) {
                try {
                    //注册session并保持凭据
                    sessionRegistry.registerNewSession(request.getSession().getId(), rememberMeAuth.getPrincipal());
                    rememberMeAuth = this.authenticationManager.authenticate(rememberMeAuth);
                    SecurityContextHolder.getContext().setAuthentication(rememberMeAuth);
                    //session策略——不允许重复登录
                    sessionStrategy.onAuthentication(rememberMeAuth, request, response);
                    this.onSuccessfulAuthentication(request, response, rememberMeAuth);
                    if (this.logger.isDebugEnabled()) {
                        this.logger.debug("SecurityContextHolder populated with remember-me token: '" + SecurityContextHolder.getContext().getAuthentication() + "'");
                    }

                    if (this.eventPublisher != null) {
                        this.eventPublisher.publishEvent(new InteractiveAuthenticationSuccessEvent(SecurityContextHolder.getContext().getAuthentication(), this.getClass()));
                    }

                    if (this.successHandler != null) {
                        this.successHandler.onAuthenticationSuccess(request, response, rememberMeAuth);
                        return;
                    }
                } catch (AuthenticationException var8) {
                    if (this.logger.isDebugEnabled()) {
                        this.logger.debug("SecurityContextHolder not populated with remember-me token, as AuthenticationManager rejected Authentication returned by RememberMeServices: '" + rememberMeAuth + "'; invalidating remember-me token", var8);
                    }
                    this.rememberMeServices.loginFail(request, response);
                    this.onUnsuccessfulAuthentication(request, response, var8);
                }
            }
            chain.doFilter(request, response);
        } else {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("SecurityContextHolder not populated with remember-me token, as it already contained: '" + SecurityContextHolder.getContext().getAuthentication() + "'");
            }

            chain.doFilter(request, response);
        }
    }

    public void setEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void setSuccessHandler(AuthenticationSuccessHandler successHandler) {
        this.successHandler = successHandler;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public void setRememberMeServices(RememberMeServices rememberMeServices) {
        this.rememberMeServices = rememberMeServices;
    }

    public void setSessionStrategy(SessionAuthenticationStrategy sessionStrategy) {
        this.sessionStrategy = sessionStrategy;
    }
}
