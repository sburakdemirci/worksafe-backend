package com.worksafe.backend.rest;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.worksafe.backend.dto.LoginDto;
import com.worksafe.backend.dto.SignupDto;
import com.worksafe.backend.enumarator.AuthProvider;
import com.worksafe.backend.security.TokenProvider;
import com.worksafe.backend.service.UserService;
import com.worksafe.backend.persistence.entity.User;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final UserService userService;


    @PostMapping("login")
    public String login(@RequestBody LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenProvider.createToken(authentication);
    }


    @PostMapping("signup")
    public void signup(@RequestBody SignupDto signupDto) {

        if (userService.checkExistsByEmail(signupDto.getEmail())) {
            throw new RuntimeException("This email address already registered");
        }

        User user = User.builder()
                .firstName(signupDto.getFirstName())
                .lastName(signupDto.getLastName())
                .authProvider(AuthProvider.app)
                .email(signupDto.getEmail())
                .password(passwordEncoder.encode(signupDto.getPassword()))
                .build();

        userService.save(user);
    }

    @PostMapping("forgot-password")
    public void forgotPassword() {

    }


    @PostMapping("reset-password")
    public void resetPassword(@RequestParam String email) {

    }

}
