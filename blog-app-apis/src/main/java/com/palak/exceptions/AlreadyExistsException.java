package com.palak.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlreadyExistsException extends RuntimeException {
    String message;

    public AlreadyExistsException(String message) {
        super(String.format("%s", message));
        this.message = message;
    }
}
