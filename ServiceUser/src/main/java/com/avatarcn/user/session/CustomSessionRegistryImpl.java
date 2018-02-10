package com.avatarcn.user.session;

import com.avatarcn.user.utils.Constant;
import com.avatarcn.user.utils.HashRedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by z1ven on 2017/10/10 11:19
 */
public class CustomSessionRegistryImpl implements SessionRegistry, ApplicationListener<SessionDestroyedEvent> {

    private static final String SESSIONIDS = Constant.SESSIONIDS;
    private static final String PRINCIPALS = Constant.PRINCIPALS;

    private HashRedisUtils<String, String, SessionInformation> sessionsHashRedisUtils;
    private HashRedisUtils<String, String, Set<String>> principalsHashRedisUtils;

    @Autowired
    private RedisTemplate redisTemplate;

    private final Logger logger = LoggerFactory.getLogger(CustomSessionRegistryImpl.class);

    public CustomSessionRegistryImpl() {
    }

    public List<Object> getAllPrincipals() {
        return new ArrayList<>(this.getPrincipalsKeySet());
    }

    public List<SessionInformation> getAllSessions(Object principal, boolean includeExpiredSessions) {
        Set<String> sessionsUsedByPrincipal =  this.getPrincipals(((UserDetails)principal).getUsername());
        if (sessionsUsedByPrincipal == null) {
            return Collections.emptyList();
        } else {
            List<SessionInformation> list = new ArrayList<>(sessionsUsedByPrincipal.size());
            Iterator var5 = sessionsUsedByPrincipal.iterator();

            while (true) {
                SessionInformation sessionInformation;
                do {
                    do {
                        if (!var5.hasNext()) {
                            return list;
                        }

                        String sessionId = (String) var5.next();
                        sessionInformation = this.getSessionInformation(sessionId);
                    } while (sessionInformation == null);
                } while (!includeExpiredSessions && sessionInformation.isExpired());

                list.add(sessionInformation);
            }
        }
    }

    public SessionInformation getSessionInformation(String sessionId) {
        Assert.hasText(sessionId, "SessionId required as per interface contract");
        return this.getSessionInfo(sessionId);
    }

    public void onApplicationEvent(SessionDestroyedEvent event) {
        String sessionId = event.getId();
        this.removeSessionInformation(sessionId);
    }

    public void refreshLastRequest(String sessionId) {
        Assert.hasText(sessionId, "SessionId required as per interface contract");
        SessionInformation info = this.getSessionInformation(sessionId);
        if (info != null) {
            info.refreshLastRequest();
        }

    }

    public void registerNewSession(String sessionId, Object principal) {
        Assert.hasText(sessionId, "SessionId required as per interface contract");
        Assert.notNull(principal, "Principal required as per interface contract");
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("Registering session " + sessionId + ", for principal " + principal);
        }

        if (this.getSessionInformation(sessionId) != null) {
            this.removeSessionInformation(sessionId);
        }

        this.addSessionInfo(sessionId, new SessionInformation(principal, sessionId, new Date()));

        Set<String> sessionsUsedByPrincipal = this.getPrincipals(principal.toString());
        if (sessionsUsedByPrincipal == null) {
            sessionsUsedByPrincipal = new CopyOnWriteArraySet<>();
            Set<String> prevSessionsUsedByPrincipal = this.putIfAbsentPrincipals(principal.toString(), sessionsUsedByPrincipal);
            if (prevSessionsUsedByPrincipal != null) {
                sessionsUsedByPrincipal = prevSessionsUsedByPrincipal;
            }
        }

        sessionsUsedByPrincipal.add(sessionId);
        this.putPrincipals(principal.toString(), sessionsUsedByPrincipal);
        if (this.logger.isTraceEnabled()) {
            this.logger.trace("Sessions used by '" + principal + "' : " + sessionsUsedByPrincipal);
        }

    }

    public void removeSessionInformation(String sessionId) {
        Assert.hasText(sessionId, "SessionId required as per interface contract");
        SessionInformation info = this.getSessionInformation(sessionId);
        if (info != null) {
            if (this.logger.isTraceEnabled()) {
                this.logger.debug("Removing session " + sessionId + " from set of registered sessions");
            }

            this.removeSessionInfo(sessionId);
            Set<String> sessionsUsedByPrincipal = this.getPrincipals(info.getPrincipal().toString());
            if (sessionsUsedByPrincipal != null) {
                if (this.logger.isDebugEnabled()) {
                    this.logger.debug("Removing session " + sessionId + " from principal's set of registered sessions");
                }

                sessionsUsedByPrincipal.remove(sessionId);
                if (sessionsUsedByPrincipal.isEmpty()) {
                    if (this.logger.isDebugEnabled()) {
                        this.logger.debug("Removing principal " + info.getPrincipal() + " from registry");
                    }

                    this.removePrincipal(((UserDetails)info.getPrincipal()).getUsername());
                }

                if (this.logger.isTraceEnabled()) {
                    this.logger.trace("Sessions used by '" + info.getPrincipal() + "' : " + sessionsUsedByPrincipal);
                }

            }
        }
    }

    public void addSessionInfo(final String sessionId, final SessionInformation sessionInformation) {
        if (sessionsHashRedisUtils == null) {
            sessionsHashRedisUtils = new HashRedisUtils<>(redisTemplate, SESSIONIDS);
        }
        sessionsHashRedisUtils.put(sessionId, sessionInformation);
    }

    public SessionInformation getSessionInfo(final String sessionId) {
        if (sessionsHashRedisUtils == null) {
            sessionsHashRedisUtils = new HashRedisUtils<>(redisTemplate, SESSIONIDS);
        }
        return sessionsHashRedisUtils.get(sessionId);
    }

    public void removeSessionInfo(final String sessionId) {
        if (sessionsHashRedisUtils == null) {
            sessionsHashRedisUtils = new HashRedisUtils<>(redisTemplate, SESSIONIDS);
        }
        sessionsHashRedisUtils.remove(sessionId);
    }

    public Set<String> putIfAbsentPrincipals(final String key, final Set<String> set) {
        if (principalsHashRedisUtils == null) {
            principalsHashRedisUtils = new HashRedisUtils<>(redisTemplate, PRINCIPALS);
        }
        principalsHashRedisUtils.putIfAbsent(key, set);
        return principalsHashRedisUtils.get(key);
    }

    public void putPrincipals(final String key, final Set<String> set) {
        if (principalsHashRedisUtils == null) {
            principalsHashRedisUtils = new HashRedisUtils<>(redisTemplate, PRINCIPALS);
        }
        principalsHashRedisUtils.put(key, set);
    }

    public Set<String> getPrincipals(final String key) {
        if (principalsHashRedisUtils == null) {
            principalsHashRedisUtils = new HashRedisUtils<>(redisTemplate, PRINCIPALS);
        }
        return principalsHashRedisUtils.get(key);
    }

    public Set<String> getPrincipalsKeySet() {
        if (principalsHashRedisUtils == null) {
            principalsHashRedisUtils = new HashRedisUtils<>(redisTemplate, PRINCIPALS);
        }
        return principalsHashRedisUtils.keys();
    }

    public void removePrincipal(final String key) {
        if (principalsHashRedisUtils == null) {
            principalsHashRedisUtils = new HashRedisUtils<>(redisTemplate, PRINCIPALS);
        }
        principalsHashRedisUtils.remove(key);
    }
}
