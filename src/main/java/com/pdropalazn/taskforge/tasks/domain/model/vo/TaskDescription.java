package com.pdropalazn.taskforge.tasks.domain.model.vo;

public record TaskDescription(String description) {

    public TaskDescription {
        description = description.trim();

        if (description.length() > 1000){
            throw new IllegalArgumentException("Description cannot be longer than 1000 characters");
        }

    }
}
