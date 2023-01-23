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

import com.worksafe.backend.dto.request.LoginRequest;
import com.worksafe.backend.dto.request.RefreshTokenRequest;
import com.worksafe.backend.dto.request.SignupRequest;
import com.worksafe.backend.dto.response.LoginResponse;
import com.worksafe.backend.enumarator.AuthProvider;
import com.worksafe.backend.exception.AuthenticationException;
import com.worksafe.backend.helper.UrlConstructHelper;
import com.worksafe.backend.persistence.entity.PasswordResetToken;
import com.worksafe.backend.persistence.entity.RefreshToken;
import com.worksafe.backend.persistence.entity.User;
import com.worksafe.backend.security.TokenProvider;
import com.worksafe.backend.security.UserPrincipal;
import com.worksafe.backend.service.EmailSender;
import com.worksafe.backend.service.RefreshTokenService;
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
    private final RefreshTokenService refreshTokenService;


    @PostMapping("login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        String accessToken = tokenProvider.createTokenFromAuthentication(
                authentication);

        RefreshToken refreshToken = refreshTokenService.create(principal.getUser());

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken())
                .userId(principal.getUser().getId())
                .username(principal.getUsername())
                .build();
    }


    @PostMapping("signup")
    public void signup(@RequestBody SignupRequest signupRequest) {

        if (userService.checkExistsByEmail(signupRequest.getEmail())) {
            throw new RuntimeException("This email address already registered");
        }

        User user = userService.save(User.builder()
                .firstName(signupRequest.getFirstName())
                .lastName(signupRequest.getLastName())
                .authProvider(AuthProvider.LOCAL)
                .email(signupRequest.getEmail())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .build());
/*
        VerificationToken verificationToken = userService.createVerificationTokenForUser(
                user);
        String verificationTokenUrl = userService.
                constructVerificationUrl(verificationToken.getToken());

        emailSender.sendSingleTextEmail("Desk Mate User Verification",
                EmailTemplateHelper.constructUserVerificationEmail(user, verificationTokenUrl),
                user.getEmail());*/

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

    @PostMapping("refresh-token")
    public LoginResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest)
            throws AuthenticationException {
        RefreshToken refreshToken = refreshTokenService.getByToken(
                        refreshTokenRequest.getRefreshToken())
                .orElseThrow(() -> new AuthenticationException("Invalid credentials"));

        String accessToken = tokenProvider.createTokenFromUserId(refreshToken.getUser().getId());

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken())
                .userId(refreshToken.getUser().getId())
                .username(refreshToken.getUser().getEmail())
                .build();

    }


/*
    @PostMapping("reset-password-token/{email}")
    public void resetPassword(@PathVariable String email, @RequestParam String token) {

    }

    @PostMapping("reset-password-token/{email}")
    public void resetPassword(@PathVariable String email, @RequestParam String token) {

    }
*/


}
