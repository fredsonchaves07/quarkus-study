package github.fredsonchaves07.domain.forum.usecases.getquestionbyslug;

import github.fredsonchaves07.core.errors.Either;
import github.fredsonchaves07.core.errors.Error;
import github.fredsonchaves07.core.usecases.UseCase;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.entities.valueobjects.Slug;
import github.fredsonchaves07.domain.forum.repositories.QuestionRepository;

import java.util.Objects;

public class GetQuestionBySlugUseCase implements UseCase<GetQuestionBySlugInput, Either<Error, GetQuestionBySlugOutput>> {

    private final QuestionRepository repository;

    public GetQuestionBySlugUseCase(QuestionRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public Either<Error, GetQuestionBySlugOutput> execute(GetQuestionBySlugInput input) {
        String slug = input.slug();
        Question question = repository
                .findBySlug(Slug.createFromSlug(slug))
                .orElseThrow(() -> new Error("Question not found"));
        Either.success(new GetQuestionBySlugOutput(
                question.id(), question.authorId().toString(), question.title(), question.content()));
    }
}
