package com.palak.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.palak.security.JwtAuthenticationEntryPoint;
import com.palak.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Http Security object
        http
                .csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(HttpMethod.POST, "/auth/login")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .exceptionHandling((e) -> e.authenticationEntryPoint(this.jwtAuthenticationEntryPoint))
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

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
}
