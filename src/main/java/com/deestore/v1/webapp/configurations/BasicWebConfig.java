package com.deestore.v1.webapp.configurations;

import com.deestore.v1.webapp.services.UserDetailsServiceImpl;
import com.deestore.v1.webapp.services.UserService;
import com.deestore.v1.webapp.services.security.SpringSecUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Configuration
public class BasicWebConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SpringSecUserDetailsServiceImpl userService;
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Bean
    protected BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager customAuth() throws Exception{
        return authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("h2-console/**").disable()
                .headers().frameOptions().disable().and().
                authorizeRequests().antMatchers("/admin/**", "/product/**").authenticated().and().
                authorizeRequests().antMatchers("/user/new", "/user/list").authenticated().and().
                authorizeRequests().antMatchers("/index/**").permitAll().and().
                authorizeRequests().antMatchers("/product/new").authenticated().and().
                authorizeRequests().antMatchers("/webjars/**").permitAll().and().
                authorizeRequests().antMatchers("/static/css").permitAll().and().
                authorizeRequests().antMatchers("/static/images").permitAll().and().
                authorizeRequests().antMatchers("/static/js").permitAll().and().
                authorizeRequests().antMatchers("/static/admin/**").permitAll().and().
                authorizeRequests().antMatchers("/h2-console/**").permitAll().and().
                authorizeRequests().antMatchers("/**").permitAll().and()
                .formLogin()
                .loginPage("/login")
                .successHandler(authenticationSuccessHandler).permitAll();
//                .and().httpBasic();
    }

    @Configuration
    public class CustomAuthenticationHandler implements AuthenticationSuccessHandler{

        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

            if(roles.contains("ADMIN")){
                response.sendRedirect("/admin");
            }
            else {
                response.sendRedirect("/index");
            }
        }
    }
}
