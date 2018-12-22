/*
 * UserDetailsServiceImpl  1.0  2018-10-26
 *
 * 沈阳成林健康科技有限公司
 *
 */
package com.github.bus.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

/**
 * [关于类内容的描述]
 *
 * @author zy
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-10-26 zy 初版
 */
//@Service("userDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String finalPassword = bCryptPasswordEncoder.encode("123456");

        return User.withUsername("user_1")
                .password(finalPassword)
                .authorities("USER")
                .build();
    }
}
