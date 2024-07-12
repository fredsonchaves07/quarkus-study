package github.fredsonchaves07.domain.forum.usecases.createquestion;

import github.fredsonchaves07.core.valueObject.ValueObject;

import java.util.List;

public record CreateQuestionOutput(
        String questionId,
        String authorId,
        String title,
        String content,
        List<String> attachmentIds
) implements ValueObject {
}
