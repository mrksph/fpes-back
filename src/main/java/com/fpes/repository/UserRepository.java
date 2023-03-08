package com.fpes.repository;

import com.fpes.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserById(Long id);

    Optional<User> findUserByUsername(String s);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
