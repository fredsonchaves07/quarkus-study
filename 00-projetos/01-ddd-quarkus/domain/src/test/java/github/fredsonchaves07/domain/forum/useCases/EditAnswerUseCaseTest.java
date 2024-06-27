package github.fredsonchaves07.domain.forum.useCases;

import github.fredsonchaves07.db.repositories.forum.FakeAnswersRepository;
import github.fredsonchaves07.domain.forum.entities.answer.Answer;
import github.fredsonchaves07.domain.forum.repositories.AnswersRepository;
import github.fredsonchaves07.domain.forum.usecases.editanswer.EditAnswerInput;
import github.fredsonchaves07.domain.forum.usecases.editanswer.EditAnswerUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static github.fredsonchaves07.factories.MakeAnswer.makeAnswer;
import static github.fredsonchaves07.factories.MakeAnswer.makeContentAnswer;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EditAnswerUseCaseTest {

    private static EditAnswerUseCase useCase;

    private final static Answer answer = makeAnswer();

    @BeforeEach
    void setUp() {
        AnswersRepository repository = FakeAnswersRepository.createRepository();
        useCase = new EditAnswerUseCase(repository);
        repository.create(answer);
    }

    @Test
    public void shouldBeEditAnswer() {
        EditAnswerInput input = new EditAnswerInput(
                answer.authorId().toString(),
                answer.id(),
                makeContentAnswer());
        assertDoesNotThrow(() -> useCase.execute(input));
    }

    @Test
    public void shouldNotBeAbleToEditAAnswerFromAnotherUser() {
        EditAnswerInput input = new EditAnswerInput(
                "123",
                answer.id(),
                makeContentAnswer());
        assertThrows(Error.class, () -> useCase.execute(input));
    }
}
