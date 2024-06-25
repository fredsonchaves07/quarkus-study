package github.fredsonchaves07.domain.forum.usecases.deletequestion;

import github.fredsonchaves07.core.usecases.InputUseCase;
import github.fredsonchaves07.core.usecases.UseCase;
import github.fredsonchaves07.domain.forum.entities.author.AuthorID;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.entities.question.QuestionID;
import github.fredsonchaves07.domain.forum.repositories.QuestionRepository;
import github.fredsonchaves07.domain.forum.usecases.createquestion.CreateQuestionInput;
import github.fredsonchaves07.domain.forum.usecases.createquestion.CreateQuestionOutput;

import java.util.Objects;

public class DeleteQuestionUseCase implements InputUseCase<DeleteQuestionInput> {

    private final QuestionRepository repository;

    public DeleteQuestionUseCase(QuestionRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public void execute(DeleteQuestionInput input) {
        Question question = repository
                .findById(new QuestionID(input.questionId()))
                .orElseThrow(() -> new Error("Question not found"));
        if (!question.authorId().toString().equals(input.authorId()))
            throw new Error("Not allowed");
        repository.delete(question);
    }
}
