package github.fredsonchaves07.domain.forum.usecases.fetchquestionanswers;

import github.fredsonchaves07.core.pagination.Pagination;
import github.fredsonchaves07.core.usecases.UseCase;
import github.fredsonchaves07.domain.forum.entities.answer.Answer;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.entities.question.QuestionID;
import github.fredsonchaves07.domain.forum.repositories.AnswersRepository;
import github.fredsonchaves07.domain.forum.repositories.QuestionRepository;

import java.util.List;
import java.util.Objects;

public class FetchQuestionsAnswersUseCase implements UseCase<FetchQuestionsAnswersInput, FetchQuestionsAnswersOutput> {

    private final AnswersRepository repository;

    public FetchQuestionsAnswersUseCase(AnswersRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public FetchQuestionsAnswersOutput execute(FetchQuestionsAnswersInput input) {
        List<Answer> answers = repository
                .findManyByQuestionId(new Pagination(input.page()), new QuestionID(input.questionId()));
        return new FetchQuestionsAnswersOutput(answers
                .stream()
                .map(AnswersOutput::from)
                .toList());
    }
}
