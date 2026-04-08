package com.lawrence.ordermanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filerChain(HttpSecurity http){
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(request -> request.requestMatchers("/user/register").permitAll());
        http.authorizeHttpRequests(request -> request.requestMatchers("/user/login").permitAll());
        http.authorizeHttpRequests(request -> request.anyRequest().authenticated()) ;
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder(12) ;
    }


}
