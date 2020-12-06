package com.fpes.mapper;

import com.fpes.dto.CenterRes;
import com.fpes.model.Center;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CenterMapper extends BaseMapper<Center, CenterRes> {

    public CenterMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public CenterRes map(Center in) {
        CenterRes out = new CenterRes();
        modelMapper.map(in, out);
        return out;
    }
}
