package ru.bioengineer.weatherservice.domain.entity.exception;

import ru.bioengineer.weatherservice.domain.exception.TooManyCitiesFoundException;

public class TooManyCitiesFound {

    private final int code;
    private final String message;

    public TooManyCitiesFound(TooManyCitiesFoundException e) {
        this.code = e.getCode();
        this.message = e.getMessage();
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
