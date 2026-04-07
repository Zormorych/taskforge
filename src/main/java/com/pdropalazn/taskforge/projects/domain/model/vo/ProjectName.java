package com.pdropalazn.taskforge.projects.domain.model.vo;

public record ProjectName(String name) {

    public ProjectName {
        if (name == null) {
            throw new IllegalArgumentException("Name can't be empty or null");
        }
        name = name.trim();
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name can't be empty or null");
        }
        if (name.length() < 3) {
            throw new IllegalArgumentException("Name length must be at least 3 caracters");
        }
        if (name.length() > 200) {
            throw new IllegalArgumentException("Name length cannot be longer than 200 caracters");
        }
    }
}
