package github.fredsonchaves07.domain.forum.usecases.editquestion;

import github.fredsonchaves07.core.valueObject.ValueObject;

import java.util.List;

public record EditQuestionInput(
        String authorId,
        String questionId,
        String title,
        String content,
        List<String>attachmentId
) implements ValueObject {
}
