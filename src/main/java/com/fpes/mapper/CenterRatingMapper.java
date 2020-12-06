package com.fpes.mapper;

import com.fpes.dto.center.CenterRatingRes;
import com.fpes.model.CenterRating;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CenterRatingMapper extends BaseMapper<CenterRating, CenterRatingRes>{
    public CenterRatingMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public CenterRatingRes map(CenterRating in) {
        CenterRatingRes out = new CenterRatingRes();
        modelMapper.map(in, out);
        return out;
    }
}
