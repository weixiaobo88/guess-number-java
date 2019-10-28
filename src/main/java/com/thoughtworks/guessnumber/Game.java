package com.thoughtworks.guessnumber;

import com.thoughtworks.guessnumber.exception.AnswerFormatIncorrectException;
import com.thoughtworks.guessnumber.exception.OutOfGuessCountException;
import com.thoughtworks.guessnumber.generator.AnswerGenerator;
import com.thoughtworks.guessnumber.model.GuessResult;

import java.util.ArrayList;
import java.util.List;

import static com.thoughtworks.guessnumber.GameStatus.*;

public class Game {

    private static final int MAX_TIMES = 6;
    private final Answer actualAnswer;
    private final List<GuessResult> guessResults;
    private final String CORRECT_RESULT_STANDAR = "4A0B";

    public Game(AnswerGenerator answerGenerator) throws AnswerFormatIncorrectException {
        this.actualAnswer = answerGenerator.generate();
        this.guessResults = new ArrayList();
    }

    public GuessResult guess(Answer inputAnswer) throws OutOfGuessCountException {
        if (!checkContinue()) {
            throw new OutOfGuessCountException("Guess count cant over 6!");
        }
        final String result = actualAnswer.check(inputAnswer).getValue();
        GuessResult guessResult = new GuessResult(result, inputAnswer);
        guessResults.add(guessResult);
        return guessResult;
    }

    public List<GuessResult> guessHistory() {
        return guessResults;
    }

    public boolean checkContinue() {
        return this.checkStatus().equals(CONTINUE);
    }

    public String checkStatus() {
        String status;
        boolean isCorrect = checkCorrectGuessResult();
        if(!isCorrect && guessResults.size() >= MAX_TIMES){
            status = FAIL;
        }else if(isCorrect){
            status= SUCCESS;
        }else{
            status = CONTINUE;
        }
        return status;
    }

    private boolean checkCorrectGuessResult() {
        return guessResults.stream().anyMatch(result -> result.getResult().contentEquals(CORRECT_RESULT_STANDAR));
    }
}
