package com.fpes.repository;

import com.fpes.model.Center;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CenterRepository extends CrudRepository<Center, Long> {
    Page<Center> findAll(Pageable pageable);

    Optional<Center> findCenterById(Long id);

    Optional<List<Center>> findCentersByNameContaining(String name);
}
