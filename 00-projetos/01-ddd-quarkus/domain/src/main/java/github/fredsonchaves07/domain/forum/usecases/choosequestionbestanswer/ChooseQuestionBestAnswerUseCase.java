package github.fredsonchaves07.domain.forum.usecases.choosequestionbestanswer;

import github.fredsonchaves07.core.errors.Either;
import github.fredsonchaves07.core.errors.Error;
import github.fredsonchaves07.core.usecases.UseCase;
import github.fredsonchaves07.domain.forum.entities.answer.Answer;
import github.fredsonchaves07.domain.forum.entities.answer.AnswerID;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.errors.AnswerNotFoundError;
import github.fredsonchaves07.domain.forum.errors.NotAllowedError;
import github.fredsonchaves07.domain.forum.errors.QuestionNotFoundError;
import github.fredsonchaves07.domain.forum.repositories.AnswersRepository;
import github.fredsonchaves07.domain.forum.repositories.QuestionRepository;

import java.util.Objects;
import java.util.Optional;

public class ChooseQuestionBestAnswerUseCase implements UseCase<ChooseQuestionBestAnswerInput, ChooseQuestionBestAnswerOutput> {

    private final QuestionRepository questionRepository;

    private final AnswersRepository answersRepository;

    public ChooseQuestionBestAnswerUseCase(
            QuestionRepository questionRepository, AnswersRepository answersRepository
    ) {
        this.questionRepository = Objects.requireNonNull(questionRepository);
        this.answersRepository = Objects.requireNonNull(answersRepository);
    }

    public Either<Error, ChooseQuestionBestAnswerOutput> execute(ChooseQuestionBestAnswerInput input) {
        AnswerID answerID = new AnswerID(input.anwerId());
        Optional<Answer> answer = answersRepository.findById(new AnswerID(input.anwerId()));
        if (answer.isEmpty())
            return Either.error(AnswerNotFoundError.trows());
        Optional<Question> question = questionRepository.findById(answer.get().questionId());
        if (question.isEmpty())
            return Either.error(QuestionNotFoundError.trows());
        if (!input.authorId().equals(question.get().authorId().toString()))
            return Either.error(NotAllowedError.trows());
        question.get().updateBestAnwserId(answerID);
        questionRepository.update(question.get());
        return Either.success(new ChooseQuestionBestAnswerOutput(
                question.get().id(),
                question.get().authorId().toString(),
                question.get().title(),
                question.get().content()
        ));
    }
}
