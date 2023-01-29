package com.worksafe.backend.reminder.rest;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.worksafe.backend.reminder.entity.SystemReminder;
import com.worksafe.backend.reminder.request.ReminderRequest;
import com.worksafe.backend.reminder.service.SystemReminderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("reminder/system")
@RequiredArgsConstructor
public class SystemReminderController {

    private final SystemReminderService systemReminderService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public List<SystemReminder> getAll() {
        return systemReminderService.getAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public SystemReminder create(@RequestBody ReminderRequest reminderRequest) {
        return systemReminderService.save(new SystemReminder(reminderRequest));
    }

    @PostMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public SystemReminder update(@PathVariable Long id, @RequestBody ReminderRequest reminderRequest) {
        SystemReminder systemReminder = systemReminderService.getById(id)
                .orElseThrow(RuntimeException::new);
        systemReminder.update(reminderRequest);
        return systemReminderService.save(new SystemReminder(reminderRequest));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id, @RequestBody ReminderRequest reminderRequest) {
        systemReminderService.deleteById(id);
    }


}
