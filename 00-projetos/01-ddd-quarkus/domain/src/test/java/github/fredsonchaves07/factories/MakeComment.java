package github.fredsonchaves07.factories;

import com.github.javafaker.Faker;
import github.fredsonchaves07.domain.forum.entities.author.AuthorID;
import github.fredsonchaves07.domain.forum.entities.comment.Comment;
import github.fredsonchaves07.domain.forum.entities.comment.CommentID;
import github.fredsonchaves07.domain.forum.entities.question.Question;

import java.util.Locale;

public class MakeComment {

    private static final Faker faker = new Faker(new Locale("pt-BR"));

    public static Comment makeComment() {
        return Comment.create(new AuthorID(faker.internet().uuid()), faker.lorem().characters());
    }

    public static String makeCommentContent() {
        return faker.lorem().characters();
    }

    public static AuthorID makeCommentAuthorId() {
        return new AuthorID(faker.internet().uuid());
    }
}
