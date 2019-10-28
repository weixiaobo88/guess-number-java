package com.thoughtworks.guessnumber.generator;

import com.thoughtworks.guessnumber.Answer;
import com.thoughtworks.guessnumber.exception.AnswerFormatIncorrectException;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AnswerGeneratorTest {
    @Test(expected = AnswerFormatIncorrectException.class)
    public void should_throw_OutOfRangeAnswerException_which_is_not_between_0_and_9() throws AnswerFormatIncorrectException {
        RandomIntGenerator randomIntGenerator = mock(RandomIntGenerator.class);
        when(randomIntGenerator.generateNums(anyInt(), anyInt())).thenReturn("1 2 3 10");
        AnswerGenerator answerGenerator = new AnswerGenerator(randomIntGenerator);

        answerGenerator.generate();
    }

    @Test
    public void should_get_random_number() throws Exception {
        RandomIntGenerator randomIntGenerator = mock(RandomIntGenerator.class);
        when(randomIntGenerator.generateNums(anyInt(), anyInt())).thenReturn("1 2 3 4");
        AnswerGenerator answerGenerator = new AnswerGenerator(randomIntGenerator);

        Answer answer = answerGenerator.generate();
        assertNotNull(answer);
        assertThat(answer.getIndexOfNum("4"), is(3));
    }
}
