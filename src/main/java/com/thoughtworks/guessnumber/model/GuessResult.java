package com.thoughtworks.guessnumber.model;

import com.thoughtworks.guessnumber.Answer;

public class GuessResult {
    private final Answer inputAnswer;
    private final String result;

    public GuessResult(String result, Answer inputAnswer) {
        this.result = result;
        this.inputAnswer = inputAnswer;
    }

    public String getResult() {
        return result;
    }

    public Answer getInputAnswer() {
        return inputAnswer;
    }
}
