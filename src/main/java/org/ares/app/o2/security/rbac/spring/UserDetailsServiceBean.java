package org.ares.app.o2.security.rbac.spring;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.ares.app.o2.security.rbac.dao.RbacMapper;
import org.ares.app.o2.security.rbac.model.SUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceBean implements UserDetailsService {

	@Override  
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SUser cu=rm.getUserByUsername(username);
        if (cu == null)
			throw new UsernameNotFoundException("username not found");
        UserDetailsModel user = new UserDetailsModel();
        user.setUsername(username);
        user.setPassword(cu.getPassword());
        Set<GrantedAuthority> auth = new HashSet<>();
        cu.getRoles().stream().forEach(e->auth.add(new SimpleGrantedAuthority(e.getRolename())));
		user.setAuthorities(auth);
        return user;
    }  

	final Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource RbacMapper rm;
}
