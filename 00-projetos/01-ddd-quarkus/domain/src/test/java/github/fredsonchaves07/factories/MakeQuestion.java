package github.fredsonchaves07.factories;

import github.fredsonchaves07.domain.forum.entities.author.AuthorID;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import com.github.javafaker.Faker;
import github.fredsonchaves07.domain.forum.entities.question.QuestionID;

import java.util.Locale;

public class MakeQuestion {

    private static final Faker faker = new Faker(new Locale("pt-BR"));

    public static Question makeQuestion() {
        return Question.createQuestion(
                new AuthorID(faker.internet().uuid()), faker.lorem().sentence(), faker.lorem().characters()
        );
    }

    public static Question makeQuestion(String authorId, String title, String content) {
        return Question.createQuestion(new AuthorID(authorId), title, content);
    }

    public static Question makeQuestion(QuestionID questionID, String authorId, String title, String content) {
        return Question.createQuestion(questionID, new AuthorID(authorId), title, content);
    }

    public static String makeTitleQuestion() {
        return faker.lorem().sentence();
    }

    public static String makeContentQuestion() {
        return faker.lorem().characters();
    }
}
