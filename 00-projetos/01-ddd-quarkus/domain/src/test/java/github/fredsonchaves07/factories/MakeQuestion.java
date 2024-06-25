package github.fredsonchaves07.factories;

import github.fredsonchaves07.domain.forum.entities.author.AuthorID;
import github.fredsonchaves07.domain.forum.entities.question.Question;

public class MakeQuestion {

    public static Question makeQuestion() {
        return Question.createQuestion(
                new AuthorID("1"), "Nova pergunta", "Conteúdo da perguta"
        );
    }

    public static Question makeQuestion(String authorId, String title, String content) {
        return Question.createQuestion(new AuthorID(authorId), title, content);
    }
}
