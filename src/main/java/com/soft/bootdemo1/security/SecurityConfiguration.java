package com.soft.bootdemo1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by Dell on 4/26/2020.
 */

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Autowired
    MyUserDetailService myUserDetailService;

    @Autowired
    JwtFilterRequest jwtFilterRequest;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(myUserDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().formLogin().disable().authorizeRequests()
                .antMatchers(HttpMethod.PATCH, "/books*").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/books*").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/books*").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/books*").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/books").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/admin").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/books").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET, "/outsideUsers").hasAnyRole("ADMIN", "USER")
                .antMatchers("/book*").hasAnyRole("ADMIN", "USER")
                .antMatchers("/user").hasAnyRole("ADMIN", "USER")
                .antMatchers("/", "authenticate").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilterRequest, UsernamePasswordAuthenticationFilter.class);

       /* http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.PATCH, "/books*").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/books*").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/books*").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/books*").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/books").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/admin").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/books").hasAnyRole("ADMIN", "USER")
                .antMatchers("/book*").hasAnyRole("ADMIN", "USER")
                .antMatchers("/user").hasAnyRole("ADMIN", "USER")
                .antMatchers("/", "/authenticate").permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .formLogin().disable();*/
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/", "/authenticate");
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
