package com.fpes.controller;

import com.fpes.dto.CenterCommentRes;
import com.fpes.dto.CenterRes;
import com.fpes.dto.CreateCommentReq;
import com.fpes.mapper.CenterCommentMapper;
import com.fpes.mapper.CenterMapper;
import com.fpes.model.Center;
import com.fpes.model.CenterComment;
import com.fpes.service.CenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RefreshScope
@RequiredArgsConstructor
public class CenterController {

    private final CenterService service;
    private final CenterMapper centerMapper;
    private final CenterCommentMapper centerCommentMapper;

    @GetMapping(path = "/centers", produces = "application/json")
    public ResponseEntity<List<CenterRes>> getAllCenters() {
        Optional<List<Center>> allCenters = service.getAllCenters();
        if (allCenters.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<CenterRes> response = allCenters.get()
                .stream()
                .map(centerMapper::map)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/centers/{id}", produces = "application/json")
    public ResponseEntity<CenterRes> getSingleCenter(@PathVariable String id) {
        Optional<Center> possibleCenter = service.findSingleCenter(Long.valueOf(id));
        if (possibleCenter.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Center center = possibleCenter.get();
        CenterRes response = centerMapper.map(center);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/centers", params = {"name"}, produces = "application/json")
    public ResponseEntity<List<CenterRes>> findCenterByNameContaining(@RequestParam(name = "name") String name) {
        Optional<List<Center>> centerByNameContaining = service.findCenterByNameContaining(name);
        if (centerByNameContaining.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<CenterRes> response = centerByNameContaining.get()
                .stream()
                .map(centerMapper::map)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/centers/{id}/comments", produces = "application/json")
    public ResponseEntity<List<CenterCommentRes>> getCenterComments(@PathVariable String id) {
        List<CenterComment> centerComments = service.getCenterComments(id);
        List<CenterCommentRes> response = centerComments.stream()
                .map(centerCommentMapper::map)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/centers/{id}/comments", produces = "application/json")
    public ResponseEntity postCommentToCenter(@PathVariable String id,
                                              @RequestBody CreateCommentReq req) {
        Optional<CenterComment> centerComment = service.createCenterComment(id, req);
        if (centerComment.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        CenterComment comment = centerComment.get();
        CenterCommentRes response = centerCommentMapper.map(comment);
        return ResponseEntity.ok(response);
    }
}
