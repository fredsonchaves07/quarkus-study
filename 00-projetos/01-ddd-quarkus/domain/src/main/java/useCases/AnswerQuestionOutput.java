package useCases;

import entities.Answer;

public record AnswerQuestionOutput(String content) {

    public static AnswerQuestionOutput from(Answer answer) {
        return new AnswerQuestionOutput(answer.content());
    }
}
