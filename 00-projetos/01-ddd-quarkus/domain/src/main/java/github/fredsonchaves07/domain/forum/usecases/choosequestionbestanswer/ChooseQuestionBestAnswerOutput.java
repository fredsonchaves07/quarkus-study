package github.fredsonchaves07.domain.forum.usecases.choosequestionbestanswer;

import github.fredsonchaves07.core.valueObject.ValueObject;

public record ChooseQuestionBestAnswerOutput(
        String questionId,
        String authorId,
        String title,
        String content
) implements ValueObject {

}
