package com.worksafe.backend.mobile.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppVersionResponse {

    private String version;
    private boolean forceUpdate;

}
