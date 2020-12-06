package com.fpes.repository;

import com.fpes.model.Center;
import com.fpes.model.CenterComment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CenterCommentRepository extends CrudRepository<CenterComment, Long> {
    Optional<List<CenterComment>> findCenterCommentsByCenter(Center id);
}
