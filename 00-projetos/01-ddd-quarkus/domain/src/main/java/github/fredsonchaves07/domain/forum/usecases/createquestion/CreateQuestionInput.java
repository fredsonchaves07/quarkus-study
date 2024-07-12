package github.fredsonchaves07.domain.forum.usecases.createquestion;

import github.fredsonchaves07.core.valueObject.ValueObject;

import java.util.List;

public record CreateQuestionInput(
        String authorId,
        String title,
        String content,
        List<String> attachmentId
) implements ValueObject {
}
