package com.fpes.controller;

import com.fpes.dto.user.CreateUserReq;
import com.fpes.dto.user.LoginUserReq;
import com.fpes.dto.user.UserRes;
import com.fpes.mapper.UserMapper;
import com.fpes.model.UserEntity;
import com.fpes.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserMapper mapper;
    private final UserService service;

    @GetMapping(path = "/", produces = "application/json")
    public ResponseEntity<List<UserRes>> getAll() {
        return null;
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<UserRes> getUserById(@PathVariable Long id) {
        return null;
    }


    @PostMapping(path = "/sign-up", produces = "application/json")
    public ResponseEntity<UserRes> registerUser(@RequestBody CreateUserReq req) {
        UserEntity user = service.registerUser(req);
        UserRes response = mapper.map(user);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/login", produces = "application/json")
    public ResponseEntity<UserRes> loginUser(@RequestBody LoginUserReq req) {
        return null;
    }
}
