package com.fpes.controller;

import com.fpes.dto.center.*;
import com.fpes.mapper.CenterCommentMapper;
import com.fpes.mapper.CenterMapper;
import com.fpes.mapper.CenterRatingMapper;
import com.fpes.model.Center;
import com.fpes.model.CenterComment;
import com.fpes.model.CenterRating;
import com.fpes.service.CenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/centers")
public class CenterController {

    private final CenterService service;
    private final CenterMapper centerMapper;
    private final CenterCommentMapper centerCommentMapper;
    private final CenterRatingMapper centerRatingMapper;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/test2")
    @PreAuthorize("hasRole('US')")
    public String test2() {
        return "test2";
    }

    @GetMapping(path = "", produces = "application/json")
    public ResponseEntity<List<CenterRes>> getAllCenters() {
        List<CenterRes> response = service.getAllCenters()
                .stream()
                .map(centerMapper::map)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<CenterRes> getSingleCenter(@PathVariable Long id) {
        Center center = service.findSingleCenter(id);
        CenterRes response = centerMapper.map(center);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/search", params = {"name"}, produces = "application/json")
    public ResponseEntity<List<CenterRes>> findCenterByNameContaining(@RequestParam(name = "name") String name) {
        List<CenterRes> response = service
                .findCenterByNameContaining(name)
                .stream()
                .map(centerMapper::map)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}/comments", produces = "application/json")
    public ResponseEntity<List<CenterCommentRes>> getCenterComments(@PathVariable Long id) {
        List<CenterCommentRes> response = service
                .getCenterComments(id)
                .stream()
                .map(centerCommentMapper::map)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/{id}/comments", produces = "application/json")
    public ResponseEntity<CenterCommentRes> postCommentToCenter(@PathVariable Long id,
                                                                @RequestBody CreateCommentReq req) {
        CenterComment centerComment = service.createCenterComment(id, req);
        CenterCommentRes response = centerCommentMapper.map(centerComment);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/{id}/ratings", produces = "application/json")
    public ResponseEntity<CenterRatingRes> postRatingToCenter(@PathVariable Long id,
                                                              @RequestBody CreateRatingReq req) {
        CenterRating rating = service.createCenterRating(id, req);
        CenterRatingRes response = centerRatingMapper.map(rating);
        return ResponseEntity.ok(response);
    }
}
