package github.fredsonchaves07.domain.forum.usecases.answerquestion;

import github.fredsonchaves07.core.valueObject.ValueObject;

public record AnswerQuestionInput(String instructorId, String questionId, String content)
implements ValueObject {
}
