package com.thoughtworks.guessnumber.exception;

public class OutOfGuessCountException extends Exception {
    public OutOfGuessCountException(String message) {
        super(message);
    }
}
