package github.fredsonchaves07.domain.forum.usecases.deleteanswer;

import github.fredsonchaves07.core.usecases.InputUseCase;
import github.fredsonchaves07.domain.forum.entities.answer.Answer;
import github.fredsonchaves07.domain.forum.entities.answer.AnswerID;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.entities.question.QuestionID;
import github.fredsonchaves07.domain.forum.repositories.AnswersRepository;
import github.fredsonchaves07.domain.forum.repositories.QuestionRepository;
import github.fredsonchaves07.domain.forum.usecases.deletequestion.DeleteQuestionInput;

import java.util.Objects;

public class DeleteAnswerUseCase implements InputUseCase<DeleteAnswerInput> {

    private final AnswersRepository repository;

    public DeleteAnswerUseCase(AnswersRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public void execute(DeleteAnswerInput input) {
        Answer answer = repository
                .findById(new AnswerID(input.answerId()))
                .orElseThrow(() -> new Error("Answer not found"));
        if (!answer.authorId().toString().equals(input.authorId()))
            throw new Error("Not allowed");
        repository.delete(answer);
    }
}
