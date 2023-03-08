package com.fpes.controller;

import com.fpes.dto.user.CreateUserReq;
import com.fpes.dto.user.LoginUserReq;
import com.fpes.dto.user.UserRes;
import com.fpes.mapper.UserMapper;
import com.fpes.model.User;
import com.fpes.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserMapper mapper;
    private final UserService service;

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<UserRes> getUserById(@PathVariable Long id) {
        return null;
    }


    @PostMapping(path = "/register", produces = "application/json")
    public ResponseEntity<UserRes> registerUser(@RequestBody CreateUserReq req) {
        User user = service.registerUser(req);
        UserRes response = mapper.map(user);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/login", produces = "application/json")
    public ResponseEntity<String> loginUser(@RequestBody LoginUserReq req) {
        String jwtToken = service.loginUser(req);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwtToken);
        return ResponseEntity.ok()
                .headers(headers)
                .body("Login successfully!");
    }
}
