package com.fpes.mapper;

import com.fpes.dto.CenterCommentRes;
import com.fpes.model.CenterComment;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CenterCommentMapper extends BaseMapper<CenterComment, CenterCommentRes> {

    public CenterCommentMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public CenterCommentRes map(CenterComment in) {
        CenterCommentRes out = new CenterCommentRes();
        modelMapper.map(in, out);
        return out;
    }
}
