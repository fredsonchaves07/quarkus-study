package github.fredsonchaves07.domain.forum.usecases.commentonquestion;

import github.fredsonchaves07.core.valueObject.ValueObject;

public record CommentOnQuestionInput(
        String authorId,
        String questionId,
        String content
) implements ValueObject {

}
