package com.pdropalazn.taskforge.tasks.domain.model.vo;

public record TaskTitle(String title) {

    public TaskTitle {
        if (title == null){
            throw new IllegalArgumentException("Name can't be empty or null");
        }
        title = title.trim();
        if (title.isEmpty()) throw new IllegalArgumentException("Name can't be empty or null");
        if (title.length() < 3) throw new IllegalArgumentException("Name length must be at least 3 characters");
        if (title.length() > 200) throw new IllegalArgumentException("Name length cannot be longer than 200 characters");
    }

}

//idea refactorizacion: puedo hacer variables min_length y max_length para no hardcodear, si el dia de mañana quiero cambiar ese maximo solo cambio el valor de la variable