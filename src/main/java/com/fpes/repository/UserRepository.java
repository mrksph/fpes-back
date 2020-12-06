package com.fpes.repository;

import com.fpes.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findUserById(Long id);

    Optional<UserEntity> findUserByUsername(String s);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
