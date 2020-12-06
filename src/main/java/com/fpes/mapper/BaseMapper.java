package com.fpes.mapper;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
public abstract class BaseMapper<I, O> {
    protected ModelMapper modelMapper;

    public abstract O map(I in);
}
