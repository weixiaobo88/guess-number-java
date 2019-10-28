package com.thoughtworks.guessnumber.generator;

import com.thoughtworks.guessnumber.Answer;
import com.thoughtworks.guessnumber.exception.AnswerFormatIncorrectException;

public class AnswerGenerator {
    private final RandomIntGenerator randomIntGenerator;

    public AnswerGenerator(RandomIntGenerator randomIntGenerator) {
        this.randomIntGenerator = randomIntGenerator;
    }

    public Answer generate() throws AnswerFormatIncorrectException {
        String RandomNumStr = this.randomIntGenerator.generateNums(9, 4);
        Answer answer = Answer.createAnswer(RandomNumStr);
        answer.validate();
        return answer;
    }
}
