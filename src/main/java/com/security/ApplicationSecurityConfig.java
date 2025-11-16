package com.banking.banking_app.security;

///import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.banking.banking_app.Services.RetrieveUserDetailsService;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig {
    
    private final RetrieveUserDetailsService userDetailsService;

    public ApplicationSecurityConfig(RetrieveUserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
    // Use constructor injection for UserDetailsService
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);

    provider.setPasswordEncoder(passwordEncoder());

    return provider;
}

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())  
        .authorizeHttpRequests(authz -> authz
            .requestMatchers("/signup", "/login").permitAll()
            .anyRequest().authenticated()  
        )
        .formLogin(form -> form
            .loginPage("/login")
            .permitAll()
        );
    
    return http.build();
    }

}