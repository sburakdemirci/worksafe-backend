package com.worksafe.backend.core.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.worksafe.backend.core.security.service.CustomUserDetailsService;
import com.worksafe.backend.core.security.filter.AuthEntryPointJwt;
import com.worksafe.backend.core.security.filter.TokenAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfiguration {

    private final TokenAuthenticationFilter tokenAuthenticationFilter;
    private final AppSecurityResourcesProperties appSecurityResourcesProperties;
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
                        .requestMatchers(appSecurityResourcesProperties.getUnauthorizedPatterns()
                                .toArray(String[]::new)
                        )
                        .permitAll()


                        .anyRequest().authenticated()

                );
        return http.build();

    }


}
