package com.pdropalazn.taskforge.tasks.domain.model.vo;

public record TaskTitle(String name) {

    public TaskTitle {
        if (name == null){
            throw new IllegalArgumentException("Name can't be empty or null");
        }
        name = name.trim();
        if (name.isEmpty()) throw new IllegalArgumentException("Name can't be empty or null");
        if (name.length() < 3) throw new IllegalArgumentException("Name length must be at least 3 characters");
        if (name.length() > 200) throw new IllegalArgumentException("Name length cannot be longer than 200 characters");
    }

}
