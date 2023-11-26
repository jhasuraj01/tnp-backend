package com.anorcle.tnp.backend.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.anorcle.tnp.backend.adapters.PasswordAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/api/auth/**").permitAll()
            .anyRequest().authenticated())
        .formLogin(Customizer.withDefaults())
        .httpBasic(Customizer.withDefaults());

    return http.build();
  }

  @Bean
  PasswordEncoder getPasswordEncoder(PasswordAdapter passwordAdapter) {
    return passwordAdapter.getEncoder();
  }

}
