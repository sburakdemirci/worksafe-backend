package com.worksafe.backend.core.security.rest;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.worksafe.backend.core.security.exception.AuthenticationException;
import com.worksafe.backend.core.user.request.ForgotPasswordRequest;
import com.worksafe.backend.core.user.request.LoginRequest;
import com.worksafe.backend.core.user.response.LoginResponse;
import com.worksafe.backend.core.user.entity.PasswordResetToken;
import com.worksafe.backend.core.user.entity.RefreshToken;
import com.worksafe.backend.core.user.request.RefreshTokenRequest;
import com.worksafe.backend.core.user.service.RefreshTokenService;
import com.worksafe.backend.core.user.request.SignupRequest;
import com.worksafe.backend.core.security.service.JwtTokenService;
import com.worksafe.backend.core.user.entity.User;
import com.worksafe.backend.core.security.configuration.UserPrincipal;
import com.worksafe.backend.core.user.service.UserService;
import com.worksafe.backend.core.user.enumarator.UserAuthProvider;
import com.worksafe.backend.email.helper.UrlConstructHelper;
import com.worksafe.backend.email.service.EmailSender;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService jwtTokenService;
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
        String accessToken = jwtTokenService.createTokenFromAuthentication(
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
                .name(signupRequest.getName())
                .userAuthProvider(UserAuthProvider.LOCAL)
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

    @PostMapping("forgot-password")
    public void forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) {

        User user = userService.findUserByEmail(forgotPasswordRequest.getEmail())
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

        String accessToken = jwtTokenService.createTokenFromUserId(refreshToken.getUser().getId());

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
