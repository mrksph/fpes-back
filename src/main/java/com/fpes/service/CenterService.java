package com.fpes.service;

import com.fpes.dto.center.CreateCommentReq;
import com.fpes.dto.center.CreateRatingReq;
import com.fpes.dto.center.SearchCenterReq;
import com.fpes.exception.EntityNotActiveException;
import com.fpes.exception.EntityNotFoundException;
import com.fpes.model.Center;
import com.fpes.model.CenterComment;
import com.fpes.model.CenterRating;
import com.fpes.model.User;
import com.fpes.repository.CenterCommentRepository;
import com.fpes.repository.CenterRatingRepository;
import com.fpes.repository.CenterRepository;
import com.fpes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CenterService {

    private final CenterRepository repository;
    private final UserRepository userRepository;
    private final CenterCommentRepository centerCommentRepository;
    private final CenterRatingRepository centerRatingRepository;

    public List<Center> getAllCenters() {
        return repository.findAll(PageRequest.of(1, 10)).getContent();
    }

    public Center findSingleCenter(Long id) {
        Center center = repository.findCenterById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));

        if (!center.getIsActive()) {
            throw new EntityNotActiveException("");
        }
        return center;
    }

    public List<Center> searchCenterByFilters(SearchCenterReq req, int pageNumber) {
        List<String> centerType = req.getType().stream().map(type -> {
            if (type.equals("isPublic")) {
                return "Centro público";
            } else {
                return "Centro privado";
            }
        }).collect(Collectors.toList());

        List<String> studyFormat = req.getStudyFormat().stream().map(format -> {
            switch (format) {
                case "online":
                    return "En línea";
                case "semi":
                    return "Semipresencial";
                case "dual":
                    return "Dual";
                case "afternoon":
                    return "Vespertino (Presencial)";
                case "night":
                    return "Nocturno (Presencial)";
                case "remote":
                    return "A Distancia";
                case "day":
                    return "Diurno (Presencial)";
                default:
                    return "Todas";
            }
        }).collect(Collectors.toList());

        List<String> studyLevel = req.getStudyLevel().stream().map(level -> {
            switch (level) {
                case "medium":
                    return "%Medio%";
                case "high":
                    return "%Superior%";
                default:
                    return "";
            }
        }).collect(Collectors.toList());

        return repository.searchCenterByFilters(req.getSearchTerm(),
                req.getRegion(),
                req.getProvince(),
                centerType,
                studyFormat,
                studyLevel,
                PageRequest.of(pageNumber, 10)).getContent();

    }

    public CenterComment createCenterComment(Long id, CreateCommentReq req) {
        Center center = repository.findCenterById(id).orElseThrow(() -> new ResponseStatusException(404, "Center not found.", null));
        CenterComment comment = new CenterComment();
        comment.setCenter(center);
        User user = userRepository.findUserById(1L).orElseThrow(() -> new ResponseStatusException(404, "No comments found for this center.", null));
        comment.setUser(user);
        comment.setComment(req.getComment());
        return centerCommentRepository.save(comment);
    }

    public List<CenterComment> getCenterComments(Long id) {
        Center centerById = repository.findCenterById(id).orElseThrow(() -> new ResponseStatusException(404, "Center not found.", null));
        return centerCommentRepository.findCenterCommentsByCenter(centerById).orElseThrow(() -> new ResponseStatusException(404, "No comments found for this center.", null));
    }

    public CenterRating createCenterRating(Long id, CreateRatingReq req) {
        Center center = repository.findCenterById(id).orElseThrow(() -> new ResponseStatusException(404, "Center not found.", null));
        CenterRating rating = new CenterRating();
        rating.setCenter(center);
        User user = userRepository.findUserById(1L).orElseThrow(() -> new ResponseStatusException(404, "No comments found for this center.", null));
        rating.setUser(user);
        return centerRatingRepository.save(rating);
    }
}
