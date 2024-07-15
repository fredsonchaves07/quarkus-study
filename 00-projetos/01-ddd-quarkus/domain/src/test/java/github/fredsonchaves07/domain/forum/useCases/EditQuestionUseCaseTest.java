package github.fredsonchaves07.domain.forum.useCases;

import github.fredsonchaves07.core.errors.Either;
import github.fredsonchaves07.core.errors.Error;
import github.fredsonchaves07.core.valueObject.ValueObject;
import github.fredsonchaves07.db.repositories.forum.FakeQuestionsRepository;
import github.fredsonchaves07.domain.forum.entities.attachment.QuestionAttachment;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.repositories.QuestionRepository;
import github.fredsonchaves07.domain.forum.usecases.deletequestion.DeleteQuestionInput;
import github.fredsonchaves07.domain.forum.usecases.deletequestion.DeleteQuestionUseCase;
import github.fredsonchaves07.domain.forum.usecases.editquestion.EditQuestionInput;
import github.fredsonchaves07.domain.forum.usecases.editquestion.EditQuestionUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static github.fredsonchaves07.factories.MakeQuestion.*;
import static github.fredsonchaves07.factories.MakeQuestionAttachment.makeQuestionAttachment;
import static org.junit.jupiter.api.Assertions.*;

public class EditQuestionUseCaseTest {

    private static EditQuestionUseCase useCase;

    private final static Question question = makeQuestion();

    private final static QuestionAttachment questionAttachment1 = makeQuestionAttachment(question.id());

    private final static QuestionAttachment questionAttachment2 = makeQuestionAttachment(question.id());;

    @BeforeEach
    void setUp() {
        QuestionRepository repository = FakeQuestionsRepository.createRepository();
        useCase = new EditQuestionUseCase(repository);
        repository.create(question);
    }

    @Test
    public void shouldBeEditQuestion() {
        EditQuestionInput input = new EditQuestionInput(
                question.authorId().toString(),
                question.id(),
                makeTitleQuestion(),
                makeContentQuestion(),
                List.of("1", "3")
                );
        Either<Error, ValueObject> output = useCase.execute(input);
        assertTrue(output.isSuccess());
    }

    @Test
    public void shouldNotBeAbleToEditAQuestionFromAnotherUser() {
        EditQuestionInput input = new EditQuestionInput(
                "123",
                question.id(),
                makeTitleQuestion(),
                makeContentQuestion(),
                List.of("1", "3"));
        Either<Error, ValueObject> output = useCase.execute(input);
        assertTrue(output.isError());
    }
}
