package com.worksafe.backend.exception;

public class BurakException  extends RuntimeException {

    public BurakException() {
        super("Could not find employee ");
    }
}

