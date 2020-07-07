package com.yian.admin.security;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yian.admin.model.SysUser;
import com.yian.admin.sevice.SysUserService;

/**
 * 用户登录认证信息查询
 * @author Louis
 * @date Nov 20, 2018
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserService.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("该用户不存在");
        }
         return new JwtUserDetails(user.getName(), user.getPassword(), user.getSalt());
    }
}