/*package com.macs.groupone.friendbookapplication;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
 

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
  @Autowired
  DataSource dataSource;
 
  @Autowired
  public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    auth.jdbcAuthentication().dataSource(dataSource)
    .authoritiesByUsernameQuery("select email, password from users where email=? and password=?")
    .usersByUsernameQuery("select email, password from users where email=?");
}
  
  @Override
  public void configure(WebSecurity web) throws Exception {
      super.configure(web);

      web
              .ignoring()
              .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");


  }

 
  @Override
  protected void configure(HttpSecurity http) throws Exception {
   http.authorizeRequests().antMatchers("/", "/login").permitAll();
     // .anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll();
    http.csrf().disable();
    http.exceptionHandling().accessDeniedPage("/403");
    
    http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/login")
	and().antMatchers("/profile").antMatchers("/addNewEmployee")
	.hasAnyRole("ADMIN").anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
	.and().logout().permitAll();
  }
}*/