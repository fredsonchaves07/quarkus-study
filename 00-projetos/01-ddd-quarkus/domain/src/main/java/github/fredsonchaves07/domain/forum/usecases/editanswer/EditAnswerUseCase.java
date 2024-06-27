package github.fredsonchaves07.domain.forum.usecases.editanswer;

import github.fredsonchaves07.core.usecases.InputUseCase;
import github.fredsonchaves07.domain.forum.entities.answer.Answer;
import github.fredsonchaves07.domain.forum.entities.answer.AnswerID;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.entities.question.QuestionID;
import github.fredsonchaves07.domain.forum.repositories.AnswersRepository;
import github.fredsonchaves07.domain.forum.repositories.QuestionRepository;

import java.util.Objects;

public class EditAnswerUseCase implements InputUseCase<EditAnswerInput> {

    private final AnswersRepository repository;

    public EditAnswerUseCase(AnswersRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public void execute(EditAnswerInput input) {
        Answer answer = repository
                .findById(new AnswerID(input.answerId()))
                .orElseThrow(() -> new Error("Question not found"));
        if (!answer.authorId().toString().equals(input.authorId()))
            throw new Error("Not allowed");
        answer.content(input.content());
        repository.update(answer);
    }
}
