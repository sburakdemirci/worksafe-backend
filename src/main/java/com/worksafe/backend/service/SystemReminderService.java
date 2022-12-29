package com.worksafe.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.worksafe.backend.persistence.entity.SystemReminder;
import com.worksafe.backend.persistence.repository.SystemReminderRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class SystemReminderService {

    private final SystemReminderRepository systemReminderRepository;


    @Transactional
    public SystemReminder save(SystemReminder systemReminder) {
        return systemReminderRepository.save(systemReminder);
    }

    @Transactional
    public void deleteById(Long id) {
        systemReminderRepository.deleteById(id);
    }

    public List<SystemReminder> getAll() {
        return systemReminderRepository.findAll();
    }

    public Optional<SystemReminder> getById(Long id) {
        return systemReminderRepository.findById(id);
    }

}
