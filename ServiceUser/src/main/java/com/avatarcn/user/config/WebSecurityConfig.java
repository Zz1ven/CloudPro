package com.avatarcn.user.config;

import com.avatarcn.user.filter.CustomRememberMeAuthenticationFilter;
import com.avatarcn.user.filter.CustomUsernamePasswordAuthenticationFilter;
import com.avatarcn.user.handler.CustomUrlAuthenticationFailureHandler;
import com.avatarcn.user.handler.CustomUrlAuthenticationSuccessHandler;
import com.avatarcn.user.service.CustomUserService;
import com.avatarcn.user.session.CustomConcurrentSessionControlAuthenticationStrategy;
import com.avatarcn.user.session.CustomSessionInformationExpiredStrategy;
import com.avatarcn.user.session.CustomSessionRegistryImpl;
import com.avatarcn.user.utils.Constant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.security.web.session.SimpleRedirectSessionInformationExpiredStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by z1ven on 2018/2/9 10:51
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private SessionRegistry sessionRegistry;

    @Resource
    private RememberMeServices rememberMeServices;

    /**
     * 认证成功后，从数据库加载对应用户的权限，供之后的资源访问时做比对
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserService();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new CustomSessionRegistryImpl();
    }

    /**
     * 基于token的RememberMeServices接口实现
     * <1>用户认证成功后会自动调用service的loginSuccess方法，生成token并保存到cookie中，返回客户端
     * <2>认证过程中不会调用autoLogin方法进行登录
     * <3>任然未登录，使用RememberMeAuthenticationFilter调用autoLogin方法自动登录
     * @return
     */
    @Bean
    public RememberMeServices rememberMeServices() {
        TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices(Constant.TOKEN_KEY, userDetailsService);
        rememberMeServices.setTokenValiditySeconds(60 * 60 * 24 * 2);
        rememberMeServices.setParameter(Constant.REMEMBER_PARAMETER);
        rememberMeServices.setCookieName(Constant.COOKIE_NAME);
        return rememberMeServices;
    }

    /**
     * 过滤忽略拦截的链接
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/register");
        containsSuffix(web);
    }

    private void containsSuffix(WebSecurity web) {
        web.ignoring()
                .regexMatchers(".+(.js|.css|.jpg|.png|.gif|.html|.eot|.svg|.ttf|.woff|.ico|.woff2)$");
    }

    /**
     * 认证规则
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        auth.userDetailsService(userDetailsService);
        auth.eraseCredentials(false);//不删除凭证
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        //添加认证过滤器
        http.addFilterAt(usernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        //remember-me
        http.addFilterAt(rememberMeAuthenticationFilter(),RememberMeAuthenticationFilter.class).authenticationProvider(new RememberMeAuthenticationProvider(Constant.TOKEN_KEY));
        //添加session并发控制过滤器
        http.addFilterAt(new ConcurrentSessionFilter(sessionRegistry, sessionInformationExpiredStrategy()), ConcurrentSessionFilter.class);
        //添加安全过滤器
        http.addFilterAt(filterSecurityInterceptor(), FilterSecurityInterceptor.class);

        LogoutHandler logoutHandler = (LogoutHandler) rememberMeServices;//登出时清除cookie

        http.exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
                .and().logout().logoutUrl("/logout").addLogoutHandler(logoutHandler).permitAll();
        http.authorizeRequests().anyRequest().fullyAuthenticated();

        http.csrf().disable();//关闭csrf,跨站请求伪造（cross-site request forgery，CSRF）
        //默认开启CSRF防止从其他网站请求接口
    }

    /**
     * 自定义的认证过滤器
     * @return usernamePasswordAuthenticationFilter
     */
    private UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter() throws Exception {
        UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter = new CustomUsernamePasswordAuthenticationFilter(sessionRegistry);
        //登录认证的相关设置
        usernamePasswordAuthenticationFilter.setPostOnly(true);
        usernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        usernamePasswordAuthenticationFilter.setUsernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY);
        usernamePasswordAuthenticationFilter.setPasswordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY);
        usernamePasswordAuthenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
        usernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(new CustomUrlAuthenticationSuccessHandler());
        usernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(new CustomUrlAuthenticationFailureHandler());
        //session并发认证策略——共享redis最大在线数设置
        usernamePasswordAuthenticationFilter.setSessionAuthenticationStrategy(new CustomConcurrentSessionControlAuthenticationStrategy(sessionRegistry, 1));
        //remember-me登录成功自动生成cookie
        usernamePasswordAuthenticationFilter.setRememberMeServices(rememberMeServices);

        return usernamePasswordAuthenticationFilter;
    }

    /**
     * 自定义的remember-me过滤器
     * <1>当cookie存在时，自动验证cookie登录
     * <2>验证通过后添加session到redis spring session中
     * <3>需要设置RememberMeAuthenticationProvider到AuthenticationManager中
     * <4>设置验证成功后的重定向
     * @return
     */
    private RememberMeAuthenticationFilter rememberMeAuthenticationFilter() throws Exception {
        CustomRememberMeAuthenticationFilter rememberMeAuthenticationFilter = new CustomRememberMeAuthenticationFilter(authenticationManagerBean(), rememberMeServices, sessionRegistry);
        rememberMeAuthenticationFilter.setSessionStrategy(new CustomConcurrentSessionControlAuthenticationStrategy(sessionRegistry, 1));
        return rememberMeAuthenticationFilter;
    }

    /**
     * session失效后的跳转
     * @return sessionInformationExpiredStrategy
     */
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy() {
        return new CustomSessionInformationExpiredStrategy();
    }

    /**
     * 自定义的http安全过滤器
     * @return filterSecurityInterceptor
     */
    private FilterSecurityInterceptor filterSecurityInterceptor() throws Exception {
        FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
        filterSecurityInterceptor.setSecurityMetadataSource(securityMetadataSource());
        filterSecurityInterceptor.setAccessDecisionManager(accessDecisionManager());
        filterSecurityInterceptor.setAuthenticationManager(authenticationManagerBean());
        return filterSecurityInterceptor;
    }

    /**
     * 认证管理器，对用户名/密码进行认证比对
     * @return
     * @throws Exception
     */
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        AuthenticationManager authenticationManager = null;
        try {
            authenticationManager = super.authenticationManagerBean();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authenticationManager;
    }

    /**
     * 资源元数据
     * 启动时加载系统所有的资源权限列表
     * @return
     */
    @Bean
    public FilterInvocationSecurityMetadataSource securityMetadataSource() {
        LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap = new LinkedHashMap<>();
        Collection<ConfigAttribute> data = new ArrayList<>();
        //配置需要角色权限的请求路径
        //data.add(new SecurityConfig("ROLE_USER"));
        //requestMap.put(new AntPathRequestMatcher("/user/page"), data);//url作为key,角色权限作为value
        return new DefaultFilterInvocationSecurityMetadataSource(requestMap);
    }

    /**
     * 投票器
     * 对用户访问资源与用户拥有权限进行比对，获取用户是否有权限访问该资源
     */
    private AbstractAccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<?>> decisionVoters = new ArrayList<>();
        decisionVoters.add(new AuthenticatedVoter());
        decisionVoters.add(new RoleVoter());//角色投票器,默认前缀为ROLE_

        /*RoleVoter AuthVoter = new RoleVoter();
        AuthVoter.setRolePrefix("AUTH_");//特殊权限投票器,修改前缀为AUTH_
        decisionVoters.add(AuthVoter);*/
        return new AffirmativeBased(decisionVoters);
    }

    /**
     * session监听器
     * @return httpSessionEventPublisher
     */
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
