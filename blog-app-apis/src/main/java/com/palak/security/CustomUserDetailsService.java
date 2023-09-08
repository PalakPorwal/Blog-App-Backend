package com.palak.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.palak.entities.User;
import com.palak.exceptions.ResourceNotFoundException;
import com.palak.repositories.UserRepo;

@Configuration
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepo.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", -1));

        return user;
    }

    // @Bean
    // CustomUserDetailsService customUserDetailsService() {
    //     return new CustomUserDetailsService();
    // }

}
