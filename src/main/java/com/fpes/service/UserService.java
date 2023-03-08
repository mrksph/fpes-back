package com.fpes.service;

import com.auth0.jwt.JWT;
import com.fpes.dto.user.CreateUserReq;
import com.fpes.dto.user.LoginUserReq;
import com.fpes.exception.ValidationException;
import com.fpes.model.ERole;
import com.fpes.model.Role;
import com.fpes.model.User;
import com.fpes.repository.RoleRepository;
import com.fpes.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.fpes.utils.Constants.EXPIRATION_TIME;
import static com.fpes.utils.Constants.SECRET;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final RoleRepository roleRepository;

    private final AuthenticationManager authenticationManager;

    public User registerUser(CreateUserReq req) {
        User user = new User();
        modelMapper.map(req, user);
        if (user.getEmail() == null) {
            throw new ValidationException(Collections.singletonList("Email is required"));
        }
        if (repository.existsByEmail(user.getEmail())) {
            throw new ValidationException("Email already exists.");
        }
        if (repository.existsByUsername(user.getUsername())) {
            throw new ValidationException("Username already exists.");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        HashSet<Role> roles = new HashSet<>();

        Role role = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found"));

        roles.add(role);

        user.setRoles(roles);

        return repository.save(user);
    }

    public String loginUser(LoginUserReq req) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Optional<User> optUser = repository.findUserByUsername(req.getUsername());
        if (optUser.isEmpty()) {
            throw new ResponseStatusException(404, "User not found.", null);
        }
        if (!bCryptPasswordEncoder.matches(req.getPassword(), optUser.get().getPassword())) {
            throw new ResponseStatusException(401, "Invalid credentials.", null);
        }

        return JWT.create()
                .withSubject(req.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
    }
}
