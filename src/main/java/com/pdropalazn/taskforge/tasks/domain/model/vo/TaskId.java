package com.pdropalazn.taskforge.tasks.domain.model.vo;

import java.util.UUID;

public record TaskId(UUID value) {

    public TaskId {
        if (value == null) {
            throw new IllegalArgumentException("TaskId cannot be null");
        }
    }

    public static TaskId generate() {
        return new TaskId(UUID.randomUUID());
    }

    public static TaskId from(String raw) {
        return new TaskId(UUID.fromString(raw));
    }

    public String asString() {
        return value.toString();
    }
}