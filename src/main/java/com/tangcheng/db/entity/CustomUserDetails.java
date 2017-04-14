package com.tangcheng.db.entity;

import org.joda.time.LocalDateTime;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by tang.cheng on 2017/4/13.
 */
public class CustomUserDetails extends UserDo implements UserDetails {
    private List<RoleDo> roles;

    public List<RoleDo> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDo> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //定义权限集合
        List<GrantedAuthority> authorities = new ArrayList<>();
        //当前用户的角色信息集合
        List<RoleDo> roles = this.getRoles();
        //添加角色信息到权限集合
        for (RoleDo role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getType()));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return LocalDateTime.now().toDate().compareTo(this.getAccountExpired()) <= 0;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.getAccountLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return LocalDateTime.now().toDate().compareTo(this.getCredentialsExpired()) <= 0;
    }

    @Override
    public boolean isEnabled() {
        return this.getAccountEnabled();
    }
}
