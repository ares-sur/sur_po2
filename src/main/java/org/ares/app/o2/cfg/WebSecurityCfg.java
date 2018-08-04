package org.ares.app.o2.cfg;

import javax.annotation.Resource;

import org.ares.app.o2.security.rbac.spring.UserDetailsServiceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityCfg extends WebSecurityConfigurerAdapter {

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .requestMatchers().anyRequest()
            .and().authorizeRequests().antMatchers("/oauth/*").permitAll();
    }
	
	@Bean
    UserDetailsService ucJdbcUserService(){
        return new UserDetailsServiceBean();
    }
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jdbcUserDetailsService);
    }
	
	@Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean() ;
    }
	
	@Resource UserDetailsService jdbcUserDetailsService;
	
}
