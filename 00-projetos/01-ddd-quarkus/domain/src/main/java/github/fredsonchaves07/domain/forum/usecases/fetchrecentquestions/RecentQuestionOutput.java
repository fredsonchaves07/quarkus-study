package github.fredsonchaves07.domain.forum.usecases.fetchrecentquestions;


import github.fredsonchaves07.domain.forum.entities.question.Question;

public record RecentQuestionOutput(
        String questionId,
        String authorId,
        String title,
        String content
){

    public static RecentQuestionOutput from(Question question) {
        return new RecentQuestionOutput(
                question.id(), question.authorId().toString(), question.title(), question.content()
        );
    }
}