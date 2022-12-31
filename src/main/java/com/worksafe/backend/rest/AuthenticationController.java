package com.worksafe.backend.rest;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.worksafe.backend.dto.request.LoginDto;
import com.worksafe.backend.dto.request.SignupDto;
import com.worksafe.backend.enumarator.AuthProvider;
import com.worksafe.backend.helper.EmailTemplateHelper;
import com.worksafe.backend.helper.UrlConstructHelper;
import com.worksafe.backend.persistence.entity.PasswordResetToken;
import com.worksafe.backend.persistence.entity.User;
import com.worksafe.backend.persistence.entity.VerificationToken;
import com.worksafe.backend.security.TokenProvider;
import com.worksafe.backend.service.EmailSender;
import com.worksafe.backend.service.UserService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final UserService userService;
    private final EmailSender emailSender;
    private final UrlConstructHelper urlConstructHelper;


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

        User user = userService.save(User.builder()
                .firstName(signupDto.getFirstName())
                .lastName(signupDto.getLastName())
                .authProvider(AuthProvider.app)
                .email(signupDto.getEmail())
                .password(passwordEncoder.encode(signupDto.getPassword()))
                .build());

        VerificationToken verificationToken = userService.createVerificationTokenForUser(
                user);
        String verificationTokenUrl = userService.
                constructVerificationUrl(verificationToken.getToken());

        emailSender.sendSingleTextEmail("Desk Mate User Verification",
                EmailTemplateHelper.constructUserVerificationEmail(user, verificationTokenUrl),
                user.getEmail());

    }

    @GetMapping("verify-user")
    public void verifyUser(@RequestParam String token) {
        User user = userService.verifyUser(token);
        //todo send welcome email after verification.

    }

    @PostMapping("forgot-password/{email}")
    public void forgotPassword(@PathVariable String email) {

        User user = userService.findUserByEmail(email)
                .orElseThrow(() -> new RuntimeException());

        PasswordResetToken passwordResetTokenForUser = userService.createPasswordResetTokenForUser(
                user);
    }


    @PostMapping("reset-password-token/{email}")
    public void resetPassword(@PathVariable String email, @RequestParam String token) {

    }


}
