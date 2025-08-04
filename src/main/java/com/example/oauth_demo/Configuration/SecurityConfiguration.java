package com.example.oauth_demo.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "./index.html", "/styles.css", "/script.js").permitAll()
                        .requestMatchers("/users").authenticated()
                        .anyRequest().permitAll()
                )
                .oauth2Login(oauth2->oauth2
                        .defaultSuccessUrl("/index.html", true)
                )
                .logout(logout->logout
                        .logoutSuccessUrl("/index.html").permitAll()
                );
        return http.build();
    }
}