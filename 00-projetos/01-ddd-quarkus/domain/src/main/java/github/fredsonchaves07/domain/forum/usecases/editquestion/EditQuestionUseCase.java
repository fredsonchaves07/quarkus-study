package github.fredsonchaves07.domain.forum.usecases.editquestion;

import github.fredsonchaves07.core.usecases.InputUseCase;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.entities.question.QuestionID;
import github.fredsonchaves07.domain.forum.repositories.QuestionRepository;
import github.fredsonchaves07.domain.forum.usecases.deletequestion.DeleteQuestionInput;

import java.util.Objects;

public class EditQuestionUseCase implements InputUseCase<EditQuestionInput> {

    private final QuestionRepository repository;

    public EditQuestionUseCase(QuestionRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public void execute(EditQuestionInput input) {
        Question question = repository
                .findById(new QuestionID(input.questionId()))
                .orElseThrow(() -> new Error("Question not found"));
        if (!question.authorId().toString().equals(input.authorId()))
            throw new Error("Not allowed");
        question.title(input.title());
        question.title(input.content());
        repository.update(question);
    }
}
