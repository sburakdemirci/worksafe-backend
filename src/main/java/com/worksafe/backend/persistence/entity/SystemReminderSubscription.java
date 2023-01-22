package com.worksafe.backend.persistence.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@IdClass(SystemReminderAndUserPK.class)
public class SystemReminderSubscription extends EntityAuditBase {


    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;


    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    private SystemReminder reminder;

    public SystemReminderSubscription(User user, SystemReminder reminder) {
        this.user = user;
        this.reminder = reminder;
    }
}

class SystemReminderAndUserPK implements Serializable {

    private final String user;
    private final Long reminder;


    public SystemReminderAndUserPK(String  user, Long reminder) {
        this.user = user;
        this.reminder = reminder;
    }
}
