package com.avatarcn.user.session;

import com.avatarcn.user.exception.ErrorCode;
import com.avatarcn.user.exception.UserErrorCode;
import com.avatarcn.user.response.JsonBean;
import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by z1ven on 2018/2/10 15:21
 * session失效后的(重定向)策略
 */
public final class CustomSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {

    public CustomSessionInformationExpiredStrategy() {
    }

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException, ServletException {
        HttpServletResponse response = sessionInformationExpiredEvent.getResponse();
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(new Gson().toJson(new JsonBean<>(UserErrorCode.SESSION_EXPIRED)));
    }
}
