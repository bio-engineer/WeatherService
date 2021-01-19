package ru.bioengineer.weatherservice.domain.exception;

public class TooManyCitiesFoundException extends IllegalArgumentException {

    private static final int CODE = 900;
    private static final String MESSAGE = "Need more information. Please send needed location";

    @Override
    public String getMessage() {
        return MESSAGE;
    }

    public int getCode() {
        return CODE;
    }
}
