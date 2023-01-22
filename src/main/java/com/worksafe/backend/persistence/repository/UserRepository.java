package com.worksafe.backend.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.worksafe.backend.persistence.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    @Modifying
    @Query("update User u set u.enabled=:enabled where u.id=:id")
    void setUserEnabled(@Param(value = "id") String id, @Param(value = "enabled") boolean enabled);


}
