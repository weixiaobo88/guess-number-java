package com.thoughtworks.guessnumber;

import com.thoughtworks.guessnumber.exception.AnswerFormatIncorrectException;
import com.thoughtworks.guessnumber.model.Record;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class Answer {

    private List<String> numList;

    public void setNumList(List<String> numList) {
        this.numList = numList;
    }

    public static Answer createAnswer(String inputStr) {
        Answer answer = new Answer();
        List<String> inputList = Arrays.stream(inputStr.split(" ")).collect(Collectors.toList());
        answer.setNumList(inputList);
        return answer;
    }

    public void validate() throws AnswerFormatIncorrectException {
        long validatedNum = numList.stream()
                .map(num -> parseInt(num))
                .distinct()
                .filter(num -> num < 10).count();
        if (validatedNum < numList.size()) {
            throw new AnswerFormatIncorrectException("Answer format is incorrect");
        }
    }

    public Record check(Answer inputAnswer) {
        Record record = new Record();
        this.numList.forEach(num -> {
            int index = inputAnswer.getIndexOfNum(num);
            if (index != -1) {
                if (index == getIndexOfNum(num)) {
                    record.increaseCurrentNum();
                } else {
                    record.increaseIncludeOnlyNum();
                }
            }
        });
        return record;
    }

    public int getIndexOfNum(String num) {
        return this.numList.indexOf(num);
    }

    @Override
    public String toString() {
        return String.join(" ", numList);
    }
}
