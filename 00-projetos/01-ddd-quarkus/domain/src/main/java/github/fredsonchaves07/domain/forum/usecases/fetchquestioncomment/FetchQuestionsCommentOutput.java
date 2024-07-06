package github.fredsonchaves07.domain.forum.usecases.fetchquestioncomment;

import java.util.List;

public record FetchQuestionsCommentOutput(
        List<CommentsOutput> questions
) {
}
