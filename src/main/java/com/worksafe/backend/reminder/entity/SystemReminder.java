package com.worksafe.backend.reminder.entity;

import com.worksafe.backend.core.configuration.EntityAuditBase;
import com.worksafe.backend.reminder.request.ReminderRequest;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class SystemReminder extends EntityAuditBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private Long intervalInSeconds;


    public SystemReminder(ReminderRequest reminderDto) {
        this.title = reminderDto.getTitle();
        this.description = reminderDto.getDescription();
        this.intervalInSeconds = reminderDto.getReminderInterval();
    }

    public void update(ReminderRequest reminderDto) {
        this.title = reminderDto.getTitle();
        this.description = reminderDto.getDescription();
        this.intervalInSeconds = reminderDto.getReminderInterval();
    }
}
