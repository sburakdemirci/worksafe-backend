package com.worksafe.backend.rest;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.worksafe.backend.dto.request.ReminderDto;
import com.worksafe.backend.persistence.entity.SystemReminder;
import com.worksafe.backend.service.SystemReminderService;

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
    public SystemReminder create(@RequestBody ReminderDto reminderDto) {
        return systemReminderService.save(new SystemReminder(reminderDto));
    }

    @PostMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public SystemReminder update(@PathVariable Long id, @RequestBody ReminderDto reminderDto) {
        SystemReminder systemReminder = systemReminderService.getById(id)
                .orElseThrow(RuntimeException::new);
        systemReminder.update(reminderDto);
        return systemReminderService.save(new SystemReminder(reminderDto));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id, @RequestBody ReminderDto reminderDto) {
        systemReminderService.deleteById(id);
    }


}
