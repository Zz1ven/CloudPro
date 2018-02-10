package com.avatarcn.user.service;

import com.avatarcn.user.exception.ErrorCodeException;
import com.avatarcn.user.exception.UserErrorCode;
import com.avatarcn.user.mapper.RoleMapper;
import com.avatarcn.user.mapper.UserRoleMapper;
import com.avatarcn.user.model.Role;
import com.avatarcn.user.response.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by z1ven on 2018/2/9 14:27
 */
@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    public Role insert(String name) throws ErrorCodeException {
        Role role = roleMapper.selectByName(name);
        if (role != null) {
            throw new ErrorCodeException(UserErrorCode.ROLE_REPEAT);
        }
        role = new Role();
        role.setName(name);
        roleMapper.insert(role);
        return role;
    }

    public int deleteById(Integer id) throws ErrorCodeException {
        //删除关联用户角色
        userRoleMapper.deleteByRoleId(id);
        if (roleMapper.deleteById(id) != 1) {
            throw new ErrorCodeException(ErrorCodeException.DELETE_ERROR);
        }
        return 1;
    }

    public Role selectById(Integer id) throws ErrorCodeException {
        Role role = roleMapper.selectById(id);
        if (role == null) {
            throw new ErrorCodeException(UserErrorCode.ROLE_NULL);
        }
        return role;
    }

    public PageResponse<Role> selectPage(Integer offset, Integer pageSize) {
        PageResponse<Role> rolePageResponse = new PageResponse<>();
        rolePageResponse.setItem(roleMapper.selectPage(offset, pageSize));
        rolePageResponse.setOffset(offset);
        rolePageResponse.setPageSize(pageSize);
        rolePageResponse.setTotal(roleMapper.count());
        return rolePageResponse;
    }

    public Role update(Integer id, String name) throws ErrorCodeException {
        Role role = roleMapper.selectById(id);
        if (role == null) {
            throw new ErrorCodeException(UserErrorCode.ROLE_NULL);
        }
        if (!role.getName().equals(name)) {
            if (roleMapper.selectByName(name) != null) {
                throw new ErrorCodeException(UserErrorCode.ROLE_REPEAT);
            }
            role.setName(name);
        }
        roleMapper.update(role);
        return role;
    }
}
