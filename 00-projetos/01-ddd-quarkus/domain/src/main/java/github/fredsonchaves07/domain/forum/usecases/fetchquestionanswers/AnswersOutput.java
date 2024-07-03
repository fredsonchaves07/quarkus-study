package github.fredsonchaves07.domain.forum.usecases.fetchquestionanswers;


import github.fredsonchaves07.domain.forum.entities.answer.Answer;

public record AnswersOutput(
        String answerId,
        String questionId,
        String authorId,
        String content
){

    public static AnswersOutput from(Answer answer) {
        return new AnswersOutput(
                answer.id(), answer.questionId().toString(), answer.authorId().toString(), answer.content()
        );
    }
}