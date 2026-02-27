package com.pdropalazn.taskforge.identity.domain.model.vo;

import java.util.UUID;

public record UserId(UUID value) {

    public UserId{
        if (value == null) {
            throw new IllegalArgumentException("UserId value cannot be null");
        }
    }
}
