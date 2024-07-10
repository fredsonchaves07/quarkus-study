package github.fredsonchaves07.domain.forum.usecases.answerquestion;

import github.fredsonchaves07.core.valueObject.ValueObject;
import github.fredsonchaves07.domain.forum.entities.answer.Answer;

public record AnswerQuestionOutput(String content) implements ValueObject {

    public static AnswerQuestionOutput from(Answer answer) {
        return new AnswerQuestionOutput(answer.content());
    }
}
