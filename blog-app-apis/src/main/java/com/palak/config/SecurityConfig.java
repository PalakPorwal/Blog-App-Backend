package com.palak.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.palak.security.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    // @Bean
    // public FilterRegistrationBean corsFilter() {

    // UrlBasedCorsConfigurationSource source = new
    // UrlBasedCorsConfigurationSource();

    // CorsConfiguration corsConfig = new CorsConfiguration();
    // corsConfig.setAllowCredentials(true);
    // corsConfig.addAllowedOriginPattern("http://localhost:3000");

    // corsConfig.addAllowedHeader("Authorization");
    // corsConfig.addAllowedHeader("Content-Type");
    // corsConfig.addAllowedHeader("Accept");

    // corsConfig.addAllowedMethod("POST");
    // corsConfig.addAllowedMethod("GET");
    // corsConfig.addAllowedMethod("PUT");
    // corsConfig.addAllowedMethod("DELETE");
    // corsConfig.addAllowedMethod("OPTIONS");

    // corsConfig.setMaxAge(3600L);

    // source.registerCorsConfiguration("/**", corsConfig);

    // FilterRegistrationBean bean = new FilterRegistrationBean<>(new
    // CorsFilter(source));
    // return bean;
    // }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // Configure AuthenticationManagerBuilder
        AuthenticationManagerBuilder auth = http
                .getSharedObject(AuthenticationManagerBuilder.class);

        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());

        // Get AuthenticationManager
        AuthenticationManager authManager = auth.build();

        // Http Security object
        http
                .csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((authz) -> authz
                        .anyRequest()
                        .authenticated())
                .authenticationManager(authManager)
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
