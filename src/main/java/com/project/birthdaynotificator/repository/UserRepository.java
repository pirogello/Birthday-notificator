package com.project.birthdaynotificator.repository;

import com.project.birthdaynotificator.model.Notification;
import com.project.birthdaynotificator.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
}
