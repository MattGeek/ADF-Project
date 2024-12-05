package com.sdh4.ai_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET).permitAll() // Allow GET requests for everyone
                        .requestMatchers(HttpMethod.PATCH, String.valueOf(HttpMethod.PUT)).hasAnyRole("USER", "ADMIN") // Allow PATCH/PUT for USER/ADMIN
                        .anyRequest().hasRole("ADMIN") // Allow all other requests for ADMIN
                )
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin) // Allow H2 Console to work
                )
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for development
                .httpBasic(withDefaults()) // Enable basic authentication
                .formLogin(withDefaults()); // Enable form-based login

        return http.build();
    }
}
