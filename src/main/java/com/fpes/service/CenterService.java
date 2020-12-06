package com.fpes.service;

import com.fpes.dto.CreateCommentReq;
import com.fpes.repository.UserRepository;
import com.fpes.model.Center;
import com.fpes.model.CenterComment;
import com.fpes.model.User;
import com.fpes.repository.CenterCommentRepository;
import com.fpes.repository.CenterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CenterService {

    private final CenterRepository repository;
    private final UserRepository userRepository;
    private final CenterCommentRepository centerCommentRepository;

    public Optional<List<Center>> getAllCenters() {
        Page<Center> all = repository.findAll(PageRequest.of(1, 10));
        List<Center> content = all.getContent();
        return Optional.of(content);
    }

    public Optional<Center> findSingleCenter(Long id) {
        return repository.findCenterById(id);
    }

    public Optional<List<Center>> findCenterByNameContaining(String name) {
        return repository.findCentersByNameContaining(name);
    }

    public Optional<CenterComment> createCenterComment(String id, CreateCommentReq req) {
        CenterComment comment = new CenterComment();
        Center center = repository.findCenterById(Long.valueOf(id)).orElse(null);
        comment.setCenter(center);
        User user = userRepository.findUserById(Long.valueOf("1")).orElse(null);
        comment.setUser(user);
        comment.setComment(req.getComment());
        CenterComment save = centerCommentRepository.save(comment);
        return Optional.of(save);
    }

    public List<CenterComment> getCenterComments(String id) {
        Optional<Center> centerById = repository.findCenterById(Long.valueOf(id));
        if (centerById.isEmpty()) {
            return null;
        }
        Center center = centerById.get();
        Optional<List<CenterComment>> centerCommentsByCenter = centerCommentRepository.findCenterCommentsByCenter(center);
        if (centerCommentsByCenter.isEmpty()) {
            return null;
        }
        return centerCommentsByCenter.get();
    }
}
