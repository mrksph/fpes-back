package com.fpes.mapper;

import com.fpes.dto.user.UserRes;
import com.fpes.model.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends BaseMapper<UserEntity, UserRes> {
    public UserMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public UserRes map(UserEntity in) {
        return null;
    }
}
