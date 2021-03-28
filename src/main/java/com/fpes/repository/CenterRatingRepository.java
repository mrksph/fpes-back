package com.fpes.repository;

import com.fpes.model.Center;
import com.fpes.model.CenterRating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CenterRatingRepository extends CrudRepository<CenterRating, Long> {
    Optional<CenterRating> findCenterRatingByCenter(Center center);
}
