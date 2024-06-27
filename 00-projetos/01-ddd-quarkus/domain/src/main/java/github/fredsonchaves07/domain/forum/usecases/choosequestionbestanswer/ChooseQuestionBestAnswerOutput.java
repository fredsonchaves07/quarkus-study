package github.fredsonchaves07.domain.forum.usecases.choosequestionbestanswer;

import github.fredsonchaves07.domain.forum.entities.answer.Answer;

public record ChooseQuestionBestAnswerOutput(
        String questionId,
        String authorId,
        String title,
        String content
) {

}
