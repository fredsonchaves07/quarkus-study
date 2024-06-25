package github.fredsonchaves07.domain.forum.usecases.getquestionbyslug;

import github.fredsonchaves07.core.usecases.UseCase;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.entities.valueobjects.Slug;
import github.fredsonchaves07.domain.forum.repositories.QuestionRepository;

import java.util.Objects;

public class GetQuestionBySlugUseCase implements UseCase<GetQuestionBySlugInput, GetQuestionBySlugOutput> {

    private final QuestionRepository repository;

    public GetQuestionBySlugUseCase(QuestionRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public GetQuestionBySlugOutput execute(GetQuestionBySlugInput input) {
        String slug = input.slug();
        Question question = repository
                .findBySlug(Slug.createFromSlug(slug))
                .orElseThrow(() -> new Error("Question not found"));
        return new GetQuestionBySlugOutput(
                question.id(), question.authorId().toString(), question.title(), question.content()
        );
    }
}
