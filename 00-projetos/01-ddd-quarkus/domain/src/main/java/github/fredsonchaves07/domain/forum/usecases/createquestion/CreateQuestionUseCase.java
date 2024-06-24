package github.fredsonchaves07.domain.forum.usecases.createquestion;

import github.fredsonchaves07.core.usecases.UseCase;
import github.fredsonchaves07.domain.forum.entities.author.AuthorID;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.repositories.QuestionRepository;

import java.util.Objects;

public class CreateQuestionUseCase implements UseCase<CreateQuestionInput, CreateQuestionOutput> {

    private final QuestionRepository repository;

    public CreateQuestionUseCase(QuestionRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public CreateQuestionOutput execute(CreateQuestionInput input) {
        String content = input.content();
        String authorId = input.authorId();
        String title = input.title();
        Question question = Question.createQuestion(new AuthorID(authorId), title, content);
        repository.create(question);
        return new CreateQuestionOutput(question.id(), authorId, title, content);
    }
}
