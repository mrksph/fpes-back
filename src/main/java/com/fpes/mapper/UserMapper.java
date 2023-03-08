package com.fpes.mapper;

import com.fpes.dto.user.UserRes;
import com.fpes.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends BaseMapper<User, UserRes> {
    public UserMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public UserRes map(User in) {
        return null;
    }
}
