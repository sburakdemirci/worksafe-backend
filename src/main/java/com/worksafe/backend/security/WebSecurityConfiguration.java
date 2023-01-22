package com.worksafe.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.worksafe.backend.dto.configuration.AppSecurityResourcesConfiguration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfiguration {

    private final CustomUserDetailsService customUserDetailsService;
    private final TokenAuthenticationFilter tokenAuthenticationFilter;
    private final AppSecurityResourcesConfiguration appSecurityResourcesConfiguration;
    private final AuthEntryPointJwt authEntryPointJwt;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf()
                .disable()
                .cors()
                .and()
                .httpBasic()
                .disable()
                .formLogin()
                .disable()
                .logout()
                .disable()
                .exceptionHandling().authenticationEntryPoint(authEntryPointJwt).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers(appSecurityResourcesConfiguration.getUnauthorizedPatterns()
                                .toArray(String[]::new)
                        )
                        .permitAll()


                        .anyRequest().authenticated()

                );
        return http.build();

    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws
            Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(
                AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

}
