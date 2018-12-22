package com.github.bus.upms.service.impl;

import com.github.bus.upms.dao.SysMenuMapper;
import com.github.bus.upms.dao.SysUserMapper;
import com.github.bus.upms.model.oauth.Authorities;
import com.github.bus.upms.model.oauth.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * SpringSecurity用户认证
 *
 * @author zcq
 * @date 2018/11/13 16:18
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysMenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserDetail user = userMapper.getUserDetailByUserCode(userName);
        if (user == null) {
            throw new UsernameNotFoundException("账号不存在");
        }
        List<String> authoritys = menuMapper.queryMenuByUserId(user.getUserId());
        user.setAuthorities(getGrantedAuthority(authoritys));
        return user;
    }

    private List<Authorities> getGrantedAuthority(List<String> authoritys) {
        List<Authorities> grantedAuthorities = new ArrayList<>();
        for (String auth : authoritys) {
            Authorities ga = new Authorities();
            ga.setAuthority(auth);
            grantedAuthorities.add(ga);
        }
        return grantedAuthorities;
    }
}
