package com.avatarcn.user.service;

import com.avatarcn.user.exception.ErrorCodeException;
import com.avatarcn.user.exception.UserErrorCode;
import com.avatarcn.user.mapper.RoleMapper;
import com.avatarcn.user.mapper.UserMapper;
import com.avatarcn.user.mapper.UserRoleMapper;
import com.avatarcn.user.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by z1ven on 2018/2/9 15:37
 */
@Service
public class UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    public UserRole insert(Integer userId, Integer roleId) throws ErrorCodeException {
        if (userMapper.selectById(userId) == null) {
            throw new ErrorCodeException(UserErrorCode.USER_NULL);
        }
        if (roleMapper.selectById(roleId) == null) {
            throw new ErrorCodeException(UserErrorCode.ROLE_NULL);
        }
        if (userRoleMapper.selectByUserIdAndRoleId(userId, roleId) != null) {
            throw new ErrorCodeException(UserErrorCode.USER_ROLE_REPEAT);
        }
        UserRole userRole = new UserRole();
        userRole.setFk_tb_user_id(userId);
        userRole.setFk_tb_role_id(roleId);
        userRoleMapper.insert(userRole);
        return userRole;
    }

    public int deleteById(Integer id) throws ErrorCodeException {
        if (userRoleMapper.deleteById(id) != 1) {
            throw new ErrorCodeException(ErrorCodeException.DELETE_ERROR);
        }
        return 1;
    }

    public UserRole update(Integer id, Integer userId, Integer roleId) throws ErrorCodeException {
        UserRole userRole = userRoleMapper.selectById(id);
        if (userRole == null) {
            throw new ErrorCodeException(UserErrorCode.USER_ROLE_NULL);
        }
        if (userId != null && roleId != null && userRole.getFk_tb_user_id() == userId && userRole.getFk_tb_role_id() == roleId) {
            return userRole;
        }
        if (userId != null && userRole.getFk_tb_user_id() != userId) {
            if (userMapper.selectById(userId) == null) {
                throw new ErrorCodeException(UserErrorCode.USER_NULL);
            }
            userRole.setFk_tb_user_id(userId);
        }
        if (roleId != null && userRole.getFk_tb_role_id() != roleId) {
            if (roleMapper.selectById(roleId) == null) {
                throw new ErrorCodeException(UserErrorCode.ROLE_NULL);
            }
            userRole.setFk_tb_role_id(roleId);
        }
        if (userRoleMapper.selectByUserIdAndRoleId(userRole.getFk_tb_user_id(), userRole.getFk_tb_role_id()) != null) {
            throw new ErrorCodeException(UserErrorCode.USER_ROLE_REPEAT);
        }
        userRoleMapper.update(userRole);
        return userRole;
    }
}
