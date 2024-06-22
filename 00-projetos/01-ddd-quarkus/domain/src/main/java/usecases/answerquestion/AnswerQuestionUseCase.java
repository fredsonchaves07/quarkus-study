package usecases.answerquestion;

import core.usecases.UseCase;
import entities.answer.Answer;
import entities.instructor.InstructorID;
import entities.question.QuestionID;
import repositories.AnswersRepository;

import java.util.Objects;

public class AnswerQuestionUseCase implements UseCase<AnswerQuestionInput, AnswerQuestionOutput> {

    private final AnswersRepository repository;

    public AnswerQuestionUseCase(AnswersRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    public AnswerQuestionOutput execute(AnswerQuestionInput answerQuestionInput) {
        String questionId = answerQuestionInput.questionId();
        String instructorId = answerQuestionInput.instructorId();
        String content = answerQuestionInput.content();
        Answer answer = Answer.createAnswer(content, new InstructorID(instructorId), new QuestionID(questionId));
        repository.create(answer);
        return AnswerQuestionOutput.from(answer);
    }
}
