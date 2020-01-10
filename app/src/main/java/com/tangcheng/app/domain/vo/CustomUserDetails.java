package com.tangcheng.app.domain.vo;

import com.tangcheng.app.domain.entity.RoleDO;
import com.tangcheng.app.domain.entity.UserDO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by tang.cheng on 2017/4/13.
 */
public class CustomUserDetails extends UserDO {
    private List<RoleDO> roles;

    public List<RoleDO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDO> roles) {
        this.roles = roles;
    }

    public Collection<? extends GrantedAuthority> generateAuthorities() {
        //定义权限集合
        List<GrantedAuthority> authorities = new ArrayList<>();
        //当前用户的角色信息集合
        List<RoleDO> roles = this.getRoles();
        //添加角色信息到权限集合
        for (RoleDO role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getType()));
        }
        return authorities;
    }

    public boolean generateAccountNonExpired() {
        return new Date().before(this.getAccountExpired());
    }

    public boolean generateCredentialsNonExpired() {
        return new Date().before(this.getAccountExpired());
    }
}
