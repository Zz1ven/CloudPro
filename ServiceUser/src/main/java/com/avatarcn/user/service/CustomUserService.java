package com.avatarcn.user.service;

import com.avatarcn.user.exception.ErrorCodeException;
import com.avatarcn.user.mapper.UserMapper;
import com.avatarcn.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by z1ven on 2018/2/9 10:58
 */
public class CustomUserService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user;
        try {
            user = userService.selectByUsername(s);
        } catch (ErrorCodeException e) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return user;
    }
}
