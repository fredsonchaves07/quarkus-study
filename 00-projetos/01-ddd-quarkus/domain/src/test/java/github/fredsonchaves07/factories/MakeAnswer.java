package github.fredsonchaves07.factories;

import com.github.javafaker.Faker;
import github.fredsonchaves07.domain.forum.entities.answer.Answer;
import github.fredsonchaves07.domain.forum.entities.author.AuthorID;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.entities.question.QuestionID;

import java.util.Locale;

public class MakeAnswer {

    private static final Faker faker = new Faker(new Locale("pt-BR"));

    public static Answer makeAnswer() {
        return Answer.createAnswer(
                faker.lorem().characters(),
                new AuthorID(faker.internet().uuid()),
                new QuestionID(faker.internet().uuid())
        );
    }

//    public static Answer makeQuestion(String authorId, String title, String content) {
//        return Answer.createAnswer(new AuthorID(authorId), title, content);
//    }
//
//    public static Answer makeQuestion(QuestionID questionID, String authorId, String title, String content) {
//        return Answer.createAnswer(questionID, new AuthorID(authorId), title, content);
//    }
}
