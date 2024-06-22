package github.fredsonchaves07.domain.forum.usecases.answerquestion;

import github.fredsonchaves07.core.usecases.UseCase;
import github.fredsonchaves07.domain.forum.entities.answer.Answer;
import github.fredsonchaves07.domain.forum.entities.instructor.InstructorID;
import github.fredsonchaves07.domain.forum.entities.question.QuestionID;
import github.fredsonchaves07.domain.forum.repositories.AnswersRepository;

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
