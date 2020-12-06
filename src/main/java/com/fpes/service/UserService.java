package com.fpes.service;

import com.fpes.dto.user.CreateUserReq;
import com.fpes.dto.user.LoginUserReq;
import com.fpes.model.UserEntity;
import com.fpes.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserEntity registerUser(CreateUserReq req) {
        UserEntity user = new UserEntity();
        modelMapper.map(req, user);
        if (repository.existsByEmail(user.getEmail())) {
            throw new ResponseStatusException(400, "Email already exists.", null);
        }
        if (repository.existsByUsername(user.getUsername())) {
            throw new ResponseStatusException(400, "Username already exists.", null);
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public UserEntity loginUser(LoginUserReq req) {
        return null;
    }

    public UserEntity getUserById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(404, "User not found.", null));
    }
}
