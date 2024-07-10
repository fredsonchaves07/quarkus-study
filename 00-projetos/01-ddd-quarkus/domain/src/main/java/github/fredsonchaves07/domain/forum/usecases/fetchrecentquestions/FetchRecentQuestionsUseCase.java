package github.fredsonchaves07.domain.forum.usecases.fetchrecentquestions;

import github.fredsonchaves07.core.errors.Either;
import github.fredsonchaves07.core.errors.Error;
import github.fredsonchaves07.core.pagination.Pagination;
import github.fredsonchaves07.core.usecases.UseCase;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.repositories.QuestionRepository;

import java.util.List;
import java.util.Objects;

public class FetchRecentQuestionsUseCase implements UseCase<FetchRecentQuestionsInput, FetchRecentQuestionsOutput> {

    private final QuestionRepository repository;

    public FetchRecentQuestionsUseCase(QuestionRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public Either<Error, FetchRecentQuestionsOutput> execute(FetchRecentQuestionsInput input) {
        List<Question> manyRecentQuestions = repository.findManyRecent(new Pagination(input.page()));
        return Either.success(new FetchRecentQuestionsOutput(manyRecentQuestions
                .stream()
                .map(RecentQuestionOutput::from)
                .toList()));
    }
}
