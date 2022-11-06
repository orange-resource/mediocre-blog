package com.ongsat.blog.api.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;

/**
 * 管理员用户表
 * t_admin_user
 */
@TableName("t_admin_user")
@KeySequence("SEQ_TEST")
public class AdminUserPO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * username
     */
    private String username;

    /**
     * password
     */
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}