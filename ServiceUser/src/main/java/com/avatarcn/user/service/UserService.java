package com.avatarcn.user.service;

import com.avatarcn.user.exception.ErrorCodeException;
import com.avatarcn.user.exception.UserErrorCode;
import com.avatarcn.user.mapper.UserMapper;
import com.avatarcn.user.mapper.UserRoleMapper;
import com.avatarcn.user.model.Role;
import com.avatarcn.user.model.User;
import com.avatarcn.user.model.UserRole;
import com.avatarcn.user.response.PageResponse;
import com.avatarcn.user.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by z1ven on 2018/2/9 14:27
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    public User insert(String username, String password) throws ErrorCodeException {
        User user = userMapper.selectByUsername(username);
        if (user != null) {
            throw new ErrorCodeException(UserErrorCode.USER_REPEAT);
        }
        user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setLocked(Constant.USER_LOCKED);
        user.setEnable(Constant.USER_ENABLE);
        user.setTime(new Date());
        userMapper.insert(user);
        return user;
    }

    public int deleteById(Integer id) throws ErrorCodeException {
        //删除关联的用户角色
        userRoleMapper.deleteByUserId(id);
        if (userMapper.deleteById(id) != 1) {
            throw new ErrorCodeException(ErrorCodeException.DELETE_ERROR);
        }
        return 1;
    }

    public User selectById(Integer id) throws ErrorCodeException {
        User user = userMapper.selectById(id);
        if (user == null) {
            throw new ErrorCodeException(UserErrorCode.USER_NULL);
        }
        user.setUserRoles(userRoleMapper.selectByUserId(user.getId()));
        return user;
    }

    /**
     * spring security 验证用户信息
     * @param username
     * @return
     * @throws ErrorCodeException
     */
    public User selectByUsername(String username) throws ErrorCodeException {
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            throw new ErrorCodeException(UserErrorCode.USER_NULL);
        }
        user.setUserRoles(userRoleMapper.selectByUserId(user.getId()));
        return user;
    }

    public PageResponse<User> selectPage(Integer offset, Integer pageSize) {
        PageResponse<User> userPageResponse = new PageResponse<>();
        userPageResponse.setItem(userMapper.selectPage(offset, pageSize));
        userPageResponse.setOffset(offset);
        userPageResponse.setPageSize(pageSize);
        userPageResponse.setTotal(userMapper.count());
        return userPageResponse;
    }

    public User update(Integer id, String username, String password, Boolean locked, Boolean enable) throws ErrorCodeException {
        User user = userMapper.selectById(id);
        if (user == null) {
            throw new ErrorCodeException(UserErrorCode.USER_NULL);
        }
        if (username != null && !username.isEmpty() && !username.equals(user.getUsername())) {
            if (userMapper.selectByUsername(username) != null) {
                throw new ErrorCodeException(UserErrorCode.USER_REPEAT);
            }
            user.setUsername(username);
        }
        if (password != null && !password.isEmpty() && !password.equals(user.getPassword())) {
            user.setPassword(password);
        }
        if (locked != null && (locked ^ user.isLocked())) {
            user.setLocked(locked);
        }
        if (enable != null && (enable ^ user.isEnable())) {
            user.setEnable(enable);
        }
        userMapper.update(user);
        return user;
    }
}
