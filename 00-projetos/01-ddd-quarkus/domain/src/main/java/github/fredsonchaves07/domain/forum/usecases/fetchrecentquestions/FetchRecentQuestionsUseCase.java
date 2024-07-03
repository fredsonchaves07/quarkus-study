package github.fredsonchaves07.domain.forum.usecases.fetchrecentquestions;

import github.fredsonchaves07.core.pagination.Pagination;
import github.fredsonchaves07.core.usecases.UseCase;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.entities.valueobjects.Slug;
import github.fredsonchaves07.domain.forum.repositories.QuestionRepository;
import github.fredsonchaves07.domain.forum.usecases.getquestionbyslug.GetQuestionBySlugInput;
import github.fredsonchaves07.domain.forum.usecases.getquestionbyslug.GetQuestionBySlugOutput;

import java.util.List;
import java.util.Objects;

public class FetchRecentQuestionsUseCase implements UseCase<FetchRecentQuestionsInput, FetchRecentQuestionsOutput> {

    private final QuestionRepository repository;

    public FetchRecentQuestionsUseCase(QuestionRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public FetchRecentQuestionsOutput execute(FetchRecentQuestionsInput input) {
        List<Question> manyRecentQuestions = repository.findManyRecent(new Pagination(input.page()));
        return new FetchRecentQuestionsOutput(manyRecentQuestions
                .stream()
                .map(RecentQuestionOutput::from)
                .toList());
    }
}
