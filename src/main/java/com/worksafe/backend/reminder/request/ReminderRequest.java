package com.worksafe.backend.reminder.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReminderRequest {

    @NotBlank
    private String title;
    @NotBlank
    private String description;

    @NotNull
    private Long reminderInterval;

}
