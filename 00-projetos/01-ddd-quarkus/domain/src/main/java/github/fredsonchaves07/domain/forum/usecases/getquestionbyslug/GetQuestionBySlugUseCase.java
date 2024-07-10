package github.fredsonchaves07.domain.forum.usecases.getquestionbyslug;

import github.fredsonchaves07.core.errors.Either;
import github.fredsonchaves07.core.errors.Error;
import github.fredsonchaves07.core.usecases.UseCase;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.entities.valueobjects.Slug;
import github.fredsonchaves07.domain.forum.errors.QuestionNotFoundError;
import github.fredsonchaves07.domain.forum.repositories.QuestionRepository;

import java.util.Objects;
import java.util.Optional;

public class GetQuestionBySlugUseCase implements UseCase<GetQuestionBySlugInput, GetQuestionBySlugOutput> {

    private final QuestionRepository repository;

    public GetQuestionBySlugUseCase(QuestionRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public Either<Error, GetQuestionBySlugOutput> execute(GetQuestionBySlugInput input) {
        String slug = input.slug();
        Optional<Question> optionalQuestion = repository.findBySlug(Slug.createFromSlug(slug));
        return optionalQuestion.map(question -> Either.success(new GetQuestionBySlugOutput(
                question.id(),
                question.authorId().toString(),
                question.title(),
                question.content())
        )).orElseGet(() -> Either.error(QuestionNotFoundError.trows()));
    }
}
