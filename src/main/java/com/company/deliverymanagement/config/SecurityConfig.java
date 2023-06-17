package com.company.deliverymanagement.config;

import com.company.deliverymanagement.service.impl.AppUserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AppUserDetailServiceImpl appUserDetailService;

    private final PasswordEncoder passwordEncoder;

    @Bean
    @Order
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/").permitAll()
                .antMatchers("/admin/dashboard").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/categories/registration").hasRole("ADMIN")
                .antMatchers("/categories/findAll").permitAll()
                .antMatchers("/categories/{id}").permitAll()
                .antMatchers(HttpMethod.PUT,"/categories/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/categories/{id}").hasRole("ADMIN")
                .antMatchers("/categories/{id}/foods").permitAll()
                .antMatchers(HttpMethod.POST,"/customer/registration").hasRole("CUSTOMER")
                .antMatchers("/customer/confirm-account/**").hasRole("CUSTOMER")
                .antMatchers(HttpMethod.POST,"/customer/confirm-account/**").hasRole("CUSTOMER")
                .antMatchers(HttpMethod.GET,"/customer/confirm-account/**").hasRole("CUSTOMER")
                .antMatchers(HttpMethod.POST,"/customer/forget-password").hasRole("CUSTOMER")
                .antMatchers("/customer/reset-password").hasRole("CUSTOMER")
                .antMatchers(HttpMethod.POST,"/customer/reset-password").hasRole("CUSTOMER")
                .antMatchers(HttpMethod.POST,"/driver/registration").hasRole("ADMIN")
                .antMatchers("/driver/findAll").hasRole("ADMIN")
                .antMatchers("/driver/{id}").hasAnyRole("DRIVER","ADMIN")
                .antMatchers(HttpMethod.PUT,"/driver/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/driver/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/food/registration").hasRole("ADMIN")
                .antMatchers("/food/findAll").permitAll()
                .antMatchers("/food//{id}").permitAll()
                .antMatchers(HttpMethod.PUT,"/food//{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/food//{id}").hasRole("ADMIN")
                .antMatchers("/orders/findAll").hasRole("ADMIN")
                .antMatchers("/orders/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/orders/{id}").hasRole("DRIVER")
//                .antMatchers("/customer/confirm-account").hasRole("CUSTOMER")
                //.antMatchers("/customer{customerId}").access("@appUserDetailServiceImpl.hasCustomerId(authentication,#customer_id)")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(appUserDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);

        return daoAuthenticationProvider;
    }
}