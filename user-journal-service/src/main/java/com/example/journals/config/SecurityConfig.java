package com.example.journals.config;

import com.example.common.entities.User;
import com.example.journals.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Optional;
import java.util.stream.Collectors;

@EnableMethodSecurity
@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(custom -> custom.disable()).authorizeHttpRequests((auth) -> {
            auth.anyRequest().authenticated();
        }).httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Optional<User> user = userRepository.findByUsername(username);
            if(user.isEmpty()) {
                throw new UsernameNotFoundException("User name " + username +" is not present");
            }
            return new org.springframework.security.core.userdetails.User(
                    user.get().getUsername(),
                    passwordEncoder().encode(user.get().getPassword()),
                    user.get().getRoles().stream()
                            .map(role -> new SimpleGrantedAuthority("ROLE_"+role.getRoleName()))
                            .collect(Collectors.toList())
            );
        };
    }
}
