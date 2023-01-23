package com.worksafe.backend.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppVersionDto {

    private String currentVersion;
    private boolean forceUpdate;

}
