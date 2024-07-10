package github.fredsonchaves07.domain.forum.usecases.commentonquestion;

import github.fredsonchaves07.core.valueObject.ValueObject;

public record CommentOnQuestionOutput(
        String authorId,
        String content
) implements ValueObject {

}
