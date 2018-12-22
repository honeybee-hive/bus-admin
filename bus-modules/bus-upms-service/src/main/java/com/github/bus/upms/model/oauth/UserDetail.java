package com.github.bus.upms.model.oauth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.bus.upms.model.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetail extends SysUser implements UserDetails {

    private static final long serialVersionUID = 1L;

    private List<Authorities> authorities;  //权限

    public UserDetail() {
    }

    public UserDetail(String userId, String userCode, String userPassword, String userName, String userSex, Date userBirthday, String userEmail, String userIcard, String userPhone, String userHeadSculpture, String userType, String userStatus, String userRemark, Date createTime, Date updateTime, String createUserId, String updateUserId, Integer flag) {
        super(userId, userCode, userPassword, userName, userSex, userBirthday, userEmail, userIcard, userPhone, userHeadSculpture, userType, userStatus, userRemark, createTime, updateTime, createUserId, updateUserId, flag);
    }

    public void setAuthorities(List<Authorities> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return super.getUserPassword();
    }

    @Override
    public String getUsername() {
        return super.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  //账户是否未过期
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  //账户是否未锁定
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  //凭证(密码)是否未过期
    }

    @Override
    public boolean isEnabled() {
        return true;  //用户是否启用
    }
}
