package com.fpes.dto.center.filters;

import lombok.Getter;

@Getter
public enum TypeEnum {
    isPublic("Centro público"),
    isPrivate("Centro privado");

    final String type;

    TypeEnum(String s) {
        this.type = s;
    }
}
