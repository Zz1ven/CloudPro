package com.avatarcn.user.model;

/**
 * Created by z1ven on 2018/2/9 14:05
 */
public class UserRole {
    private int id;
    private int fk_tb_user_id;
    private int fk_tb_role_id;

    private Role role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFk_tb_user_id() {
        return fk_tb_user_id;
    }

    public void setFk_tb_user_id(int fk_tb_user_id) {
        this.fk_tb_user_id = fk_tb_user_id;
    }

    public int getFk_tb_role_id() {
        return fk_tb_role_id;
    }

    public void setFk_tb_role_id(int fk_tb_role_id) {
        this.fk_tb_role_id = fk_tb_role_id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
