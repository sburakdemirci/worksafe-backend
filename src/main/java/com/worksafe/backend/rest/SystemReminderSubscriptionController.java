package com.worksafe.backend.rest;


import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.worksafe.backend.persistence.entity.SystemReminder;
import com.worksafe.backend.persistence.entity.SystemReminderSubscription;
import com.worksafe.backend.security.CurrentUser;
import com.worksafe.backend.security.UserPrincipal;
import com.worksafe.backend.service.SystemReminderService;
import com.worksafe.backend.service.SystemReminderSubscriptionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/reminder/system/subscription")
@RequiredArgsConstructor
@Slf4j
public class SystemReminderSubscriptionController {

    private final SystemReminderSubscriptionService systemReminderSubscriptionService;
    private final SystemReminderService systemReminderService;

    @GetMapping
    public List<SystemReminderSubscription> getAllByUser(@CurrentUser UserPrincipal userPrincipal) {
        return systemReminderSubscriptionService.findAllByUserId(userPrincipal.getUser().getId());
    }

    @PostMapping("{id}")
    public SystemReminderSubscription subscribe(@PathVariable Long id,
            @CurrentUser UserPrincipal userPrincipal) {

        systemReminderSubscriptionService.findByReminderIdAndUserId(id, userPrincipal.getUser()
                .getId()).ifPresent(systemReminderSubscription -> {
                    throw new RuntimeException();
                    //user already subscribed
                }
        );

        SystemReminder systemReminder = systemReminderService.getById(id)
                .orElseThrow(RuntimeException::new);
        //reminder did not found

        return systemReminderSubscriptionService.create(
                new SystemReminderSubscription(userPrincipal.getUser(), systemReminder));
    }


    @DeleteMapping("{id}")
    public void unsubscribe(@PathVariable Long id,
            @CurrentUser UserPrincipal userPrincipal) {

        systemReminderSubscriptionService.deleteByReminderIdAndUserId(id, userPrincipal.getUser()
                .getId());
    }

    @GetMapping("/admin")
    public List<SystemReminderSubscription> getAllByAdmin() {
        return systemReminderSubscriptionService.getAll();
    }


}
