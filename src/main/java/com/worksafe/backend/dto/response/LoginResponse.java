package com.worksafe.backend.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

    private String userId;
    private String username;
    private String accessToken;

    private String refreshToken;


}
