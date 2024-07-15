package github.fredsonchaves07.factories;

import com.github.javafaker.Faker;
import github.fredsonchaves07.domain.forum.entities.attachment.AttachmentID;
import github.fredsonchaves07.domain.forum.entities.attachment.QuestionAttachment;
import github.fredsonchaves07.domain.forum.entities.question.QuestionID;

import java.util.Locale;

public class MakeQuestionAttachment {

    private static final Faker faker = new Faker(new Locale("pt-BR"));

    public static QuestionAttachment makeQuestionAttachment(String questionID) {
        return QuestionAttachment.create(new QuestionID(questionID), new AttachmentID(faker.internet().uuid()));
    }
}
