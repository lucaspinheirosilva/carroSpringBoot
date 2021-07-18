package com.example.carros.security;

import com.example.carros.security.jwt.JwtAuthenticationFilter;
import com.example.carros.security.jwt.JwtAuthorizationFilter;
import com.example.carros.security.jwt.handler.AccessDeniedHandler;
import com.example.carros.security.jwt.handler.UnauthorizedHandler;
import io.swagger.models.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private  UserDatailServiceImpl userDatailService;
    @Autowired
    private UnauthorizedHandler unauthorizedHandler;
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        AuthenticationManager authManager = authenticationManager();

        http
                .authorizeRequests()
                .antMatchers(String.valueOf(HttpMethod.GET), "/api/v1/login").permitAll()
                .antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**")
                .permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .addFilter(new JwtAuthenticationFilter(authManager))
                .addFilter(new JwtAuthorizationFilter(authManager, userDetailsService()))
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
         BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

         auth.userDetailsService(userDatailService).passwordEncoder(encoder);

    }
}
