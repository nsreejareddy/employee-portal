package com.societegenerale.employeeportal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class EmployeeWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

  @Value("${employee.portal.admin}")
  private String admin;

  @Value("${employee.portal.admin.password}")
  private String password;

  @Autowired
  private EmployeeBasicAuthenticationEntryPoint authEntryPoint;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
      .authorizeRequests()
      .antMatchers("/employee/**")
      .authenticated()
      .and()
      .httpBasic()
      .authenticationEntryPoint(authEntryPoint);
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().withUser(admin).password(password).roles("USER");
  }

  @Bean
  public BCryptPasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }
}

