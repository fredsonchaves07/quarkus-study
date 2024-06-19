package useCases;

import entities.Answer;
import repositories.AnswersRepository;

import java.util.Objects;

public class AnswerQuestionUseCase {

    private final AnswersRepository repository;

    public AnswerQuestionUseCase(AnswersRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    public AnswerQuestionOutput execute(AnswerQuestionInput answerQuestionInput) {
        String questionId = answerQuestionInput.questionId();
        String instructorId = answerQuestionInput.instructorId();
        String content = answerQuestionInput.content();
        Answer answer = Answer.createAnswer(content, instructorId, questionId);
        repository.create(answer);
        return AnswerQuestionOutput.from(answer);
    }
}
