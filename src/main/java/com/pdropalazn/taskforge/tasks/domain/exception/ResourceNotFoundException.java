package com.pdropalazn.taskforge.tasks.domain.exception;

public class ResourceNotFoundException  extends RuntimeException{

    public ResourceNotFoundException (String message) {
        super (message);
    }

}
