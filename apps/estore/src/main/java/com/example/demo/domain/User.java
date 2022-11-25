package com.example.demo.domain;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * 用戶表
 */
public class User {
    private int id; // 用戶編號
    private String username; // 用戶名
    private String password; // 密碼
    private String email; // 郵箱
    private String role; // 角色 默認是user
    private int state; // 是否激活 0 未激活
    private String activecode; // 激活碼 UUID獲取
    private Timestamp updatetime; // 更新時間

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getActivecode() {
        return activecode;
    }

    public void setActivecode(String activecode) {
        this.activecode = activecode;
    }

    public Timestamp getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Timestamp updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 對用戶密碼進行非空驗證
     *
     * @return 如果用戶名、密碼為空，拋出用戶名，密碼為空即可
     */
    public Map<String, String> validation() {
        Map<String, String> map = new HashMap<String, String>();
        if (username == null || username.trim().length() == 0) {
            map.put("regist.username.error", "用戶名不能為空");
        }
        if (password == null || password.trim().length() == 0) {
            map.put("regist.password.error", "密碼不能為空");
        }
        return map;
    }

}
