package com.worksafe.backend.service;

import org.springframework.stereotype.Service;

import com.worksafe.backend.persistence.repository.SystemReminderRepository;
import com.worksafe.backend.persistence.repository.SystemReminderSubscriptionRepository;
import com.worksafe.backend.persistence.repository.UserReminderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReminderService {

    private final UserReminderRepository userReminderRepository;
    private final SystemReminderRepository systemReminderRepository;
    private final SystemReminderSubscriptionRepository systemReminderSubscriptionRepository;


}
